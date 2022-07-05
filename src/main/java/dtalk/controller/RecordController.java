package dtalk.controller;

import dtalk.domain.Quiz;
import dtalk.domain.Record;
import dtalk.dto.record.RecordDTO;
import dtalk.dto.user.UserResponseDTO;
import dtalk.service.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/record")

public class RecordController {
    private final QuizService quizService;

    @PutMapping
    @Operation(description = "게임 플레이")
    public void playQuiz( @RequestBody RecordDTO recordDTO){
        Quiz quiz = quizService.findQuiz(recordDTO.getQuizIdx());


    }


}
