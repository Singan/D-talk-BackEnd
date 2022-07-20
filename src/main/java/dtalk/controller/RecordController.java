package dtalk.controller;

import dtalk.domain.Quiz;
import dtalk.domain.Record;
import dtalk.domain.User;
import dtalk.dto.record.RecordDTO;
import dtalk.dto.user.UserDetailDTO;
import dtalk.dto.user.UserResponseDTO;
import dtalk.service.QuizService;
import dtalk.service.RecordService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/record")

public class RecordController {
    private final QuizService quizService;
    private final RecordService recordService;
    @PutMapping
    @Operation(description = "게임 플레이")
    public void playQuiz( @RequestBody RecordDTO recordDTO){
        Quiz quiz = quizService.findQuiz(recordDTO.getQuizIdx());
    }
    @GetMapping
    @Operation(description = "유저의 좋아요 수")
    public void likeQuiz(@RequestBody FindRecord findRecord){
        User user = new User();
        user.setIdx(findRecord.getUserIdx());

    }
    @GetMapping("/recommend")
    @Operation(description = "퀴즈 좋아요")
    public void quizRecommend(@RequestBody FindRecord idx){
        User me = ((UserDetailDTO) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        Quiz quiz = new Quiz();
        quiz.setIdx(idx.quizIdx);
        recordService.quizRecommend(me,quiz);

    }
    @Data
    static class FindRecord{
        private Long userIdx;
        private Long quizIdx;
        private Long getUserIdx(){
            User me = ((UserDetailDTO) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal()).getUser();
            if(this.userIdx == null)
                userIdx = me.getIdx();

            return this.userIdx;
        }

    }
}