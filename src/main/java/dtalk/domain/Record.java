package dtalk.domain;

import dtalk.domain.status.RecordStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Getter
@Setter
@Table(name = "record_info")
public class Record {
    @Id
    @GeneratedValue
    @Column(name = "record_idx")
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_idx")
    private Quiz quiz;

    @Enumerated
    private RecordStatus recordStatus;

    @Column(name="record_sec")
    private Integer sec;

    @Column(name = "record_like")
    private Integer like;
    private CUTime cuTime;
}
