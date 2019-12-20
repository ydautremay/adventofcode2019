package dyve.aoc.day.day18;

import java.util.HashMap;
import java.util.Map;

public class Key {

    String name;

    Map<Key, Integer> visibleKeys = new HashMap<>();

    Map<Door, Integer> visibleDoors = new HashMap<>();

    boolean taken = false;

    public Key(String name) {
        this.name = name;
    }
}
