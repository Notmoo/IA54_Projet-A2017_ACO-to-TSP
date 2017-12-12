package gui.graph.edge;

public enum EdgeType {

    NORMAL("edge_type_normal"),
    BEST_SOLUTION("egde_type_best_solution")
    ;

    private String type_id;

    EdgeType(String type_id) {
        this.type_id = type_id;
    }

    public String getType_id() {
        return type_id;
    }
}
