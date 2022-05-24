package dtalk.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
@Getter
@Setter
@Entity
public class UserRank extends Rank{
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
}
