package model;

import java.util.Map;

public interface ITSPDisplayer {
    void displayNodes(Map<Short, String> nodes);
    void clearNodes();

    void displaySolution(int nbNodes, Short[] solution, long dist, boolean best);
    void clearSolutions();

    void addListener(ITSPDisplayerCallback l);
    void removeListener(ITSPDisplayerCallback l);
}
