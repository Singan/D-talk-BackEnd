package dtalk.scheduler;

import dtalk.domain.Quiz;
import dtalk.domain.User;
import dtalk.service.QuizService;
import dtalk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class RankScheduler {
    @Autowired
    private QuizService quizService;
/*    @Scheduled(fixedDelay = 1000)
    public void test(){
        System.out.println("스케쥴 테스트");
        User user = userService.findByUserId("aaaa1111");
        System.out.println(user.getNickname());
    }*/
    @Scheduled(fixedDelay = 1000)
    public void quizRank(){

        LocalDateTime  localDateTime2 = LocalDateTime .now();
        LocalDateTime  localDateTime1 = localDateTime2.minusDays(6);

        List<Quiz> quizList = quizService.rankQuizList(localDateTime1,localDateTime2);

    }
}
