package dyve.aoc.day.day18;

import java.util.HashMap;
import java.util.Map;

public class Door {

    String name;

    Map<Key, Integer> visibleKeys = new HashMap<>();

    Map<Door, Integer> visibleDoors = new HashMap<>();

    boolean open = false;

    public Door(String name) {
        this.name = name;
    }
}
