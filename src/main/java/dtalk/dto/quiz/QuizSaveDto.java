package dtalk.dto.quiz;

import dtalk.domain.CUTime;
import dtalk.domain.Quiz;
import dtalk.domain.User;
import dtalk.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@AllArgsConstructor
@NotBlank
public class QuizSaveDto {
    private String keyword;
    private String thumbImg;
    private String detail;
    private Integer sec;

    public static Quiz createQuiz(QuizSaveDto quizSaveDto) {
        System.out.println(quizSaveDto.keyword);
        Quiz quiz = new Quiz();
        quiz.setKeyword(quizSaveDto.keyword);
        quiz.setThumbImg(quizSaveDto.thumbImg);
        quiz.setDetail(quizSaveDto.detail);
        quiz.setSec(quizSaveDto.sec);
        quiz.setRemove(false);
        quiz.setCuTime(new CUTime(LocalDateTime.now()));
        return quiz;
    }
}