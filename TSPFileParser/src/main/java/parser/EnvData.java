package parser;

public class EnvData {

    private float env[][][];
    private short nbNodes;
    private int tspVersionNb;
    private float timeWindow[], initialTimeStamp;

    public EnvData(float[][][] env, short nbNodes, int tspVersionNb) {
        this.env = env;
        this.nbNodes = nbNodes;
        this.tspVersionNb = tspVersionNb;
    }

    public EnvData(float[][][] env, short nbNodes, int tspVersionNb, float[] timeWindow, float initialTimeStamp) {
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

    public float[] getTimeWindow() {
        return timeWindow;
    }

    public float getInitialTimeStamp() {
        return initialTimeStamp;
    }
}
