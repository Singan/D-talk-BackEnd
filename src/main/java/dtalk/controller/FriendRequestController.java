package dtalk.controller;

import dtalk.domain.User;
import dtalk.dto.friend.request.FriendRequestResponseDTO;
import dtalk.dto.friend.request.FriendRequestSendDTO;
import dtalk.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/friend/request")
public class FriendRequestController {

    private final FriendService friendService;

    @PostMapping
    public Long friendSend(@RequestBody FriendRequestSendDTO friendRequestSendDTO){
        System.out.println(friendRequestSendDTO.getUserIdx());
        return friendService.friendSend(friendRequestSendDTO);
    }
    @GetMapping
    public List<FriendRequestResponseDTO> friendReceive(){
        return FriendRequestResponseDTO.createUserResDto(friendService.friendReceive());
    }
    @DeleteMapping
    public List<User> friendRefuse(Long idx){
        return friendService.friendReceive();
    }
    @PutMapping
    public List<User> friendAccept(Long idx){
        return friendService.friendReceive();
    }


}
