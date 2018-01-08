package parser;

import javafx.util.Pair;

import java.util.List;
import java.util.Map;

public class EnvData {

    private float env[][][];
    private short nbNodes;
    private int tspVersionNb;
    private Map<Short, List<Pair<Float,Float>>> timeWindow;
    private float initialTimeStamp;

    public EnvData(float[][][] env, short nbNodes, int tspVersionNb) {
        this.env = env;
        this.nbNodes = nbNodes;
        this.tspVersionNb = tspVersionNb;
    }

    public EnvData(float[][][] env, short nbNodes, int tspVersionNb, Map<Short, List<Pair<Float,Float>>> timeWindow, float initialTimeStamp) {
        this.env = env;
        this.nbNodes = nbNodes;
        this.tspVersionNb = tspVersionNb;
        this.timeWindow = timeWindow;
        this.initialTimeStamp = initialTimeStamp;
    }

    public float[][][] getEnv() {
        return env;
    }

    public short getNbNodes() {
        return nbNodes;
    }

    public int getTspVersionNb() {
        return tspVersionNb;
    }

    public Map<Short, List<Pair<Float,Float>>> getTimeWindow() {
        return timeWindow;
    }

    public float getInitialTimeStamp() {
        return initialTimeStamp;
    }
}
