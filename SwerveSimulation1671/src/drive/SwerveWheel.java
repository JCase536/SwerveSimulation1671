package drive;

import math.Vector2D;

public class SwerveWheel {
	
	private Vector2D v;
	//private Motor motorT;
	//private Motor motorR;
	
	public SwerveWheel(int portT, int portR) {
		v = new Vector2D(0, 0);
		//motorT = new Motor(portT);
		//motorR = new Motor(portR);
	}
	
	public void set(double x, double y) {
		v.set(x, y);
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
	
	public Vector2D getVector() {
		return v;
	}
	
}
