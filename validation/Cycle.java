package validation;

import java.util.*;

public class Cycle {
    private final int boardSize;
    private final Map<Integer, List<Integer>> graph;

    public Cycle(int boardSize) {
        this.boardSize = boardSize;
        this.graph = new HashMap<>();
    }

    public void addSnakeLadderOrCrocs(int start, int end) {
        graph.computeIfAbsent(start, k -> new ArrayList<>()).add(end);
    }

    public boolean hasCycle() {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> recursionStack = new HashSet<>();

        for (int cell = 1; cell <= boardSize * boardSize; cell++) {
            if (!visited.contains(cell) && hasCycleUtil(cell, visited, recursionStack)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasCycleUtil(int cell, Set<Integer> visited, Set<Integer> recursionStack) {
        visited.add(cell);
        recursionStack.add(cell);

        if (graph.containsKey(cell)) {
            for (int neighbor : graph.get(cell)) {
                if (!visited.contains(neighbor)) {
                    if (hasCycleUtil(neighbor, visited, recursionStack)) {
                        return true;
                    }
                } else if (recursionStack.contains(neighbor)) {
                    return true;
                }
            }
        }

        recursionStack.remove(cell);
        return false;
    }
}
