package com.epam.laboratory.entities.global;

import com.epam.laboratory.entities.user.User;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonRootName(value = "UserStore")
public class UserStore extends GlobalObject {

    private List<User> userList;

    @Override
    public List<User> getList() {
        if (userList == null) {
            userList = new ArrayList<>();
        }
        return this.userList;
    }

    @Override
    public void addObjectToList(Object object) {
        userList.add((User) object);
    }

    @Override
    public void setList(List<?> objects) {
        userList = (List<User>) objects;
    }

    @Override
    public String toString() {
        return "UserStore{" +
                "userList=" + userList +
                '}';
    }
}
