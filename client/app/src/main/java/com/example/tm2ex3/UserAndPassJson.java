package com.example.tm2ex3;

public class UserAndPassJson {
    private String username;
    private String password;

    public UserAndPassJson(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
