package dtalk.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "friend" ,uniqueConstraints={
        @UniqueConstraint(name = "friend_unique",columnNames = {
                "friend_me",
                "friend_you"
        })})
public class Friend {
    @Id
    @GeneratedValue
    @Column(name = "friend_idx")
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "friend_me")
    private User me;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "friend_you")
    private User you;

    private CUTime cuTime;
}
