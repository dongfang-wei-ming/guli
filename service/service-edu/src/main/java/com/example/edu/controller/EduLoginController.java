package com.example.edu.controller;


import com.example.utils.R;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eduservice/user")

public class EduLoginController {
    //login
    @PostMapping("login")
    public R login() {
        return R.success().data("token","admin");
    }
    //info
    @GetMapping("info")
    public R info() {
        return R.success().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
