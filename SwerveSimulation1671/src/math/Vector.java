package math;

public class Vector {
	
	private int dimensions;
	private double magnitude;
	private double[] v;
	
	public Vector(double[] v) {
		this.v = v.clone();
		dimensions = v.length;
	}
	
	public void setComponent(int index, double val) {
		v[index] = val;
		magnitude = 0.0;
		for(int i = 0; i < dimensions; i++) {
			magnitude += v[i] * v[i];
		}
		magnitude = Math.sqrt(magnitude);
	}
	
	public double getComponent(int index) {
		return v[index];
	}
	
	public double getMagnitude() {
		return magnitude;
	}
	
	public double[] get() {
		return v;
	}
	
	public int getDimensions() {
		return dimensions;
	}
	
}
