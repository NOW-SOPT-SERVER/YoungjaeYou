package org.sopt.carrotmarket.Service;

import lombok.RequiredArgsConstructor;
import org.sopt.carrotmarket.DTO.UserCreateDto;
import org.sopt.carrotmarket.Domain.Users;
import org.sopt.carrotmarket.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public String createUser(UserCreateDto userCreateDto) {
        Users user = Users.builder()
                .name(userCreateDto.name())
                .mannerTemperature(36.5)
                .build();

        return userRepository.save(user).getId().toString();
    }
}
