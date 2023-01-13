package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/guest")
@Controller
public class GuestController {
    @GetMapping()
    public String guestUser() {
        return "guest";
    }
}
