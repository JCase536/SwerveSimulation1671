package drive;

import math.LinearAlgebraLib;
import math.Vector2D;

public class SwerveTrain {
	
	private SwerveWheel lF;
	private SwerveWheel lB;
	private SwerveWheel rF;
	private SwerveWheel rB;
	private Vector2D gyro;
	private static double gyroPosX = 0;
	private static double gyroPosY = 0;
	
	/*
	private final double length = 0.15;
	private final double width = 0.14;
	private final double diagonal = 0.2052;
	*/
	private final double length = 0.075;
	private final double width = 0.07;
	private final double diagonal = 0.0513;
	
	
	public SwerveTrain() {
		lF = new SwerveWheel(1, 5, -width, length, "lF");
		lB = new SwerveWheel(2, 6, -width, -length, "lB");
		rF = new SwerveWheel(3, 7, width, length, "rF");
		rB = new SwerveWheel(4, 8, width, -length, "rB");
		gyro = new Vector2D(0, 0);
	}
	
	public void drive(double axisTX, double axisTY, double axisR) {
		double tX = deadZone(axisTX, 0.0002);
		double tY = deadZone(axisTY, 0.0002);
		double r = deadZone(axisR, 0.0002);
		
		double gX = rF.getPosition().getX() - lF.getPosition().getX();
		double gY = rF.getPosition().getY() - lF.getPosition().getY();
		gyro.set(gX, gY);
		gyroPosX += tX;
		gyroPosY += tY;
		
		/*
		System.out.println((int)(lF.getVector().getAngle() * 180.0/Math.PI) + "     " + (int)(rF.getVector().getAngle() * 180.0/Math.PI) + "\n\n\n");
		System.out.println((int)(lB.getVector().getAngle() * 180.0/Math.PI) + "     " + (int)(rB.getVector().getAngle() * 180.0/Math.PI) + "\n\n\n\n\n\n\n\n\n");
		*/
		
		lF.setSetpoint(tX, r * getRotation(lF).getX(), tY, r * getRotation(lF).getY());
		lB.setSetpoint(tX, r * getRotation(lB).getX(), tY, r * getRotation(lB).getY());
		rF.setSetpoint(tX, r * getRotation(rF).getX(), tY, r * getRotation(rF).getY());
		rB.setSetpoint(tX, r * getRotation(rB).getX(), tY, r * getRotation(rB).getY());
		
		/*
		lF.setSetpointT(tX, tY);
		lB.setSetpointT(tX, tY);
		rF.setSetpointT(tX, tY);
		rB.setSetpointT(tX, tY);
		
		lF.setSetpointR(r * getRotation(lF).getX(), r * getRotation(lF).getY());
		lB.setSetpointR(r * getRotation(lB).getX(), r * getRotation(lB).getY());
		rF.setSetpointR(r * getRotation(rF).getX(), r * getRotation(rF).getY());
		rB.setSetpointR(r * getRotation(rB).getX(), r * getRotation(rB).getY());
		*/
		
		lF.update();
		lB.update();
		rF.update();
		rB.update();
		/*
		for(int i = 0; i < 50; i++) {
			System.out.println();
		}
		*/
	}
	
	public Vector2D getRotation(SwerveWheel wheel) {
		double x = (wheel.getPosition().getY() - gyroPosY);
		double y = -(wheel.getPosition().getX() - gyroPosX);
		return new Vector2D(x, y);
	}
	
	public double aTrigRange(double val) {
		return Math.min(Math.max(val, -1), 1);
	}
	
	public double deadZone(double val, double deadZone) {
		if(Math.abs(val) > deadZone) {
			return Math.max(Math.min(1.0, val), -1.0);
		}
		return 0.0;
	}
	
	public double getSquared(double val) {
		if(val < 0) {
			return -val * val;
		}
		return val * val;
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
	
	public static double getGyroPosX() {
		return gyroPosX;
	}
	
	public static double getGyroPosY() {
		return gyroPosY;
	}
	
}
