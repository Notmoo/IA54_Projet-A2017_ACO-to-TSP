package gui.graph.cell.impl;

import gui.graph.cell.Cell;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class SimpleCell extends Cell {
    public SimpleCell(String cellId) {
        super(cellId);

        StackPane view = new StackPane();

        Rectangle base = new Rectangle( 50,50);
        base.setStroke(Color.DODGERBLUE);
        base.setFill(Color.DODGERBLUE);

        Text text = new Text(cellId);

        view.getChildren().addAll(base, text);

        setView( view);

    }
}
