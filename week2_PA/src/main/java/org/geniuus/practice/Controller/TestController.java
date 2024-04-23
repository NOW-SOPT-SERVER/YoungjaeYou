package org.geniuus.practice.Controller;

import lombok.extern.slf4j.Slf4j;
import org.geniuus.practice.Service.dto.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String test() {
        return "test API";
    }

    @GetMapping("/json")
    public ApiResponse jsonTest() {
        return ApiResponse.create("json test api content");
    }
}
