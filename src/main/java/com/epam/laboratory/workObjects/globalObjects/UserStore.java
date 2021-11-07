package com.epam.laboratory.workObjects.globalObjects;

import com.epam.laboratory.workObjects.user.User;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonRootName(value = "UserStore")
public class UserStore extends GlobalObject {

    protected List<User> userList;

    @Override
    public List<User> getList() {
        if (userList == null) {
            userList = new ArrayList<>();
        }
        return this.userList;
    }

    @Override
    public void addObjectsToList(List<?> objects) {
        List<User> objectList = new ArrayList<>((Collection<? extends User>) objects);
        userList.addAll(objectList);
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
