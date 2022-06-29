package dtalk.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "friend_request" ,uniqueConstraints={
        @UniqueConstraint(name = "friend_request_unique",columnNames = {
                "friend_sender",
                "friend_receive"
        })})
public class FriendRequest {
    @Id
    @GeneratedValue
    @Column(name = "friend_request_idx")
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "friend_sender")
    private User sendUser;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "friend_receive")
    private User receiveUser;

    private CUTime cuTime;
}
