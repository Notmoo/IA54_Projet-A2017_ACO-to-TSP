package gui.main_frame;

import gui.graph.Graph;
import gui.graph.cell.CellType;
import gui.graph.edge.EdgeType;
import gui.layout.Layout;
import gui.layout.impl.RandomLayout;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.Chart;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.ITSPDisplayer;
import model.ITSPDisplayerCallback;

import javax.swing.event.EventListenerList;
import java.util.Map;

public class MainFrame implements ITSPDisplayer{

    private BorderPane root;
    private Scene scene;

    private Graph graph;
    private Chart chart;

    private String currentScreen;
    private String requestedScreen;

    private EventListenerList listenerList;

    public MainFrame() {
        currentScreen = "";
        requestedScreen = "";

        listenerList = new EventListenerList();
    }

    private void init(){
        root = new BorderPane();

        scene = new Scene(root, 1024, 768);
        //scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        graph = new Graph();

        Layout layout = new RandomLayout(graph);
        layout.execute();


        requestedScreen = "graph";
        updateScreen();
    }

    private void updateScreen(){
        if(!currentScreen.equals(requestedScreen)){
            switch(requestedScreen){
                case "graph" :
                    Platform.runLater(()->{
                        root.setCenter(graph.getContent());
                    });
                    break;
                case "chart" :
                    Platform.runLater(()->{
                        //TODO add chart screen's content
                    });
                    break;
                default :
                    requestedScreen = "";
                    break;
            }
            currentScreen = requestedScreen;
        }
    }

    public void initAndShow(Stage primaryStage){

        init();

        primaryStage.setScene(scene);
        primaryStage.setTitle("Ant Colony Optimisation appliqué au problème du TSP - IA54 - A2017 - BOUCHEREAU/PROST");
        primaryStage.show();
    }

    @Override
    public void displayNodes(Map<Short, String> nodes) {
        for(short s : nodes.keySet()){
            graph.addCell(convertToCellTag(s, nodes.get(s)), CellType.SIMPLE);
        }
        graph.applyUpdate();
    }

    @Override
    public void clearNodes() {
        graph.clearCells();
    }

    @Override
    public void displaySolution(int nbNodes, Short[] solution, long dist, boolean best) {
        EdgeType type = best? EdgeType.BEST_SOLUTION : EdgeType.NORMAL;
        for(int i =1; i<nbNodes; i++){
            graph.addEdge(solution[i-1].toString(), solution[i].toString(), type);
        }
        graph.applyUpdate();

        if(best){
            //TODO update chart
        }
    }

    @Override
    public void clearSolutions() {
        graph.clearEdges();
    }

    @Override
    public void addListener(ITSPDisplayerCallback l) {
        listenerList.add(ITSPDisplayerCallback.class, l);
    }

    @Override
    public void removeListener(ITSPDisplayerCallback l) {
        listenerList.remove(ITSPDisplayerCallback.class, l);
    }

    private static String convertToCellTag(short cellId, String cellStr){
        return Short.toString(cellId);
    }
}
