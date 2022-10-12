package dtalk.dto.quiz;

import dtalk.domain.CUTime;
import dtalk.domain.Quiz;
import dtalk.domain.Record;
import dtalk.domain.User;
import dtalk.domain.status.RecordStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class QuizSendDTO {
    @NotBlank
    private Long quizIdx;
    @NotBlank
    private Long userIdx;

    private Quiz getQuiz(){
        Quiz quiz = new Quiz();
        quiz.setIdx(quizIdx);
        return quiz;
    };
    private User getUser(){
        User user = new User();
        user.setIdx(userIdx);
        return user;
    };
    public Record getRecord(){
        Record record = new Record();
        record.setQuiz(getQuiz());
        record.setUser(getUser());
        record.setRecordStatus(RecordStatus.대기);
        record.setCuTime(new CUTime(LocalDateTime.now()));
        record.setRecommend(0);

        return record;
    }
}
