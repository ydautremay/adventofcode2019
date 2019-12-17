package dyve.aoc.day.day14;

import java.util.HashMap;
import java.util.Map;

public class Stock extends HashMap<String, Long> {

    public Stock() {
        super();
    }

    public Stock(Map<? extends String, ? extends Long> m) {
        super(m);
    }
}
