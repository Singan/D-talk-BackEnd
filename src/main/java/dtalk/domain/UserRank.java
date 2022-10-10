package dtalk.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@DiscriminatorValue("U")
@Entity
public class UserRank extends Rank{
    @JoinColumn(name = "rank_user")
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
}
