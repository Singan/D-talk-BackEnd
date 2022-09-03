package dtalk.controller;

import dtalk.domain.Rank;
import dtalk.service.RankService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rank")
public class RankController {
    private final RankService rankService;
    @GetMapping("/day")
    @Operation(description = "일간랭킹")
    public Rank dayRank(){
        rankService.dayRank();
        return null;
    }
}
