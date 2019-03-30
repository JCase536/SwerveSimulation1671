package drive;

import math.LinearAlgebraLib;
import math.Vector2D;

public class SwerveTrain {
	
	private SwerveWheel lF;
	private SwerveWheel lB;
	private SwerveWheel rF;
	private SwerveWheel rB;
	private Vector2D gyro;
	private double gyroPosX = 0;
	private double gyroPosY = 0;
	
	/*
	private final double length = 30.0;
	private final double width = 28.0;
	private final double diagonal = 41.04;
	*/
	private final double length = 0.15;
	private final double width = 0.14;
	private final double diagonal = 0.2052;
	
	
	public SwerveTrain() {
		lF = new SwerveWheel(1, 5, -width, length);
		lB = new SwerveWheel(2, 6, -width, -length);
		rF = new SwerveWheel(3, 7, width, length);
		rB = new SwerveWheel(4, 8, width, -length);
		gyro = new Vector2D(0, 0);
	}
	
	public void drive(double axisTX, double axisTY, double axisR) {
		double tX = deadZone(axisTX, 0.0001);
		double tY = deadZone(axisTY, 0.0001);
		double r = deadZone(axisR, 0.0001);
		
		double gX = rF.getPosition().getX() - lF.getPosition().getX();
		double gY = rF.getPosition().getY() - lF.getPosition().getY();
		gyro.set(gX, gY);
		gyroPosX += tX;
		gyroPosY += tY;
		
		/*
		System.out.println((int)(lF.getVector().getAngle() * 180.0/Math.PI) + "     " + (int)(rF.getVector().getAngle() * 180.0/Math.PI) + "\n\n\n");
		System.out.println((int)(lB.getVector().getAngle() * 180.0/Math.PI) + "     " + (int)(rB.getVector().getAngle() * 180.0/Math.PI) + "\n\n\n\n\n\n\n\n\n");
		*/
		lF.setSetpoint(tX, tY);
		lB.setSetpoint(tX, tY);
		rF.setSetpoint(tX, tY);
		rB.setSetpoint(tX, tY);
		
		lF.setR(r * getRotation(lF).getX(), r * getRotation(lF).getY());
		lB.setR(r * getRotation(lB).getX(), r * getRotation(lB).getY());
		rF.setR(r * getRotation(rF).getX(), r * getRotation(rF).getY());
		rB.setR(r * getRotation(rB).getX(), r * getRotation(rB).getY());
		
		System.out.println("T: " + lF.getVectorT().getMagnitude());
		System.out.println("R: " + lF.getVectorR().getMagnitude());
		/*
		lF.set(tX + r * getRotation(lF).getX(), tY + r * getRotation(lF).getY());
		lB.set(tX + r * getRotation(lB).getX(), tY + r * getRotation(lB).getY());
		rF.set(tX + r * getRotation(rF).getX(), tY + r * getRotation(rF).getY());
		rB.set(tX + r * getRotation(rB).getX(), tY + r * getRotation(rB).getY());
		*/
		lF.update();
		lB.update();
		rF.update();
		rB.update();
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
	
}
