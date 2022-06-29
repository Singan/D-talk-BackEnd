package dtalk.dto.friend.request;

import dtalk.domain.CUTime;
import dtalk.domain.FriendRequest;
import dtalk.domain.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@NotBlank
public class FriendRequestSendDTO {
    private Long userIdx;

    public FriendRequest getFriendRequest(User u){

        if(this.getUserIdx() == u.getIdx())
            throw new RuntimeException("자신을 요청할 수 없습니다.");
        User user = new User();
        user.setIdx(userIdx);
        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setCuTime(new CUTime(LocalDateTime.now()));
        friendRequest.setSendUser(u);
        friendRequest.setReceiveUser(user);

        return friendRequest;
    }
}
