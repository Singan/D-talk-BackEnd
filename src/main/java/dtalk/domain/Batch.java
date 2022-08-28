package dtalk.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Batch {
    @Id
    @GeneratedValue
    @Column(name = "batch_idx")
    private Long idx;

    private CUTime cuTime;
}
