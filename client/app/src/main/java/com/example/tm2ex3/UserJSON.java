package com.example.tm2ex3;

public class UserJSON {
    private String username;
    private String displayName;
    private String profilePic;

    public UserJSON(String username, String displayName, String profilePic) {
        this.username = username;
        this.displayName = displayName;
        this.profilePic = profilePic;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public String getUsername() {
        return username;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
