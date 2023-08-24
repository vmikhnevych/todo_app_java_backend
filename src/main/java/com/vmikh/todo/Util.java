package com.vmikh.todo;

import java.time.LocalDate;

public class Util {
    public static String addCurrentDateToName(String name) {
        return name + " - " + LocalDate.now();
    }

}
