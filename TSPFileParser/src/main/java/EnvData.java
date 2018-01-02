public class EnvData {

    private float env[][][];
    private short nbNodes;
    private int tspVersionNb;

    public EnvData(float[][][] env, short nbNodes, int tspVersionNb) {
        this.env = env;
        this.nbNodes = nbNodes;
        this.tspVersionNb = tspVersionNb;
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
}
