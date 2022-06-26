package dtalk.controller;

import dtalk.domain.User;
import dtalk.dto.friend.request.FriendRequestResponseDTO;
import dtalk.dto.friend.request.FriendRequestSendDTO;
import dtalk.dto.user.UserDetailDTO;
import dtalk.service.FriendRequestService;
import dtalk.service.FriendService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/friend/request")
public class FriendRequestController {

    private final FriendRequestService friendRequestService;
    private final FriendService friendService;

    @PostMapping
    public Long friendSend(@RequestBody FriendRequestSendDTO friendRequestSendDTO){

        return friendRequestService.friendSend(friendRequestSendDTO);
    }
    @GetMapping
    public List<FriendRequestResponseDTO> friendReceive(){
        User me = ((UserDetailDTO) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        return FriendRequestResponseDTO.createUserResDto(friendRequestService.friendReceive(me));
    }
    @DeleteMapping
    public void friendAction(@RequestBody Action action){
        User me = ((UserDetailDTO) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        if(action.key ==0)
            friendService.addFriend(me,action.getUser());


            friendRequestService.friendRequestDelete(me,action.getUser());
    }
    @Data
    static class Action{
        private Long idx;
        private Integer key;
        public User getUser(){
            User user = new User();
            user.setIdx(idx);
            return user;
        }
    }
}
