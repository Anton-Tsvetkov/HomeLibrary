package com.epam.laboratory.entities.global;

import java.util.List;

public abstract class GlobalObject {

    protected List<?> list;

    public abstract List<?> getList();

    public abstract void addObjectsToList(List<?> objects);

    public abstract void setList(List<?> objects);
}
