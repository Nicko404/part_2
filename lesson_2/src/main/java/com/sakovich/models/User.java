package com.sakovich.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private long id;
    private String login;
    private String password;
    private String userName;

    public static int cal() {
        return 2 * 2;
    }

}
