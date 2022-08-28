package dtalk.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name="rank")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public  class Rank {
    @Id
    @GeneratedValue
    @Column(name = "rank_idx")
    private Long idx;

    @Column(name = "rank_num")
    private Integer rank;

    @JoinColumn(name = "rank_quiz")
    @OneToOne(fetch = FetchType.LAZY)
    private Quiz quiz;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "batch_idx")
    private Batch batch;
}
