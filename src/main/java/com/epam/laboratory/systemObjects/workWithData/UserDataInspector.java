package com.epam.laboratory.systemObjects.workWithData;

import com.epam.laboratory.systemObjects.workWithData.parsers.DataLoader;
import com.epam.laboratory.workObjects.globalObjects.UserStore;
import com.epam.laboratory.workObjects.user.User;

import java.util.List;

public class UserDataInspector {

    private final DataLoader DATA_LOADER = new DataLoader();
    private List<User> userList = (List<User>) DATA_LOADER.getDataFromFile(ConfigurationDataUsage.pathToUserStoreJsonFile);

    public boolean isUserExists(String username) {
        UserStore userStore = (UserStore) DATA_LOADER.getDataFromFile(ConfigurationDataUsage.pathToUserStoreJsonFile);
        for (User user : userStore.getList()) {
            if (user.getUsername().equals(username)) {
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
