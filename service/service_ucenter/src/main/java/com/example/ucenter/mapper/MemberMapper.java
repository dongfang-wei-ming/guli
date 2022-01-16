package com.example.ucenter.mapper;

import com.example.ucenter.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2022-01-10
 */
@Mapper
public interface MemberMapper extends BaseMapper<Member> {

    Integer getRegisterCountOneDay(String day);
}
