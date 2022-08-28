package dtalk.scheduler;

import dtalk.domain.*;
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
    public void quizRank(){
        LocalDateTime  localDateTime2 = LocalDateTime .now().minusDays(1);
        LocalDateTime  localDateTime1 = localDateTime2.minusDays(6);

        Batch batch = new Batch();
        batch.setCuTime(new CUTime(LocalDateTime.now()));
        List<Quiz> quizList = quizService.rankQuizList(localDateTime1,localDateTime2);
        batchRepository.batchIdx(batch);
        List<Rank> rankList = new ArrayList<>();
        for(int i = 0 ; i <quizList.size() ; i++){
            System.out.println(quizList.get(i).getKeyword() + " " + i + "번입니다.");
            Rank rank = new Rank();
            rank.setRank(i+1);
            rank.setQuiz(quizList.get(i));
            rank.setBatch(batch);
            rankService.rankUpdate(rank);
            System.out.println(quizList.get(i).getIdx());
        }

    }
}
