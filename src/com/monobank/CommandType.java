package com.monobank;

public enum CommandType {
    REGISTRATION("REG"),
    CREATE_POST_OFFICE("CREATE_POST_OFFICE"),
    CREATE_PARCEL_SEND("CREATE_PARCEL_SEND");

    private final String commandType;

    CommandType(String commandType) {
        this.commandType = commandType;
    }

    public String getCommandType() {
        return commandType;
    }

    public static CommandType getCommandTypeOfString(String s) {
        for (CommandType type : values()) {
            if(type.getCommandType().equals(s)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum found with command type: [" + s + "]");
    }
}
