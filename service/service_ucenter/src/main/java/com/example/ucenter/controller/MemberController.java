package com.example.ucenter.controller;


import com.example.ucenter.entity.Member;
import com.example.ucenter.entity.vo.RegisterVo;
import com.example.ucenter.service.MemberService;
import com.example.utils.JwtUtils;
import com.example.utils.R;
import com.example.utils.orderVo.MemberOrder;
import jdk.nashorn.internal.ir.RuntimeNode;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-01-10
 */
@RestController
@RequestMapping("/ucenter/member")

public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping("login")
    public R login(@RequestBody Member member){
        String token= memberService.login(member);
        return R.success().data("token",token);
    }

    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo){
        boolean isOK =memberService.register(registerVo);
        return R.success();
    }

    @GetMapping("getUserInfo")
    public R getInfo(HttpServletRequest request){
        String id = JwtUtils.getMemberIdByJwtToken(request);
        Member byId = memberService.getById(id);
        return R.success().data("userInfo",byId);
    }
    @GetMapping("getUserInfo/{id}")
    public R getUserInfo(@PathVariable String id){
        Member byId = memberService.getById(id);
        return R.success().data("username",byId.getNickname())
                .data("userAvatar",byId.getAvatar());
    }

    @PostMapping("getUserInfoOrder/{id}")
    public MemberOrder getUserInfoOrder(@PathVariable String id){
        Member byId = memberService.getById(id);
        MemberOrder memberOrder = new MemberOrder();
        BeanUtils.copyProperties(byId,memberOrder);
        return memberOrder;
    }

    @GetMapping("getRegisterCount/{day}")
    public Integer getRegisterCount(@PathVariable String day){
        Integer count = memberService.getRegisterCountOneDay(day);
        return count;
    }
}

