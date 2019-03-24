package drive;

import math.LinearAlgebraLib;
import math.Vector2D;

public class SwerveTrainBackup {
	
	private SwerveWheel lF;
	private SwerveWheel lB;
	private SwerveWheel rF;
	private SwerveWheel rB;
	private Vector2D gyro;
	
	private final double length = 30;
	private final double width = 28;
	private final double diagonal = 41.04;
	
	
	public SwerveTrainBackup() {
		lF = new SwerveWheel(1, 5, -0.14, 0.15);
		lB = new SwerveWheel(2, 6, -0.14, -0.15);
		rF = new SwerveWheel(3, 7, 0.14, 0.15);
		rB = new SwerveWheel(4, 8, 0.14, -0.15);
		gyro = new Vector2D(0, 0);
	}
	
	public void drive(double axisTX, double axisTY, double axisR) {
		double tX = deadZone(axisTX, 0.001);
		double tY = deadZone(axisTY, 0.001);
		double r = deadZone(axisR, 0.001);
		
		double gX = rF.getPosition().getX() - lF.getPosition().getX();
		double gY = rF.getPosition().getY() - lF.getPosition().getY();
		gyro.set(gX, gY);
		
		System.out.println((int)(lF.getVector().getAngle() * 180.0/Math.PI) + "     " + (int)(rF.getVector().getAngle() * 180.0/Math.PI) + "\n\n\n");
		System.out.println((int)(lB.getVector().getAngle() * 180.0/Math.PI) + "     " + (int)(rB.getVector().getAngle() * 180.0/Math.PI) + "\n\n\n\n\n\n\n\n\n");
		
		lF.set(tX + r / diagonal * (getRotation(lF).getX() * gX - getRotation(lF).getY() * gY) + getX(), tY + r / diagonal * (getRotation(lF).getX() * gY + getRotation(lF).getX() * gY) + getY());
		lB.set(tX + r / -diagonal * (getRotation(lB).getX() * gX - getRotation(lB).getY() * gY) - getX(), tY + r / diagonal * (getRotation(lB).getX() * gY + getRotation(lB).getX() * gY) + getY());
		rF.set(tX + r / diagonal * (getRotation(rF).getX() * gX - getRotation(rF).getY() * gY) + getX(), tY + r / -diagonal * (getRotation(rF).getX() * gY + getRotation(rF).getX() * gY) - getY());
		rB.set(tX + r / -diagonal * (getRotation(rB).getX() * gX - getRotation(rB).getY() * gY) - getX(), tY + r / -diagonal * (getRotation(rB).getX() * gY + getRotation(rB).getX() * gY) - getY());
		//System.out.println(gyro.getAngle());
	}
	
	public Vector2D getRotation(SwerveWheel wheel) {
		return new Vector2D(wheel.getPosition().getX() - getX(), wheel.getPosition().getY() - getY());
	}
	
	public double deadZone(double val, double deadZone) {
		if(Math.abs(val) > deadZone) {
			return val;
		}
		return 0.0;
	}
	
	public SwerveWheel getWheel(String wheel) {
		switch(wheel) {
		case "lF":
			return lF;
		case "lB":
			return lB;
		case "rF":
			return rF;
		case "rB":
			return rB;
		default:
			return null;
		}
	}
	
	public double getHeading() {
		return gyro.getAngle();
	}
	
	public double getX() {
		return gyro.getX();
	}
	
	public double getY() {
		return gyro.getY();
	}
	
}
