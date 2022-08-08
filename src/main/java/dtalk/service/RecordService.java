package dtalk.service;

import dtalk.domain.Quiz;
import dtalk.domain.Record;
import dtalk.domain.User;
import dtalk.domain.status.RecordStatus;
import dtalk.repository.QuizRepository;
import dtalk.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordService {
    private final RecordRepository recordRepository;

    public void quizRecommend(User user, Quiz quiz){
        Record record = new Record();
        record.setQuiz(quiz);
        record.setUser(user);
        recordRepository.quizRecommend(record);
    }
    public Record findRecord(Record record){
        return recordRepository.findRecord(record);
    }

    public Long userRecommendCount(User user){
        Record record = new Record();

        record.setUser(user);
        return recordRepository.userRecommendCount(record);
    }
    public void quizPlay(User user,Quiz quiz,boolean flag){
        Record record = new Record();
        record.setUser(user);
        record.setQuiz(quiz);
        Record findRecord = this.findRecord(record);
        RecordStatus recordStatus = RecordStatus.실패;
        if(flag){
            recordStatus = RecordStatus.성공;
        }
        findRecord.setRecordStatus(recordStatus);
        recordRepository.updateRecord(findRecord);
    }
    public List<Quiz> receiveQuizList(User user){
        return recordRepository.receiveQuizList(user);
    }
}
