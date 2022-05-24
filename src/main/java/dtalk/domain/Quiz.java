package dtalk.domain;

import dtalk.domain.status.RecordStatus;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
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
    private String thumb_img;

    @Column(columnDefinition = "TEXT",name = "quiz_detail")
    private String detail;

    @Column(name="quiz_sec")
    private Integer sec;


    @Column(name="quiz_remove")
    private Boolean remove;

    private CUTime cuTime;
}
