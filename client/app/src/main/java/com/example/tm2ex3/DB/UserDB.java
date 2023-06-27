package com.example.tm2ex3.DB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserDB {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String username;
    private String token;
    private String displayName;
    private String profilePic;

    public UserDB(String username, String token, String displayName, String profilePic){
        this.displayName = displayName;
        this.username = username;
        this.token = token;
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

    public int getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
