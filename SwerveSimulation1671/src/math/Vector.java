package math;

public class Vector {
	
	private int dimensions;
	private double[] v;
	
	public Vector(double[] v) {
		this.v = v.clone();
		dimensions = v.length;
	}
	
	public double getComponent(int index) {
		return v[index];
	}
	
	public double[] get() {
		return v;
	}
	
	public int getDimensions() {
		return dimensions;
	}
	
}
