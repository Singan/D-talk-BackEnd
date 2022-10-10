package dtalk.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name="rank_info")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "rtype")
public  class Rank {
    @Id
    @Column(name="rank_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "batch_idx")
    private Batch batch;

    @Column(name = "rank_like")
    private Long like;
}
