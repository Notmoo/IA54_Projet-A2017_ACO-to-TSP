package gui.graph;

import gui.graph.cell.Cell;
import gui.graph.cell.CellType;
import gui.graph.edge.EdgeType;
import gui.graph.layout.CellLayer;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

import java.util.List;

public class Graph {

    private GraphModel model;

    private Group canvas;

    private ScrollPane scrollPane;

    GraphMouseGestures mouseGestures;

    /**
     * the pane wrapper is necessary or else the scrollpane would always align
     * the top-most and left-most child to the top and left eg when you drag the
     * top child down, the entire scrollpane would move down
     */
    CellLayer cellLayer;

    public Graph() {

        this.model = new GraphModel();

        canvas = new Group();
        cellLayer = new CellLayer();

        canvas.getChildren().add(cellLayer);

        mouseGestures = new GraphMouseGestures(this);

        //scrollPane = new ZoomableScrollPane(canvas);
        scrollPane = new ScrollPane(canvas);

        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

    }

    public Pane getCellLayer() {
        return this.cellLayer;
    }

    public void applyUpdate() {

        // add components to graph pane
        getCellLayer().getChildren().addAll(model.getAddedEdges());
        getCellLayer().getChildren().addAll(model.getAddedCells());

        // remove components from graph pane
        getCellLayer().getChildren().removeAll(model.getRemovedCells());
        getCellLayer().getChildren().removeAll(model.getRemovedEdges());

        // enable dragging of cells
        for (Cell cell : model.getAddedCells()) {
            mouseGestures.makeDraggable(cell);
        }

        // every cell must have a parent, if it doesn't, then the graphParent is
        // the parent
        model.attachOrphansToGraphParent(model.getAddedCells());

        // remove reference to graphParent
        model.disconnectFromGraphParent(model.getRemovedCells());

        // merge added & removed cells with all cells
        model.merge();
    }

    public double getScale() {
        return 1;
        //return this.scrollPane.getScaleValue();
    }

    public void addCell(String cell_b, CellType type) {
        model.addCell(cell_b, type);
    }

    public void addEdge(String cell_b, String cell_c, EdgeType type) {
        model.addEdge(cell_b, cell_c, type);
    }

    public List<Cell> getAllCells() {
        return model.getAllCells();
    }

    public void clearCells(){model.removeAllCells();}

    public void clearEdges(){model.removeAllEdges();}

    public Node getContent() {
        return this.scrollPane;
    }
}