package math;

public class Vector2D extends Vector {
	
	private double x;
	private double y;
	private double angle;
	private double magnitude;
	
	
	public Vector2D(double x, double y) {
		super(new double[] {x, y});
		this.x = x;
		this.y = y;
		magnitude = Math.sqrt(x*x + y*y);
		if(x < 0) {
			if(y < 0) {
				angle = Math.atan(y/x) - Math.PI;
			} else {
				angle = Math.atan(y/x) + Math.PI;
			}
		} else {
			angle = Math.atan(y/x);
		}
	}
	
	public void set(double x, double y) {
		this.x = x;
		this.y = y;
		magnitude = Math.sqrt(x*x + y*y);
		if(x < 0) {
			if(y < 0) {
				angle = Math.atan(y/x) - Math.PI;
			} else {
				angle = Math.atan(y/x) + Math.PI;
			}
		} else {
			angle = Math.atan(y/x);
		}
	}
	
	public void debug() {
		System.out.println("X: " + x);
		System.out.println("Y: " + y);
		System.out.println("Mag: " + magnitude);
		System.out.println("Angle: " + angle * 180.0 / Math.PI);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getMagnitude() {
		return magnitude;
	}
	
	public double getAngle() {
		return angle;
	}
	
}
