package dtalk.controller;

import dtalk.domain.Quiz;
import dtalk.domain.User;
import dtalk.dto.quiz.QuizListDTO;
import dtalk.dto.quiz.QuizSaveDto;
import dtalk.dto.quiz.QuizSendDTO;
import dtalk.dto.record.RecordDTO;
import dtalk.dto.user.UserDetailDTO;
import dtalk.dto.user.UserResponseDTO;
import dtalk.service.QuizService;
import dtalk.service.RecordService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quiz")
@Valid
public class QuizController {
    private final QuizService quizService;
    private final RecordService recordService;
    @PostMapping
    public Long save(@RequestBody QuizSaveDto quizSaveDto){
        return quizService.save(quizSaveDto);
    }

    @GetMapping
    @Operation(description = "퀴즈디테일")
    public String detail(@RequestParam(required = false) Long idx){
        return quizService.findQuiz(idx).getDetail();
    }
    @PutMapping
    @Operation(description = "게임 플레이")
    public Boolean playQuiz( @RequestBody RecordDTO recordDTO){
        Quiz quiz = quizService.findQuiz(recordDTO.getQuizIdx());
        User me = ((UserDetailDTO) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        boolean flag = false;
        if(recordDTO.getAnswer() ==quiz.getKeyword()){
            flag = true;
        };
        recordService.quizPlay(me,quiz,flag);
        return flag;
    }
    @GetMapping
    public List<QuizListDTO> myList(){
        User me = ((UserDetailDTO) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        List<Quiz> quizList = quizService.myList(me);
        List<QuizListDTO> responseList = new ArrayList<>();
        for (Quiz quiz: quizList) {
            responseList.add(QuizListDTO.getQuizListDTO(quiz));
        }
        return responseList;
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
    @PostMapping("/send")
    @Operation(description = "퀴즈 보내기")
    public void quizSend(@RequestBody QuizSendDTO quizSendDTO){
        quizService.quizSend(quizSendDTO.getRecord());
    }



    @Data
    static class Idx{
        private Long idx;
    }
}
