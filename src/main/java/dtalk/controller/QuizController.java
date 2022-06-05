package dtalk.controller;

import dtalk.dto.quiz.QuizSaveDto;
import dtalk.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quiz")
public class QuizController {
    private final QuizService quizService;
    @PostMapping
    public Long save(@RequestBody @Valid QuizSaveDto quizSaveDto){
        System.out.println("퀴즈세이브");
        return quizService.save(quizSaveDto);
    }
}
