package drive;

import math.LinearAlgebraLib;
import math.Vector2D;

public class SwerveWheel {
	
	private Vector2D setpoint;
	private Vector2D setpointT;
	private Vector2D setpointR;
	private Vector2D a;
	private Vector2D aT;
	private double aR;
	private Vector2D vT;
	private Vector2D vR;
	private Vector2D v;
	private double x;
	private double y;
	private String id;
	private boolean useAccel = false;;
	private boolean useAccelT = true;
	private boolean useAccelR = false;
	//private Motor motorT;
	//private Motor motorR;
	
	public SwerveWheel(int portT, int portR, double x, double y, String id) {
		a = new Vector2D(0.001, 0.001);
		aT = new Vector2D(0.001, 0.001);
		aR = 0.001;
		vT = new Vector2D(0.0, 0.0);
		vR = new Vector2D(0.0, 0.0);
		v = new Vector2D(0.0, 0.0);
		setpoint = new Vector2D(0.0, 0.0);
		setpointT = new Vector2D(0.0, 0.0);
		setpointR = new Vector2D(0.0, 0.0);
		this.x = x;
		this.y = y;
		this.id = id;
		//motorT = new Motor(portT);
		//motorR = new Motor(portR);
	}
	
	public void setT(double x, double y) {
		vT.set(x, y);
	}
	
	public void setR(double x, double y) {
		vR.set(x, y);
	}
	
	public void setSetpoint(double xT, double xR, double yT, double yR) {
		if(useAccel) {
			double rMag = xR * xR + yR * yR;
			setpoint.set(xT + 3.16 * xR, yT + 3.16 * yR);
			v.set(v.getX() - a.getX() * (v.getX() - setpoint.getX()), v.getY() - a.getY() * (v.getY() - setpoint.getY()));
			/*setpointT.set(xT, yT);
			setT(vT.getX() - aT.getX() * (vT.getX() - setpointT.getX()), vT.getY() - aT.getY() * (vT.getY() - setpointT.getY()));
			
			setpointR.set(xR, yR);
			//aR.set(0.00001 * (getPosition().getX() - SwerveTrain.getGyroPosX()), 0.00001 * (getPosition().getY() - SwerveTrain.getGyroPosY()));
			if(xR != 0.0) {
				if(vR.getX() != 0.0) {
					setR(vR.getX() - rMag * Math.cos(aR * (setpointR.getAngle() - vR.getAngle())), vR.getY() - rMag * Math.sin(aR * (setpointR.getAngle() - vR.getAngle())));
				} else {
					setR(xR, yR);
					//setR(vR.getX() - rMag * Math.cos(aR * (setpointR.getAngle() - vR.getAngle())), vR.getY() - rMag * Math.sin(aR * (setpointR.getAngle() - vR.getAngle())));
				}
			}
			v = LinearAlgebraLib.add(vT, vR);
			*/
		} else {
			v.set(xT + 2.0 * xR, yT + 2.0 * yR);
		}
	}
	
	public void setSetpointT(double x, double y) {
		if(useAccelT) {
			setpointT.set(x, y);
			setT(vT.getX() - aT.getX() * (vT.getX() - setpointT.getX()), vT.getY() - aT.getY() * (vT.getY() - setpointT.getY()));
		} else {
			setT(x, y);
		}
	}
	
	public void setSetpointR(double x, double y) {
		if(useAccelR) {
			setpointR.set(x, y);
			//aR.set(0.001 * (getPosition().getY() - SwerveTrain.getGyroPosY()), -0.001 * (getPosition().getX() - SwerveTrain.getGyroPosX()));
			//setR(vR.getX() - aR.getX() * (vR.getX() - setpointR.getX()), vR.getY() - aR.getY() * (vR.getY() - setpointR.getY()));
		} else {
			setR(x, y);
		}
	}
	
	public void update() {
		/*
		try {
			v = (Vector2D) LinearAlgebraLib.add(vT, vR).clone();
		} catch(CloneNotSupportedException c){}
		*/
		if(!useAccel) {
			//v = LinearAlgebraLib.add(vT, vR);
		}
		x += v.getX();
		y += v.getY();
		//System.out.println(id + " M: " + v.getMagnitude());
		//System.out.println(id + " T: " + vT.getMagnitude());
		//System.out.println(id + " R: " + vR.getAngle());
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
