package dtalk.service;

import dtalk.domain.CUTime;
import dtalk.domain.Friend;
import dtalk.domain.User;
import dtalk.domain.status.FriendStatus;
import dtalk.dto.user.UserDetailDTO;
import dtalk.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FriendService {
    private final FriendRepository friendRepository;

    public Long sendFriend(Long idx){
        UserDetailDTO u =(UserDetailDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(idx == u.getIdx())
            throw new RuntimeException("자신을 요청할 수 없습니다.");
        User user = new User();
        user.setIdx(idx);
        Friend friend = new Friend();
        friend.setCuTime(new CUTime(LocalDateTime.now()));
        friend.setFriendStatus(FriendStatus.대기);
        friend.setSendUser(u.getUser());
        friend.setReceiveUser(user);
        return friendRepository.friendSend(friend);
    }


}
