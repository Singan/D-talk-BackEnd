package dtalk.controller;

import dtalk.domain.User;
import dtalk.dto.quiz.QuizSaveDto;
import dtalk.dto.user.UserDetailDTO;
import dtalk.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quiz")
public class QuizController {
    private final QuizService quizService;
    @PostMapping
    public Long save(@RequestBody @Valid QuizSaveDto quizSaveDto){
        return quizService.save(quizSaveDto);
    }
    @GetMapping
    public void myList(){
        User me = ((UserDetailDTO) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        quizService.myList(me);

    }

    @GetMapping("/quiz/send")
    public void quizSend(){


    }
}
