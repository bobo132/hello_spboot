package com.bobo.hello_spboot.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bobo.hello_spboot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

// mybatis-plus
@Repository
public interface UserMapper extends BaseMapper<User> {


}
