package dyve.aoc.day.day18;

import java.util.HashSet;
import java.util.Set;

public class Graph {

    Set<Node> nodes = new HashSet<>();

    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }
}

