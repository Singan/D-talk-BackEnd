package dtalk.domain;

import dtalk.domain.status.BatchType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Batch {
    @Id
    @GeneratedValue
    @Column(name = "batch_idx")
    private Long idx;

    private CUTime cuTime;


    @Enumerated(EnumType.STRING)
    @Column(name = "batch_type")
    private BatchType type;
}
