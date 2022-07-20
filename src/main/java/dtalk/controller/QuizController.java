package dtalk.controller;

import dtalk.domain.Quiz;
import dtalk.domain.User;
import dtalk.dto.quiz.QuizSaveDto;
import dtalk.dto.quiz.QuizSendDTO;
import dtalk.dto.user.UserDetailDTO;
import dtalk.dto.user.UserResponseDTO;
import dtalk.service.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quiz")
@Valid
public class QuizController {
    private final QuizService quizService;
    @PostMapping
    public Long save(@RequestBody QuizSaveDto quizSaveDto){
        return quizService.save(quizSaveDto);
    }
    @GetMapping
    public List<Quiz> myList(){
        User me = ((UserDetailDTO) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        return quizService.myList(me);
    }
    @GetMapping("/count")
    @Operation(description = "퀴즈 수")
    public Long countList(@RequestParam(required = false) Long idx){
        User user = new User();
        user.setIdx(idx);
        if(idx == null) {
            User me = ((UserDetailDTO) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal()).getUser();
            user.setIdx(me.getIdx());
        }
        return quizService.listCount(user);
    }
    @PostMapping("/quiz/send")
    @Operation(description = "퀴즈 보내기")
    public void quizSend(@RequestBody QuizSendDTO quizSendDTO){
        quizService.quizSend(quizSendDTO.getRecord());
    }



    @Data
    static class Idx{
        private Long idx;
    }
}
