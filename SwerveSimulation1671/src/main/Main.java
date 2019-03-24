package main;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import java.awt.Point;

import org.lwjgl.opengl.GL;
import org.lwjgl.glfw.GLFWVidMode;

import drive.SwerveTrain;

public class Main {
	
	private static SwerveTrain driveBase;
	
	public Main() {
		if(!glfwInit()) {
			throw new IllegalStateException("Failed to initialize GLFW!");
		}
		
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		long window = glfwCreateWindow(640, 480, "Swerve Simulation", 0, 0);
		if(window == 0) {
			throw new IllegalStateException("Failed to create window!");
		}
		
		GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, (videoMode.width() - 640) / 2, (videoMode.height() - 480) / 2);
		glfwShowWindow(window);
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		
		driveBase = new SwerveTrain();
		
		System.out.println("Start:");
		
		while(!glfwWindowShouldClose(window)) {
			glfwPollEvents();
			glClear(GL_COLOR_BUFFER_BIT);
			
			glColor4f(0.5f, 0.5f, 0.5f, 0.5f);
			glLineWidth(3.8f);
			drawRect(driveBase.getWheel("lF").getPosition().getX(),
					driveBase.getWheel("lF").getPosition().getY(),
					driveBase.getWheel("rF").getPosition().getX(),
					driveBase.getWheel("rF").getPosition().getY(),
					driveBase.getWheel("rB").getPosition().getX(),
					driveBase.getWheel("rB").getPosition().getY(),
					driveBase.getWheel("lB").getPosition().getX(),
					driveBase.getWheel("lB").getPosition().getY());
			
			glfwSwapBuffers(window);
			driveBase.drive(0.01 * getAxis(0), 0.01 * -getAxis(1), 0.01 * getAxis(2));
		}
		
		glfwTerminate();
	}
	
	public static void main(String[] args) {
		new Main();
	}
	
	private void drawLine(double pointX, double pointY, double point2X, double point2Y) {
	    glBegin(GL_LINE_STRIP);
	    glVertex2d(pointX, pointY);
	    glVertex2d(point2X, point2Y);
	    glEnd();
	}
	
	private void drawRect(double pointX, double pointY, double point2X, double point2Y, double point3X, double point3Y, double point4X, double point4Y) {
		drawLine(pointX, pointY, point2X, point2Y);
		drawLine(point2X, point2Y, point3X, point3Y);
		drawLine(point3X, point3Y, point4X, point4Y);
		drawLine(point4X, point4Y, pointX, pointY);
	}
	
	static double getAxis(int axis) {
		return (double)glfwGetJoystickAxes(GLFW_JOYSTICK_1).get(axis);
	}
	
	static boolean getButton(int button) {
		return glfwGetJoystickButtons(GLFW_JOYSTICK_1).get(button) != 0;
	}
	
}
