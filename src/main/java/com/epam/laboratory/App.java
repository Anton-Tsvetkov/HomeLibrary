package com.epam.laboratory;

import com.epam.laboratory.userCommunication.Questioner;
import com.epam.laboratory.util.parsers.CSVParser;

public class App {
    public static void main(String[] args) throws Throwable {
        Questioner questioner = new Questioner();
        questioner.askUserTasks();

        //new CSVParser().showCSVFileData();

    }
}
