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
    public Record getRecord(Quiz quiz){
        User me = ((UserDetailDTO) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        Record record = new Record();


        return record;
    }
}
