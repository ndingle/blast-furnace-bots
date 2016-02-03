
package org.usfirst.frc.team5983.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    //Variables that connect to the robot and the input devices
    RobotDrive jenny;
    Joystick joystick1;
    Joystick joystick2;
    
    //Ports used for the motors
    static final int LEFT_MOTOR = 0;
    static final int RIGHT_MOTOR = 1;
	
    //Ports for the joysticks
    int logitechJoystickPort;
    int x360ControllerPort;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        
        //Connect to the robot
        jenny = new RobotDrive(LEFT_MOTOR, RIGHT_MOTOR);
        
        //Get the joysticks
        joystick1 = new Joystick(0);
        joystick2 = new Joystick(1);
        
        /*
         * Check if the first joystick is the 360 controller,
         * if so, then set the x360controller port to 0 and
         * logitech port to 1. Otherwise, set the numbers
         * the other way around
         */
        if(joystick1.getIsXbox()) {
        	
        	x360ControllerPort = 0;
        	logitechJoystickPort = 1;
        	
        }
        else {
        	
        	x360ControllerPort = 1;
        	logitechJoystickPort = 0;
        	
        }
        
        /*
         * Set the turning sensitivity to 0.1 to reduce
         * the robot kicking when turning at high speeds 
         */
        //jenny.setSensitivity(0.1);
        
        
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {

    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {

    }

    public void teleopInit() {
    	

    }
    
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	
    	if(x360ControllerPort == 0){
    		jenny.arcadeDrive(joystick1.getRawAxis(1), joystick1.getRawAxis(4), true);
    	}
    	else {
    		jenny.arcadeDrive(joystick2.getRawAxis(1), joystick2.getRawAxis(4), true);
    	}
    	
    	
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    	
    	
    }
    
}
