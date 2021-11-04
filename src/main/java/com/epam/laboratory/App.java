package com.epam.laboratory;

import com.epam.laboratory.systemObjects.workWithData.ConfigurationDataUsage;
import com.epam.laboratory.systemObjects.workWithData.loadData.JSONParser;
import com.epam.laboratory.systemObjects.workWithUser.Questioner;

public class App {
    public static void main(String[] args) throws Throwable {
        Questioner questioner = new Questioner();
        questioner.askUserTasks();
        //System.out.println(new JSONParser().parseObjectsFromJson(ConfigurationDataUsage.pathToUserStoreJsonFile));

    }
}
