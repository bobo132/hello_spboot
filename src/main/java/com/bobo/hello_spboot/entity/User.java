package com.bobo.hello_spboot.entity;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
@Accessors(chain = true)
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1332643889208978231L;

    private int id;
    private String username;
    private String email;
    private String password;
    private Date createTime;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + DateUtil.formatDateTime(createTime) +
                '}';
    }
}
