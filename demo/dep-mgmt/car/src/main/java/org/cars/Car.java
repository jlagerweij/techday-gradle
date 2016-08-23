package org.cars;

import java.util.Map;
import java.util.HashMap;

public class Car {

    Map locations = new HashMap();

    public Car() {
        locations.put("grocery", new Location());
    }

    public void driveTo(String location) {
        if (!locations.containsKey(location)) {
            throw new IllegalArgumentException("sorry, I don't know how to drive to " + location);
        }
    }

    private class Location {
    }
}