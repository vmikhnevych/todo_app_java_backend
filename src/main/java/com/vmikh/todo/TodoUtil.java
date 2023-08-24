package com.vmikh.todo;

import java.time.LocalDate;

public class TodoUtil {
    public static String addCurrentDateToName(String name) {
        return name + " - " + LocalDate.now();
    }

}
