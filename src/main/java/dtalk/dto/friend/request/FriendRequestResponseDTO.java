package dtalk.dto.friend.request;

import dtalk.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@Data
public class FriendRequestResponseDTO {
    private Long idx;
    private String id;
    private String nickname;
    private String profileImg;
    public static List<FriendRequestResponseDTO> createUserResDto(List<User> userList) {
        List<FriendRequestResponseDTO> list= new ArrayList<>();
        for (User user : userList) {
            FriendRequestResponseDTO friendRequestResponseDTO = new FriendRequestResponseDTO(
                    user.getIdx(),
                    user.getId(),
                    user.getNickname(),
                    user.getProfileImg());
            list.add(friendRequestResponseDTO);

        }
        return list;
    }
}
