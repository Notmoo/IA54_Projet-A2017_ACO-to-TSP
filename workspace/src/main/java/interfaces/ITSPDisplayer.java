package interfaces;

import java.util.Map;

public interface ITSPDisplayer {
    void setProperties(Map<String, String> properties);

    void displayNodes(Map<Short, String> nodes);
    void displayNodes(short nbNodes);
    void clearNodes();

    void displaySolution(int nbNodes, Short[] solution, long dist, boolean best);
    void clearSolutions();

    void addListener(ITSPDisplayerCallback l);
    void removeListener(ITSPDisplayerCallback l);
}
