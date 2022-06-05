package dtalk.controller;

import dtalk.dto.user.UserResponseDTO;
import dtalk.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/friend")
public class FriendController {

    private final FriendService friendService;

    @GetMapping("/recommend")
    public List<UserResponseDTO> friendRecommend(){

        return null;
    }
    @GetMapping("/send")
    public Long friendSend(Long idx){
        return friendService.sendFriend(idx);
    }

}
