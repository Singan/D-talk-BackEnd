package dtalk.dto.quiz;

import dtalk.domain.Quiz;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class QuizListDTO {
    private Long idx;
    private String thumbImg;
    private Integer sec;

    public static QuizListDTO getQuizListDTO(Quiz quiz){
        QuizListDTO quizListDTO = new QuizListDTO();
        quizListDTO.setIdx(quiz.getIdx());
        quizListDTO.setSec(quiz.getSec());
        quizListDTO.setThumbImg(quiz.getThumbImg());
        return quizListDTO;
    }
}
