package main;

import org.lwjgl.glfw.GLFW;

import drive.SwerveTrain;

public class Main extends GLFW {
	
	private static SwerveTrain driveBase;
	
	public static void main(String[] args) {
		glfwInit();
		driveBase = new SwerveTrain();
		System.out.println("Start:");
		while(true) {
			driveBase.drive(getAxis(0), -getAxis(1), getAxis(2));
		}
	}
	
	static double getAxis(int axis) {
		return (double) glfwGetJoystickAxes(GLFW_JOYSTICK_1).get(axis);
	}
	
	static boolean getButton(int button) {
		return glfwGetJoystickButtons(GLFW_JOYSTICK_1).get(button) != 0;
	}
	
}
