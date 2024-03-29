package math;

public class Vector2D extends Vector implements Cloneable {
	
	private double x;
	private double y;
	private double angle;
	
	
	public Vector2D(double x, double y) {
		super(new double[] {x, y});
		this.x = x;
		this.y = y;
		if(x < 0) {
			if(y < 0) {
				angle = Math.atan(y/x) - Math.PI;
			} else {
				angle = Math.atan(y/x) + Math.PI;
			}
		} else if(x > 0) {
			angle = Math.atan(y/x);
		} else {
			if(y < 0) {
				angle = -Math.PI / 2.0;
			} else {
				angle = Math.PI / 2.0;
			}
		}
	}
	
	public Object clone() throws CloneNotSupportedException {  
		return super.clone();  
	}
	
	public void set(double x, double y) {
		setComponent(0, x);
		setComponent(1, y);
		this.x = x;
		this.y = y;
		if(x < 0) {
			if(y < 0) {
				angle = Math.atan(y/x) - Math.PI;
			} else {
				angle = Math.atan(y/x) + Math.PI;
			}
		} else if(x > 0) {
			angle = Math.atan(y/x);
		} else {
			if(y < 0) {
				angle = -Math.PI / 2.0;
			} else {
				angle = Math.PI / 2.0;
			}
		}
	}
	
	public void debug() {
		System.out.println("X: " + x);
		System.out.println("Y: " + y);
		System.out.println("Mag: " + getMagnitude());
		System.out.println("Angle: " + angle * 180.0 / Math.PI);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getAngle() {
		return angle;
	}
	
}
