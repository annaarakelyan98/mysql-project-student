package com.students.operation;

public enum OperationType {
    INSERT_INTO,
    GET_ALL,
    GET_BY_ID,
    DELETE,
    DELETE_BY_ID,
    EXIT;

    public static OperationType findOperation(String opName) {
        for (OperationType o : values()) {
            if (o.name().equals(opName)) {
                return o;
            }
        }
        return null;
    }
}
