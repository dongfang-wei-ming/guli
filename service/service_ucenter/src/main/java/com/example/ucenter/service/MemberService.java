package com.example.ucenter.service;

import com.example.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ucenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-01-10
 */
public interface MemberService extends IService<Member> {

    String login(Member member);

    boolean register(RegisterVo registerVo);

    Member getOpenIdMember(String openid);

    Integer getRegisterCountOneDay(String day);
}
