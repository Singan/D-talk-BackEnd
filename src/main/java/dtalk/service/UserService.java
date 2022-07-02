package dtalk.service;

import dtalk.domain.CUTime;
import dtalk.domain.User;
import dtalk.dto.user.UserSaveDTO;
import dtalk.dto.user.UserUpdateDTO;
import dtalk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Long save(UserSaveDTO userSaveDto){
        User u = userSaveDto.createUser();
        u.setCuTime(new CUTime(LocalDateTime.now()));
        validate(u);
        return userRepository.save(u);

    }

    public List<User> userList() {

        return userRepository.findAll();
    }

    public void validate(User user) {
        Long l = userRepository.findByUserCount(user.getId());
        if (l!=0)
            throw new IllegalStateException("이미 존재하는 회원입니다.");
    }
    public User findByUserId(String id){
        return userRepository.findByUserId(id);
    }
    public void UpdateUser(User user){


        userRepository.updateUser(user);
    }

}
