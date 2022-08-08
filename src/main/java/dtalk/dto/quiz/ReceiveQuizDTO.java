package dtalk.dto.quiz;

import dtalk.domain.Quiz;
import dtalk.domain.User;
import lombok.Data;

@Data
public class ReceiveQuizDTO {
    private Long idx;
    private String thumbImg;
    private String userName;

    public static ReceiveQuizDTO getQuizListDTO(Quiz quiz){
        ReceiveQuizDTO quizListDTO = new ReceiveQuizDTO();
        quizListDTO.setIdx(quiz.getIdx());
        quizListDTO.setThumbImg(quiz.getThumbImg());
        quizListDTO.setUserName(quiz.getUser().getNickname());
        return quizListDTO;
    }
}
