package com.utbm.ia54.aco.java;

public final class ArrayFactory {

	private ArrayFactory() {}
	
	public static double[][][] newEnvDoubleMatrix(short nbNodes){
		return new double[nbNodes][nbNodes][2];
	}
	
	public static float[][][] newEnvFloatMatrix(short nbNodes){
		return new float[nbNodes][nbNodes][2];
	}
	
	public static boolean[] newBooleanArray(short size) {
		return new boolean[size];
	}
	
	public static short[] newShortArray(short size) {
		return new short[size];
	}
	
	public static double[] newDoubleArray(short size) {
		return new double[size];
	}
}
