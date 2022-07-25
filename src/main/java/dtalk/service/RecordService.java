package dtalk.service;

import dtalk.domain.Quiz;
import dtalk.domain.Record;
import dtalk.domain.User;
import dtalk.repository.QuizRepository;
import dtalk.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public Long userRecommendCount(User user){
        Record record = new Record();

        record.setUser(user);
        return recordRepository.userRecommendCount(record);
    }
    public void quizPlay(User user,Quiz quiz){

    }
}
