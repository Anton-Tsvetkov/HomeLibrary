package com.epam.laboratory.systemObjects.workWithData;

import com.epam.laboratory.systemObjects.workWithData.ConfigurationDataUsage;
import com.epam.laboratory.systemObjects.workWithData.loadData.DataLoader;
import com.epam.laboratory.workObjects.user.User;

import java.util.ArrayList;
import java.util.List;

public class UserDataInspector {

    private final DataLoader DATA_LOADER = new DataLoader();
    private List<User> userList = (List<User>) DATA_LOADER.getDataFromFile(ConfigurationDataUsage.pathToUserStoreJsonFile);

    public boolean isUserExists(String username) {
        List<?> userList = DATA_LOADER.getDataFromFile(ConfigurationDataUsage.pathToUserStoreJsonFile);
        for (Object user : userList) {
            if (((User) user).getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public int getIndexOfUserInListByUserName(List<User> users, String username){
        for (int i = 0; i < users.size(); i++){
            if(users.get(i).getUsername().equals(username)){
                return i;
            }
        }
        return -1;
    }

    public boolean isUserDataCorrect(String username, String password) {
        return userList.get(getIndexOfUserInListByUserName(userList, username)).getPassword().equals(password);
    }

}
