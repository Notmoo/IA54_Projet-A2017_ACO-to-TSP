package gui.main_frame;

import gui.chart.Chart;
import gui.graph.Graph;
import gui.graph.cell.CellType;
import gui.graph.edge.EdgeType;
import gui.graph.layout.Layout;
import gui.graph.layout.impl.RandomLayout;
import gui.toolbar.PropertyToolbar;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import interfaces.ITSPDisplayer;
import interfaces.ITSPDisplayerCallback;

import javax.swing.event.EventListenerList;
import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends Application implements ITSPDisplayer  {

    private static MainFrame INSTANCE = null;
    private static final String GRAPH_STRING_ID = "Graph", CHART_STRING_ID = "Courbe";

    public static MainFrame getInstance(){
        return INSTANCE;
    }

    public static void summonGui(){
        new Thread(Application::launch).start();
    }

    private BorderPane root;

    private Graph graph;
    private Chart chart;
    private PropertyToolbar toolbar;

    private String currentScreen;
    private String requestedScreen;

    private EventListenerList listenerList;

    public MainFrame() {
        INSTANCE = this;
        currentScreen = "";
        requestedScreen = "";

        listenerList = new EventListenerList();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initAndShow(primaryStage);
    }

    @Override
    public void stop(){
        Arrays.stream(listenerList.getListeners(ITSPDisplayerCallback.class))
                .forEach(ITSPDisplayerCallback::onGuiClose);
    }

    private void updateScreen(){
        if(!currentScreen.equals(requestedScreen)){
            switch(requestedScreen){
                case GRAPH_STRING_ID :
                    Platform.runLater(()->{
                        root.setCenter(graph.getContent());
                    });
                    break;
                case CHART_STRING_ID :
                    Platform.runLater(()->{
                        root.setCenter(chart.getContent());
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

        //Initialisation de du pane pricipal
        root = new BorderPane();

        //Initialisation du pane contenant le graph
        graph = new Graph();

        Layout layout = new RandomLayout(graph);
        layout.execute();

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

        Menu miscMenu = new Menu("Autres");
        CheckMenuItem propertiesMenuItem = new CheckMenuItem("Afficher les propriétés");
        propertiesMenuItem.setSelected(true);
        propertiesMenuItem.selectedProperty().addListener((obs, oldValue, newValue)->{
            if(newValue)
                root.setRight(toolbar.getContent());
            else
                root.setRight(null);
        });
        MenuItem importFileMenuItem = new MenuItem("Importer...");
        importFileMenuItem.setOnAction((event)->{
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Importer un fichier de configuration TSP");
            File selectedFile = fileChooser.showOpenDialog(new Stage());

            if(selectedFile!=null){
                Path path = selectedFile.toPath();
                Arrays.stream(listenerList.getListeners(ITSPDisplayerCallback.class))
                        .forEach(l->l.onFileInput(path));
            }
        });
        miscMenu.getItems().addAll(propertiesMenuItem, importFileMenuItem);

        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        menuBar.getMenus().addAll(navMenu, miscMenu);
        root.setTop(menuBar);

        //Affichage de l'écran par défaut
        requestedScreen = GRAPH_STRING_ID;
        updateScreen();

        //Initialisation de la scène et du stage JFX + affichage de la GUI
        //TODO revoir la taille de la fenêtre
        Scene scene = new Scene(root, 1024, 768);
        scene.getStylesheets().add("style.css");

        primaryStage.setScene(scene);
        primaryStage.setTitle("Ant Colony Optimisation appliqué au problème du TSP - IA54 - A2017 - BOUCHEREAU/PROST");
        primaryStage.show();
    }

    @Override
    public void setProperties(Map<String, String> properties) {
        Platform.runLater(()->{
            toolbar.setProperties(properties);
        });
    }

    @Override
    public void displayNodes(Map<Short, String> nodes) {
        graph.clearEdges();
        for(short s : nodes.keySet()){
            graph.addCell(convertToCellTag(s, nodes.get(s)), CellType.SIMPLE);
        }
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
    }

    @Override
    public void clearNodes() {
        Platform.runLater(()->{
            graph.clearCells();
            chart.clearChart();
        });
    }

    @Override
    public void displaySolution(int nbNodes, short[] solution, double dist) {
        graph.clearEdges();
        for(int i =1; i<nbNodes; i++){
            short start = (short)(solution[i - 1]+1);
            short end = (short)(solution[i]+1);
            graph.addEdge(Short.toString(start), Short.toString(end), EdgeType.BEST_SOLUTION);
        }

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
        }

        Platform.runLater(()->{
            graph.applyUpdate();
            chart.addNextDistance(bestDist);
        });
    }

    @Override
    public void clearSolutions() {
        Platform.runLater(()->{
            graph.clearEdges();
            chart.clearChart();
        });
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
