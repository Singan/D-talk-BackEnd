package dtalk.service;

import dtalk.domain.User;
import dtalk.dto.UserResponseDto;
import dtalk.dto.UserSaveDto;
import dtalk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Long save(UserSaveDto userSaveDto){
        User u = userSaveDto.createUser();

        validate(u);


        return userRepository.save(u);

    }

    public List<UserResponseDto> userList() {
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();
        for (User u : userRepository.findAll()) {
            userResponseDtoList.add(UserResponseDto.createUserResDto(u));
        }
        ;
        return userResponseDtoList;
    }

    public void validate(User user) {
        Long l = userRepository.findByUserCount(user.getId());
        if (l!=0)
            throw new IllegalStateException("이미 존재하는 회원입니다.");

    }

}
