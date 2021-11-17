package com.epam.laboratory.entities.global;

import java.util.List;

public abstract class GlobalObject {

    private List<?> list;

    public abstract List<?> getList();

    public abstract void addObjectToList(Object object);

    public abstract void setList(List<?> objects);
}
