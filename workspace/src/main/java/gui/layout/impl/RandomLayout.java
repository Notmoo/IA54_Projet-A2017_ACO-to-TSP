package gui.layout.impl;

import gui.graph.Graph;
import gui.graph.cell.Cell;
import gui.layout.Layout;

import java.util.List;
import java.util.Random;

public class RandomLayout extends Layout {

    Graph graph;

    Random rnd = new Random();

    public RandomLayout(Graph graph) {

        this.graph = graph;

    }

    public void execute() {

        List<Cell> cells = graph.getAllCells();

        for (Cell cell : cells) {

            //TODO modifier les tailles pour fit la taille actuelle de la zone de graph
            double x = rnd.nextDouble() * 500;
            double y = rnd.nextDouble() * 500;

            cell.relocate(x, y);

        }

    }

}