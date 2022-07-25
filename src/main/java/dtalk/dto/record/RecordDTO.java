package dtalk.dto.record;

import dtalk.domain.CUTime;
import dtalk.domain.Quiz;
import dtalk.domain.Record;
import dtalk.domain.User;
import dtalk.domain.status.RecordStatus;
import dtalk.dto.user.UserDetailDTO;
import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;

@Data
public class RecordDTO {
    private Long quizIdx;
    private String answer;
    private Integer sec;
    public Record getRecord(){
        User me = ((UserDetailDTO) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        Record record = new Record();
        Quiz quiz = new Quiz();
        quiz.setIdx(quizIdx);
        record.setQuiz(quiz);
        record.setUser(me);
        record.setSec(sec);
        record.setRecordStatus(RecordStatus.성공);
        return record;
    }
}
