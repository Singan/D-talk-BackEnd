package dtalk.service;

import dtalk.domain.CUTime;
import dtalk.domain.FriendRequest;
import dtalk.domain.User;
import dtalk.domain.status.FriendStatus;
import dtalk.dto.friend.request.FriendRequestSendDTO;
import dtalk.dto.user.UserDetailDTO;
import dtalk.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendService {
    private final FriendRepository friendRepository;

    public Long friendSend(FriendRequestSendDTO friendRequestSendDTO){
        UserDetailDTO u =(UserDetailDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = new User();
        user.setIdx(friendRequestSendDTO.getUserIdx());
        if(friendRepository.friendCount(u.getUser(),user) != 0L)
            throw new RuntimeException("이미 친구신청을 하였습니다.");
        return friendRepository.friendSend(
                friendRequestSendDTO.getFriendRequest(u.getUser()) // 친구 리퀘스트 DTO 에서 친구 리퀘스트 객체로변환해줌
        );
    }

    public List<User> friendList(){
        UserDetailDTO u = (UserDetailDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return null;
    }
    public List<User> friendReceive(){
        UserDetailDTO u = (UserDetailDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("여기까지오네 1");
        return friendRepository.friendReceive(u.getUser());
    }
}
