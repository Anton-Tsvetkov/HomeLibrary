package com.epam.laboratory;

import com.epam.laboratory.userCommunication.Questioner;

public class App {
    public static void main(String[] args) throws Throwable {
        Questioner questioner = new Questioner();
        questioner.askUserTasks();
        //System.out.println(new JSONParser().parseObjectsFromJson(ConfigurationDataUsage.pathToUserStoreJsonFile));

    }
}
