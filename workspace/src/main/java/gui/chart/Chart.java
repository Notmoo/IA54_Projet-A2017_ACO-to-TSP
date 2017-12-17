package gui.chart;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class Chart {

    private XYChart.Series<Number, Number> series;
    private ChartModel model;
    private LineChart<Number, Number> lineChart;

    public Chart() {

        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Numéro d'itération");
        yAxis.setLabel("Distance");
        //creating the chart
        lineChart = new LineChart<>(xAxis,yAxis);

        lineChart.setTitle("Données globales");
        //defining a series
        series = new XYChart.Series<>();
        series.setName("Meilleures solutions");
        //populating the series with data

        lineChart.getData().add(series);

        model = new ChartModel();
        model.addListener(new ChartModel.IChartModelListener() {
            @Override
            public void onDataAdded(Integer iteration, Float distance) {
                addData(iteration, distance);
            }

            @Override
            public void onDataModified() {
                remakeSerie();
            }
        });

        remakeSerie();
    }

    private void addData(Integer iteration, Float distance) {
        series.getData().add(new XYChart.Data<>(iteration, distance));
    }

    private void remakeSerie(){
        Platform.runLater(()->series.getData().clear());
        for(Integer iterNb : model.getDatas().keySet()){
            Platform.runLater(()->series.getData().add(new XYChart.Data<>(iterNb,model.getDatas().get(iterNb))));
        }
    }

    public Node getContent() {
        return lineChart;
    }
}
