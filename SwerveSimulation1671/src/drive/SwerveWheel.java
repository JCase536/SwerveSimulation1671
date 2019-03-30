package drive;

import math.LinearAlgebraLib;
import math.Vector2D;

public class SwerveWheel {
	
	private Vector2D setpoint;
	private Vector2D a;
	private Vector2D vT;
	private Vector2D vR;
	private Vector2D v;
	private double x;
	private double y;
	//private Motor motorT;
	//private Motor motorR;
	
	public SwerveWheel(int portT, int portR, double x, double y) {
		a = new Vector2D(0.001, 0.001);
		vT = new Vector2D(0.0, 0.0);
		vR = new Vector2D(0.0, 0.0);
		v = new Vector2D(0.0, 0.0);
		setpoint = new Vector2D(0.0, 0.0);
		this.x = x;
		this.y = y;
		//motorT = new Motor(portT);
		//motorR = new Motor(portR);
	}
	
	public void setT(double x, double y) {
		vT.set(x, y);
	}
	
	public void setR(double x, double y) {
		vR.set(0.5*x, 0.5*y);
	}
	
	public void setSetpoint(double x, double y) {
		setpoint.set(x, y);
		setT(vT.getX() - a.getX() * (vT.getX() - setpoint.getX()), vT.getY() - a.getY() * (vT.getY() - setpoint.getY()));
	}
	
	public void update() {
		v = LinearAlgebraLib.add(vT, vR);
		x += v.getX();
		y += v.getY();
	}
	
	public boolean inTolerance(double val, double setpoint, double tolerance) {
		return (val > (setpoint - tolerance) && val < (setpoint + tolerance));
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
	
	public Vector2D getVectorT() {
		return vT;
	}
	
	public Vector2D getVectorR() {
		return vR;
	}
	
	public Vector2D getVector() {
		return v;
	}
	
}
