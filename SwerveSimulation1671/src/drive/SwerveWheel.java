package drive;

import math.LinearAlgebraLib;
import math.Vector2D;

public class SwerveWheel {
	
	private Vector2D v;
	private double x;
	private double y;
	//private Motor motorT;
	//private Motor motorR;
	
	public SwerveWheel(int portT, int portR, double x, double y) {
		v = new Vector2D(0, 0);
		this.x = x;
		this.y = y;
		//motorT = new Motor(portT);
		//motorR = new Motor(portR);
	}
	
	public void set(double x, double y) {
		v.set(x, y);
		this.x += x;
		this.y += y;
	}
	
	public void setSpeed(int speedT, int speedR) {
		//motorT.set(speedT);
		//motorR.set(speedR);
	}
	
	public void setTranslateSpeed(int speed) {
		//motorT.set(speed);
	}
	
	public void setRotateSpeed(int speed) {
		//motorR.set(speed);
	}
	
	public Vector2D getPosition() {
		return new Vector2D(x, y);
	}
	
	public Vector2D getVector() {
		return v;
	}
	
}
