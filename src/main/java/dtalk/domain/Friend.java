package dtalk.domain;

import dtalk.domain.status.FriendStatus;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "friend_info")
public class Friend {
    @Id
    @GeneratedValue
    @Column(name = "friend_idx")
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friend_sender")
    private User sendUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friend_reciver")
    private User reciverUser;

    @Enumerated
    @Column(name = "friend_status")
    private FriendStatus friendStatus;

    private CUTime cuTime;
}
