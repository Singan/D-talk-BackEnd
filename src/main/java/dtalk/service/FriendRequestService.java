package dtalk.service;

import dtalk.domain.User;
import dtalk.dto.friend.request.FriendRequestSendDTO;
import dtalk.dto.user.UserDetailDTO;
import dtalk.repository.FriendRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendRequestService {
    private final FriendRequestRepository friendRequestRepository;


    public Long friendSend(FriendRequestSendDTO friendRequestSendDTO){
        UserDetailDTO u =(UserDetailDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = new User();
        user.setIdx(friendRequestSendDTO.getUserIdx());
        if(friendRequestRepository.findRequest(u.getUser(),user) != 0L)
            throw new RuntimeException("이미 친구신청을 하였습니다.");
        return friendRequestRepository.friendSend(
                friendRequestSendDTO.getFriendRequest(u.getUser()) // 친구 리퀘스트 DTO 에서 친구 리퀘스트 객체로변환해줌
        );
    }


    public List<User> friendReceive(User me){

        return friendRequestRepository.friendReceive(me);
    }
    public void friendRequestDelete(User user,User me){

        friendRequestRepository.friendRequestDelete(user,me);
    }
}
