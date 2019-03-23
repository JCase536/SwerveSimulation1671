package main;

import org.lwjgl.glfw.GLFW;

import math.LinearAlgebraLib;
import math.Vector2D;

public class Main extends GLFW {
	
	static Vector2D lF;
	static Vector2D lB;
	static Vector2D rF;
	static Vector2D rB;
	static Vector2D gyro;
	static final double length = 30;
	static final double width = 28;
	static final double diagonal = 41.04;
	
	public static void main(String[] args) {
		glfwInit();
		lF = new Vector2D(0, 0);
		lB = new Vector2D(0, 0);
		rF = new Vector2D(0, 0);
		rB = new Vector2D(0, 0);
		gyro = new Vector2D(0, 0);
		System.out.println("Start:");
		while(true) {
			lF.set(getAxis(0) + getAxis(2) * (length / diagonal) - gyro.getX(), -getAxis(1) + getAxis(2) * (width / diagonal) - gyro.getY());
			lB.set(getAxis(0) + getAxis(2) * (-length / diagonal) - gyro.getX(), -getAxis(1) + getAxis(2) * (width / diagonal) - gyro.getY());
			rF.set(getAxis(0) + getAxis(2) * (-length / diagonal) - gyro.getX(), -getAxis(1) + getAxis(2) * (-width / diagonal) - gyro.getY());
			rB.set(getAxis(0) + getAxis(2) * (length / diagonal) - gyro.getX(), -getAxis(1) + getAxis(2) * (-width / diagonal) - gyro.getY());
			if(lF.getMagnitude() > 0.1) {
				System.out.println((int)(lF.getAngle() * 180.0/Math.PI) + "     " + (int)(rF.getAngle() * 180.0/Math.PI) + "\n\n\n");
				System.out.println((int)(lB.getAngle() * 180.0/Math.PI) + "     " + (int)(rB.getAngle() * 180.0/Math.PI) + "\n\n\n\n\n\n\n\n\n");
			}
		}
	}
	
	static double getAxis(int axis) {
		return (double) glfwGetJoystickAxes(GLFW_JOYSTICK_1).get(axis);
	}
	
	static boolean getButton(int button) {
		return glfwGetJoystickButtons(GLFW_JOYSTICK_1).get(button) != 0;
	}
	
}
