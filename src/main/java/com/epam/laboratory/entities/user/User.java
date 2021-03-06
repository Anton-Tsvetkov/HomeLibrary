package com.epam.laboratory.entities.user;

import com.epam.laboratory.entities.library.Bookmark;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.ArrayList;
import java.util.List;

@JsonRootName(value = "user")
public class User {

    @JsonProperty("username")
    protected String username;

    @JsonProperty("password")
    protected String password;

    @JsonProperty("userRights")
    protected UserRights userRights;

    @JsonProperty("userStatus")
    protected UserStatus userStatus;

    @JsonProperty("bookmarkList")
    protected List<Bookmark> bookmarkList;



    public User() {
    }

    public User(String login, String password) {
        this.username = login;
        this.password = password;
        this.userRights = UserRights.USER;
        this.userStatus = UserStatus.UNLOCKED;
        this.bookmarkList = new ArrayList<>();
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBookmarkList(List<Bookmark> bookmarkList) {
        this.bookmarkList = bookmarkList;
    }

    public void setUserRights(UserRights userRights) {
        this.userRights = userRights;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public String getUsername() {
        return username;
    }

    public List<Bookmark> getBookmarkList() {
        return bookmarkList;
    }

    public UserRights getUserRights() {
        return userRights;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userRights=" + userRights +
                ", userStatus=" + userStatus +
                ", bookmarkList=" + bookmarkList +
                '}';
    }
}
