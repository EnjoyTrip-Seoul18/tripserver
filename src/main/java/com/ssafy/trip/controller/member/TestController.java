package com.ssafy.trip.controller.member;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {
    @GetMapping("/test1")
    public void test1(@RequestAttribute("userId") String userId , HttpServletRequest httpReq) {
        System.out.println(userId+"zz");
        System.out.println("TEST1");
    }

    //userId 추출이 필요한경우 RequestAttribute로 꺼내어 사용할 것
    @GetMapping("/test2")
    public void test2(@RequestAttribute("userId") String userId) {
        System.out.println(userId+"ZZ");
        System.out.println("TEST2");
    }
}
