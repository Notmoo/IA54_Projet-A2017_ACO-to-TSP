package gui.graph.layout.impl;

import gui.graph.Graph;
import gui.graph.cell.Cell;
import gui.graph.layout.Layout;

import java.util.List;
import java.util.Random;

public class RandomLayout extends Layout {

    Graph graph;

    Random rnd = new Random();

    public RandomLayout(Graph graph) {

        this.graph = graph;

    }

    public void execute(double width, double height) {

        List<Cell> cells = graph.getAllCells();

        for (Cell cell : cells) {

            double x = rnd.nextDouble() * width*0.9 + width*0.05;
            double y = rnd.nextDouble() * height*0.9 + height*0.05;

            cell.relocate(x, y);

        }

    }

}