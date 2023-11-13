package org.example.Utils;

import java.util.HashMap;
import java.util.Map;

public class LockerUtil {
    public static int generateOpenCode () {
        int min = 0, max = 9999;
        return (min + (int)(Math.random() * ((max - min) + 1)));
    }
    public static String changeStatus(String currentStatus, String operation) {
        Map<String, String> statusTransition = new HashMap<>();
        statusTransition.put("deliverToWarehouse", "deliver to warehouse");
        statusTransition.put("reserveForDriver", "reserved for driver delivery");
        statusTransition.put("readyForPickup", "ready for customer to pick up");
        if (statusTransition.containsKey(operation)) {
            return statusTransition.get(operation);
        }
        return currentStatus;
    }
}
