package gui.main_frame;

import gui.graph.Graph;
import gui.graph.cell.CellType;
import gui.graph.edge.EdgeType;
import gui.layout.Layout;
import gui.layout.impl.RandomLayout;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.Chart;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.ITSPDisplayer;
import model.ITSPDisplayerCallback;

import javax.swing.event.EventListenerList;
import java.util.Map;

public class MainFrame extends Application implements ITSPDisplayer  {

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

    @Override
    public void start(Stage primaryStage) throws Exception {
        initAndShow(primaryStage);
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

    private void initAndShow(Stage primaryStage){

        root = new BorderPane();

<<<<<<< Updated upstream
        scene = new Scene(root, 1024, 768);
        //scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

=======
        //Initialisation du pane contenant le graph
>>>>>>> Stashed changes
        graph = new Graph();

        Layout layout = new RandomLayout(graph);
        layout.execute();
<<<<<<< Updated upstream
=======

        //Initialisation du pane contenant les courbes
        chart = new Chart();

        //Initialisation de la barre de propriété latérale
        toolbar = new PropertyToolbar();
        root.setRight(toolbar.getContent());
        toolbar.addListener(((property, value) -> {
            Arrays.stream(listenerList.getListeners(ITSPDisplayerCallback.class))
                    .forEach(l->l.onPropertyChanged(property, value));
        }));

        //Initialisation de la barre de menu
        Menu navMenu = new Menu("Ecrans");
        ToggleGroup tGroup = new ToggleGroup();
        RadioMenuItem graphMenuItem = new RadioMenuItem(GRAPH_STRING_ID);
        graphMenuItem.setToggleGroup(tGroup);
        graphMenuItem.setSelected(true);
        graphMenuItem.setOnAction(event->{
            requestedScreen = GRAPH_STRING_ID;
            updateScreen();
        });
        RadioMenuItem chartMenuItem = new RadioMenuItem(CHART_STRING_ID);
        chartMenuItem.setOnAction(event->{
            requestedScreen = CHART_STRING_ID;
            updateScreen();
        });
        chartMenuItem.setToggleGroup(tGroup);
        navMenu.getItems().addAll(graphMenuItem, chartMenuItem);
>>>>>>> Stashed changes


        requestedScreen = "graph";
        updateScreen();

<<<<<<< Updated upstream
=======
        //Initialisation de la scène et du stage JFX + affichage de la GUI
        //TODO revoir la taille de la fenêtre
        Scene scene = new Scene(root, 1024, 768);
        scene.getStylesheets().add("style.css");

>>>>>>> Stashed changes
        primaryStage.setScene(scene);
        primaryStage.setTitle("Ant Colony Optimisation appliqué au problème du TSP - IA54 - A2017 - BOUCHEREAU/PROST");
        primaryStage.show();
    }

    @Override
    public void displayNodes(Map<Short, String> nodes) {
        for(short s : nodes.keySet()){
            graph.addCell(convertToCellTag(s, nodes.get(s)), CellType.SIMPLE);
        }
<<<<<<< Updated upstream
        graph.applyUpdate();
=======
        Platform.runLater(()->{
            graph.applyUpdate();
            new RandomLayout(graph).execute();
            chart.clearChart();
        });
    }

    @Override
    public void displayNodes(short nbNodes) {
        Map<Short, String> nodes = new HashMap<>();
        for(short i = 1; i<=nbNodes; i++){
            nodes.put(i, "Node n°"+i);
        }
        displayNodes(nodes);
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
        if(best){
            //TODO update chart
=======
        Platform.runLater(()->{
            graph.applyUpdate();
            chart.addNextDistance(dist);
        });
    }

    @Override
    public void displaySolution(int nbNodes, short[] bestSolution, double bestDist, short[] solution, double dist) {
        graph.clearEdges();
        for(int i =1; i<nbNodes; i++){
            short start = (short)(solution[i - 1]+1);
            short end = (short)(solution[i]+1);
            graph.addEdge(Short.toString(start), Short.toString(end), EdgeType.NORMAL);

            short startForBest = (short)(bestSolution[i - 1]+1);
            short endForBest = (short)(bestSolution[i]+1);
            graph.addEdge(Short.toString(startForBest), Short.toString(endForBest), EdgeType.BEST_SOLUTION);
>>>>>>> Stashed changes
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
