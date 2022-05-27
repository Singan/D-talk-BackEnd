package dtalk.domain;

import dtalk.dto.UserDetailDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "user_info")
public class User extends UserDetailDTO {
    @Id
    @GeneratedValue
    @Column(name = "user_idx")
    private Long idx;
    @Column(name = "user_id",unique = true)
    private String id;
    @Column(name = "user_pw")
    private String pw;
    @Column(name = "user_nickname")
    private String nickname;
    @Column(name = "user_profile_img")
    private String profileImg;
    @Column(name = "user_bgm_status")
    private Integer bgmStatus;
    @Column(name = "user_remove")
    private Boolean remove;

    @Column(name = "user_role")
    private String role ;




    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<Quiz> quizList= new ArrayList();

    private CUTime cuTime;

    @Override
    public List<String> getRoles() {
        List<String> s = super.getRoles();
        s.add(this.role);
        return s;
    }

    @Override
    public String getPassword() {
        return this.getPw();
    }

    @Override
    public String getUsername() {
        return this.getId();
    }
}
