package com.epam.laboratory.workObjects.globalObjects;

import java.util.List;

public abstract class GlobalObject {

    protected List<?> list;

    public abstract List<?> getList();

    public abstract void addObjectsToList(List<?> objects);
}