package dtalk.domain;

import dtalk.domain.status.RecordStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "quiz_info")
public class Quiz {
    @Id
    @GeneratedValue
    @Column(name = "quiz_idx")
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

    @Column(name = "quiz_keyword")
    private String keyword;

    @Column(name = "quiz_thumb_img")
    private String thumbImg;

    @Column(columnDefinition = "TEXT",name = "quiz_detail")
    private String detail;

    @Column(name="quiz_sec")
    private Integer sec;


    @Column(name="quiz_remove")
    private Boolean remove;

    private CUTime cuTime;
}
