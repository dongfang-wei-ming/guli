package com.example.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.oss.config.exception.GuliException;
import com.example.ucenter.entity.Member;
import com.example.ucenter.entity.vo.RegisterVo;
import com.example.ucenter.mapper.MemberMapper;
import com.example.ucenter.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.utils.JwtUtils;
import com.example.utils.MD5;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-01-10
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @SneakyThrows
    @Override
    public String login(Member member) {
        String phone = member.getMobile();
        String password = member.getPassword();
        if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(password))throw new GuliException(20001,"登录失败");

        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",phone);
        Member member1 = baseMapper.selectOne(wrapper);
        if(member1 == null) throw new GuliException(20001,"用户不存在");
        if(MD5.encrypt(password).equals(member1.getPassword()) && member1.getIsDisabled() ==0){
            return JwtUtils.getJwtToken(member1.getId(),member1.getNickname());
        }
        throw new GuliException(20001,"密码错误或者被封号 ");
    }

    @SneakyThrows
    @Override
    public boolean register(RegisterVo registerVo) {
        String code = registerVo.getCode();
        String nickname = registerVo.getNickname();
        String mobile = registerVo.getMobile();
        String password = registerVo.getPassword();
        if(StringUtils.isEmpty(code) ||
                StringUtils.isEmpty(nickname) ||
                StringUtils.isEmpty(mobile) ||
                StringUtils.isEmpty(password) ) throw new GuliException(20001,"注册失败");
        //判断手机号是否注册过
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile",mobile);
        Integer i = baseMapper.selectCount(queryWrapper);
        if( i != 0) throw new GuliException(20001,"手机已经注册过");

        if(!redisTemplate.opsForValue().get(mobile).equals(code))throw new GuliException(20001,"验证码错误");
        Member member = new Member();
        BeanUtils.copyProperties(registerVo,member);
        member.setPassword(MD5.encrypt(password));
        baseMapper.insert(member);
        return true;
    }

    @Override
    public Member getOpenIdMember(String openid) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid",openid);
        Member member = baseMapper.selectOne(queryWrapper);
        return member;
    }

    @Override
    public Integer getRegisterCountOneDay(String day) {
        Integer count = baseMapper.getRegisterCountOneDay(day);
        return count;
    }
}
