package dtalk.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@DiscriminatorValue("Q")
public class QuizRank extends Rank{
    @JoinColumn(name = "rank_quiz")
    @OneToOne(fetch = FetchType.LAZY)
    private Quiz quiz;
}
