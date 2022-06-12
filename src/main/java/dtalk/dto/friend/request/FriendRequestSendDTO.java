package dtalk.dto.friend.request;

import dtalk.domain.CUTime;
import dtalk.domain.FriendRequest;
import dtalk.domain.User;
import dtalk.domain.status.FriendStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FriendRequestSendDTO {
    private Long userIdx;

    public FriendRequest getFriendRequest(User u){

        if(this.getUserIdx() == u.getIdx())
            throw new RuntimeException("자신을 요청할 수 없습니다.");
        User user = new User();
        user.setIdx(userIdx);
        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setCuTime(new CUTime(LocalDateTime.now()));
        friendRequest.setFriendStatus(FriendStatus.대기);
        friendRequest.setSendUser(u);
        friendRequest.setReceiveUser(user);

        return friendRequest;
    }
}
