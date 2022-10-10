package dtalk.domain;

import dtalk.domain.status.BatchType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "batch")
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "batch_idx")
    private Long idx;

    private CUTime cuTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "batch_type")
    private BatchType type;

    @OneToMany(mappedBy = "batch",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    List<Rank> rankList = new ArrayList<>();
}
