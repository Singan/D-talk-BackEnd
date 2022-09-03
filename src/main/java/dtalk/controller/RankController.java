package dtalk.controller;

import dtalk.domain.Quiz;

import dtalk.domain.Rank;
import dtalk.dto.rank.RankDTO;
import dtalk.service.QuizService;
import dtalk.service.RankService;
import dtalk.service.RecordService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rank")
public class RankController {
    private final RankService rankService;
    private final RecordService recordService;
    private final QuizService quizService;
    @GetMapping("/day")
    @Operation(description = "일간랭킹")
    public List<RankDTO> dayRank(){
        List<Rank> rankList = rankService.dayRank();
        List<RankDTO> list = new ArrayList<>();
        for (Rank r: rankList) {
            Quiz quiz = quizService.findQuiz(r.getQuiz().getIdx());
            Long like = recordService.findRecordLike(quiz);

            list.add(RankDTO.createRankDTO(quiz.getThumbImg(),like));
        }
        Collections.sort(list);
        for (int i = 0 ; i<list.size();i++){
            list.get(i).setRank(i+1);
        }
        return list;
    }
}
