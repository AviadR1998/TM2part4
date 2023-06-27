package com.example.tm2ex3;

public class UserPostJson {
    private String username;
    private String password;
    private String displayName;
    private String profilePic;

    public UserPostJson(String username, String password, String displayName, String profilePic){
        this.username = username;
        this.password = password;
        this.displayName = displayName;
        this.profilePic = profilePic;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getProfilePic() {
        return profilePic;
    }
}
