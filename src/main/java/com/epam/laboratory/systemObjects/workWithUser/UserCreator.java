package com.epam.laboratory.systemObjects.workWithUser;

import com.epam.laboratory.systemObjects.workWithData.ConfigurationDataUsage;
import com.epam.laboratory.systemObjects.workWithData.loadData.DataLoader;
import com.epam.laboratory.workObjects.user.User;
import com.epam.laboratory.workObjects.user.UserRights;

import java.util.ArrayList;
import java.util.List;

public class UserCreator {

    private final DataLoader DATA_LOADER = new DataLoader();

    public void registerNewUser(String username, String password) {
        List<User> userList = new ArrayList<>();
        userList.add(new User(username, password));
        DATA_LOADER.addNewObjectsInFile(ConfigurationDataUsage.pathToUserStoreJsonFile, userList);
    }

    private UserRights getUserStatusByUserName(String username) {

//        return UserStatus.ADMIN;
        return UserRights.USER;
    }


}
