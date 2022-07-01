package dtalk.controller;

import dtalk.domain.User;
import dtalk.dto.user.UserDetailDTO;
import dtalk.dto.user.UserResponseDTO;
import dtalk.service.FriendRequestService;
import dtalk.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping()
    public List<UserResponseDTO> friendList(){
        User me = ((UserDetailDTO) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        return UserResponseDTO.createUserResDto(friendService.friendList(me));
    }
    @GetMapping("/count")
    public Integer friendListCount(){
        User me = ((UserDetailDTO) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        return friendService.friendListCount(me);
    }
    @DeleteMapping
    public void friendDelete(@RequestBody Idx idx){
        User me = ((UserDetailDTO) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        User you = new User();
        you.setIdx(idx.idx);
        friendService.friendDelete(me,you);
    }
    static class Idx{
        private Long idx;
    }
}
