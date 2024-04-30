package org.sopt.carrotmarket.Controller;

import lombok.RequiredArgsConstructor;
import org.sopt.carrotmarket.DTO.UserCreateDto;
import org.sopt.carrotmarket.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    @PostMapping
    public ResponseEntity createUser(@RequestBody UserCreateDto userCreateDto) {
        return ResponseEntity.created(URI.create(userService.createUser(userCreateDto))).build();
    }
}
