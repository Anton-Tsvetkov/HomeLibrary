package com.epam.laboratory.systemObjects.workWithData.parsers;

import com.epam.laboratory.exceptions.DataException;
import com.epam.laboratory.systemObjects.workWithData.ConfigurationDataUsage;
import com.epam.laboratory.workObjects.globalObjects.GlobalObject;
import com.epam.laboratory.workObjects.library.Author;
import com.epam.laboratory.workObjects.library.Book;
import com.epam.laboratory.workObjects.user.User;

import java.util.List;

public class GlobalObjectParser {

    public GlobalObject parseListAsGlobalObject(List<?> objects) throws DataException {
        if (objects.get(0) instanceof Author) {
            return parseAuthorListAsGlobalDataObject((List<Author>) objects);
        } else if (objects.get(0) instanceof Book) {
            return parseBookListAsGlobalDataObject((List<Book>) objects);
        } else if (objects.get(0) instanceof User) {
            return parseUserListAsGlobalDataObject((List<User>) objects);
        }
        throw new DataException("");
    }


    private GlobalObject parseAuthorListAsGlobalDataObject(List<Author> objects) {
        GlobalObject globalObject = new DataLoader().getDataFromFile(ConfigurationDataUsage.pathToLibraryJsonFile);
        globalObject.addObjectsToList(objects);
        return globalObject;
    }

    private GlobalObject parseBookListAsGlobalDataObject(List<Book> objects) {
        GlobalObject globalObject = new DataLoader().getDataFromFile(ConfigurationDataUsage.pathToLibraryJsonFile);
        globalObject.addObjectsToList(objects);
        return globalObject;
    }

    private GlobalObject parseUserListAsGlobalDataObject(List<User> objects) {
        GlobalObject globalObject = new DataLoader().getDataFromFile(ConfigurationDataUsage.pathToUserStoreJsonFile);
        globalObject.addObjectsToList(objects);
        return globalObject;
    }


}
