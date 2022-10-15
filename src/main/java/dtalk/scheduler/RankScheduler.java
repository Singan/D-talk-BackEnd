package dtalk.scheduler;

import dtalk.domain.*;
import dtalk.domain.status.BatchType;
import dtalk.dto.rank.RankDTO;
import dtalk.repository.BatchRepository;
import dtalk.service.QuizService;
import dtalk.service.RankService;
import dtalk.service.RecordService;
import dtalk.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RankScheduler {

    private final QuizService quizService;
    private final RecordService recordService;
    private final RankService rankService;
    private final BatchRepository batchRepository;
/*    @Scheduled(fixedDelay = 1000)
    public void test(){
        System.out.println("스케쥴 테스트");
        User user = userService.findByUserId("aaaa1111");
        System.out.println(user.getNickname());
    }*/
    @Scheduled(cron = " 0 0 2 * * 1")
    public void weeklyRank(){
        LocalDateTime  localDateTime2 = LocalDateTime .now().minusDays(1);
        LocalDateTime  localDateTime1 = localDateTime2.minusDays(6);

        Batch batch = new Batch();
        batch.setCuTime(new CUTime(LocalDateTime.now()));
        batch.setType(BatchType.주간);
        List<Quiz> quizList = quizService.rankQuizList(localDateTime1,localDateTime2);
        batchRepository.batchIdx(batch);
        for(int i = 0 ; i <quizList.size() ; i++){
            QuizRank rank = new QuizRank();
            rank.setQuiz(quizList.get(i));
            rank.setBatch(batch);
            rank.setRecommend(recordService.findRecordLike(quizList.get(i)));
            rankService.rankUpdate(rank);
        }

    }
    @Scheduled(cron = " 0 17 23 * * *")
    public void daysRank(){
        LocalDateTime  next = LocalDateTime.now();
        LocalDateTime  prev = next.minusDays(100);
        Batch batch = new Batch();
        batch.setCuTime(new CUTime(LocalDateTime.now()));
        batch.setType(BatchType.일간);
        List<Quiz> quizList = quizService.rankQuizList(prev,next);
        batchRepository.batchIdx(batch);
        for(int i = 0 ; i <quizList.size() ; i++){
            QuizRank rank = new QuizRank();
            rank.setQuiz(quizList.get(i));
            rank.setBatch(batch);
            rank.setRecommend(recordService.findRecordLike(quizList.get(i)));
            rankService.rankUpdate(rank);
            System.out.println("왜안들어오냐고계속");
        }

    }
}
