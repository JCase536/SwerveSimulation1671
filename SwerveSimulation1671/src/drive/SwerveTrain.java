package drive;

import math.Vector2D;

public class SwerveTrain {
	
	private SwerveWheel lF;
	private SwerveWheel lB;
	private SwerveWheel rF;
	private SwerveWheel rB;
	private Vector2D gyro;
	
	private final double length = 30;
	private final double width = 28;
	private final double diagonal = 41.04;
	
	
	public SwerveTrain() {
		lF = new SwerveWheel(1, 5);
		lB = new SwerveWheel(2, 6);
		rF = new SwerveWheel(3, 7);
		rB = new SwerveWheel(4, 8);
		gyro = new Vector2D(0, 0);
	}
	
	public void drive(double axisTX, double axisTY, double axisR) {
		lF.set(axisTX + axisR * (length / diagonal) - gyro.getX(), axisTY + axisR * (width / diagonal) - gyro.getY());
		lB.set(axisTX + axisR * (-length / diagonal) - gyro.getX(), axisTY + axisR * (width / diagonal) - gyro.getY());
		rF.set(axisTX + axisR * (-length / diagonal) - gyro.getX(), axisTY + axisR * (-width / diagonal) - gyro.getY());
		rB.set(axisTX + axisR * (length / diagonal) - gyro.getX(), axisTY + axisR * (-width / diagonal) - gyro.getY());
		if(lF.getVector().getMagnitude() > 0.1) {
			System.out.println((int)(lF.getVector().getAngle() * 180.0/Math.PI) + "     " + (int)(rF.getVector().getAngle() * 180.0/Math.PI) + "\n\n\n");
			System.out.println((int)(lB.getVector().getAngle() * 180.0/Math.PI) + "     " + (int)(rB.getVector().getAngle() * 180.0/Math.PI) + "\n\n\n\n\n\n\n\n\n");
		}
	}
	
}
