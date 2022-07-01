package dtalk.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserLoginDTO {
    @NotBlank(message = "아이디는 빌 수 없습니다.")
    private String id;
    @NotBlank(message = "패스워드는 빌 수 없습니다.")
    private String pw;
}
