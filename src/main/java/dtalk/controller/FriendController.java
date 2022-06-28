package dtalk.controller;

import dtalk.domain.User;
import dtalk.dto.user.UserDetailDTO;
import dtalk.dto.user.UserResponseDTO;
import dtalk.service.FriendRequestService;
import dtalk.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @GetMapping()
    public List<UserResponseDTO> friendList(){
        User me = ((UserDetailDTO) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        return UserResponseDTO.createUserResDto(friendService.friendList(me));
    }
}
