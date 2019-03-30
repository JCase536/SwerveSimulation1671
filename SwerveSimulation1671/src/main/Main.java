package main;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import java.awt.Point;

import org.lwjgl.opengl.GL;
import org.lwjgl.glfw.GLFWVidMode;

import drive.SwerveTrain;
import drive.SwerveWheel;

public class Main {
	
	private static SwerveTrain driveBase;
	private static final int HEIGHT = 900;
	private static final int WIDTH = 900;
	
	public Main() {
		if(!glfwInit()) {
			throw new IllegalStateException("Failed to initialize GLFW!");
		}
		
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		long window = glfwCreateWindow(WIDTH, HEIGHT, "Swerve Simulation", 0, 0);
		if(window == 0) {
			throw new IllegalStateException("Failed to create window!");
		}
		
		GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, (videoMode.width() - WIDTH) / 2, (videoMode.height() - HEIGHT) / 2);
		glfwShowWindow(window);
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		
		driveBase = new SwerveTrain();
		
		System.out.println("Start:");
		
		while(!glfwWindowShouldClose(window)) {
			glfwPollEvents();
			glClear(GL_COLOR_BUFFER_BIT);
			
			glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
			glBegin(GL_QUADS);
				glVertex2d(-1.0, 1.0);
				glVertex2d(1.0, 1.0);
				glVertex2d(1.0, -1.0);
				glVertex2d(-1.0, -1.0);
			glEnd();
			
			glColor4f(0.0f, 0.0f, 0.0f, 1.0f);
			glLineWidth(7.5f);
			drawRect(driveBase.getWheel("lF").getPosition().getX(),
					driveBase.getWheel("lF").getPosition().getY(),
					driveBase.getWheel("rF").getPosition().getX(),
					driveBase.getWheel("rF").getPosition().getY(),
					driveBase.getWheel("rB").getPosition().getX(),
					driveBase.getWheel("rB").getPosition().getY(),
					driveBase.getWheel("lB").getPosition().getX(),
					driveBase.getWheel("lB").getPosition().getY());
			
			glColor4f(0.0f, 0.5f, 0.0f, 1.0f);
			drawWheel(driveBase.getWheel("lF"));
			drawWheel(driveBase.getWheel("lB"));
			drawWheel(driveBase.getWheel("rF"));
			drawWheel(driveBase.getWheel("rB"));
			
			glfwSwapBuffers(window);
			driveBase.drive(0.001 * getAxis(0), 0.001 * -getAxis(1), 0.001 * getAxis(2));
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
		glColor4f(1.0f, 0.0f, 0.0f, 1.0f);
		glBegin(GL_QUADS);
			glVertex2d(pointX, pointY);
			glVertex2d(point2X, point2Y);
			glVertex2d((point2X + point3X) / 2.0, (point2Y + point3Y) / 2.0);
			glVertex2d((pointX + point4X) / 2.0, (pointY + point4Y) / 2.0);
		glEnd();
	}
	
	private void drawWheel(SwerveWheel wheel) {
		drawLine(wheel.getPosition().getX(),
				wheel.getPosition().getY(),
				wheel.getPosition().getX() + 200 * wheel.getVector().getX(),
				wheel.getPosition().getY() + 200 * wheel.getVector().getY());
	}
	
	static double getAxis(int axis) {
		return (double)glfwGetJoystickAxes(GLFW_JOYSTICK_1).get(axis);
	}
	
	static boolean getButton(int button) {
		return glfwGetJoystickButtons(GLFW_JOYSTICK_1).get(button) != 0;
	}
	
}
