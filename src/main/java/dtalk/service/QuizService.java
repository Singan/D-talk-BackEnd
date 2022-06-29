package dtalk.service;

import dtalk.domain.Quiz;
import dtalk.domain.User;
import dtalk.dto.quiz.QuizSaveDto;
import dtalk.dto.user.UserDetailDTO;
import dtalk.repository.QuizRepository;
import dtalk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    private final UserRepository userRepository;
    public Long save(QuizSaveDto quizSaveDto){
        Quiz quiz = QuizSaveDto.createQuiz(quizSaveDto);
        UserDetailDTO u = (UserDetailDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = new User();
        user.setIdx(u.getIdx());
        System.out.println("퀴즈 세이브 유저" + u.getUser().getIdx());
        quiz.setUser(user);
        return quizRepository.save(quiz);
    }

    public List<Quiz> myList(User user){

        return quizRepository.list(user);
    };
}
