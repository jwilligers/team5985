package org.usfirst.frc.team5985.robot;

import edu.wpi.first.wpilibj.IterativeRobot;

//import edu.wpi.first.wpilibj.
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
//import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	RobotDrive myRobot;
	Joystick stick;
//	Servo servoBot;
//	Servo servoTop;
	Victor driveLeft;
	Victor driveRight;
	DigitalInput lineSensor;	
	int autoLoopCounter;
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	myRobot = new RobotDrive(0,1);
    	driveLeft = new Victor(0);
    	driveRight = new Victor(1);
    	stick = new Joystick(0);
//    	servoBot = new Servo(2);
//    	servoTop = new Servo(3);
    	lineSensor = new DigitalInput(0);
    }
    
    /**
     * This function is run once each time the robot enters autonomous mode
     */
    public void autonomousInit() {
    	System.out.println("autonomousInit: STARTED");
    	
    	SmartDashboard.putString("test", "Test");
    	
    	autoLoopCounter = 0;
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	if (lineSensor.get() && autoLoopCounter < 250)
    	{
    		myRobot.drive(-0.5, 0.0); 	// drive forwards half speed
			autoLoopCounter++;
    	}
    	
    	else 
    	{
			myRobot.drive(0.0, 0.0); 	// stop robot
		}
		if (lineSensor.get())
		{
			System.out.println("True");
		}
		else
		{
			System.out.println("False");
		}
    }
    
    /**
     * This function is called once each time the robot enters tele-operated mode
     */
    public void teleopInit(){
    	System.out.println("teleopInit: Called");
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        
    	drive();
    	
    	System.out.println("teleopPeriodic: Stick x = " + stick.getX() + " y = " + stick.getY());
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	LiveWindow.run();
    }
    
    private void drive()
    {
    	// Driving code for the robot
    	
    	double speedModifier = 1.0;
    	
    	// If the trigger is pressed, drastically limit the max speed.
    	if (stick.getRawButton(1))
    	{
    		speedModifier = 0.1;
    	}
    	
    	double steering = stick.getX() * speedModifier;
    	double power = stick.getY() * speedModifier;
    	
    	// TODO: test this on the actual robot, the signs are probably wrong 
    	driveLeft.set(power + steering);
    	driveRight.set(-power + steering);
    }
}
 