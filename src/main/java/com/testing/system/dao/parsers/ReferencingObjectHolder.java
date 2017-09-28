package com.testing.system.dao.parsers;

/**
 * Created by yuri on 23.09.17.
 */
public class ReferencingObjectHolder {
    private Object object;
    private ReferenceType referenceType;

    private ReferencingObjectHolder(Object object, ReferenceType referenceType) {
        this.object = object;
        this.referenceType = referenceType;
    }

    public static ReferencingObjectHolder oneToMany(Object object){
        return new ReferencingObjectHolder(object, ReferenceType.ONE_TO_MANY);
    }

    public static ReferencingObjectHolder manyToOne(Object object){
        return new ReferencingObjectHolder(object, ReferenceType.MANY_TO_ONE);
    }

    public Object getObject() {
        return object;
    }

    public boolean isOneToMany() {
        return referenceType == ReferenceType.ONE_TO_MANY;
    }

    public boolean isManyToOne() {
        return referenceType == ReferenceType.MANY_TO_ONE;
    }

    enum ReferenceType {
        MANY_TO_ONE, ONE_TO_MANY;
    }
}
