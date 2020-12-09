package com.bobo.hello_spboot.entity;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Player implements Serializable {

    private static final long serialVersionUID = 1332643889208978231L;

    private int id;
    private String username;
    private String email;
    private String password;

}
