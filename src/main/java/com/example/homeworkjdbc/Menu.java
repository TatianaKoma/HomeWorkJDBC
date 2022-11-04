package com.example.homeworkjdbc;

public enum Menu {
    ADD_ADDRESS(1, "Add address"),
    GET_ADDRESS(2, "Get address by id"),
    DELETE_ADDRESS(3, "Delete address by id"),
    GET_ALL_ADDRESSES(4, "Get all addresses"),
    UPDATE_ADDRESS(5, "Update address by id"),
    ADD_CITIZEN(6, "Add citizen"),
    DELETE_CITIZEN(7, "Delete citizen by id"),
    GET_CITIZEN(8, "Get citizen by id"),
    GET_CITIZEN_BY_ADDRESS(9, "Get citizens by address id"),
    UPDATE_CITIZEN(10, "Update citizen by id"),
    EXIT(0, "Exit"),
    UNDEFINED(-1, "");
    private final int command;
    private final String description;

    Menu(int command, String description) {
        this.command = command;
        this.description = description;
    }

    public int getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }

    public static Menu getInstance(int command) {
        for (Menu v : values()) {
            if (v.getCommand() == command) {
                return v;
            }
        }
        return UNDEFINED;
    }

    public static String getMenuStr() {
        StringBuilder stringBuilder = new StringBuilder("What do you want to do:");
        for (Menu item : Menu.values()) {
            if (item.isUndefined()) {
                continue;
            }
            stringBuilder.append(System.lineSeparator());
            stringBuilder.append(item.getCommand()).append(") ").append(item.getDescription());
        }
        stringBuilder.append("\n").append("Your choice:");
        return stringBuilder.toString();
    }

    public boolean isUndefined() {
        return this == UNDEFINED;
    }
}
