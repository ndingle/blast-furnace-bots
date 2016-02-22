
package org.usfirst.frc.team5983.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
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
    
    Joystick x360Controller;
    Joystick logitechJoystick;
    
    Victor verticalArmController;
    Victor horizontalArmController;
    
    DigitalInput verticalSwitch;
    
    float forwardTime = 0.0f;
    float backwardTime = 0.0f;
    
    //Ports used for the motors
    static final int LEFT_MOTOR = 0;
    static final int RIGHT_MOTOR = 1;
    
    static final int HORIZONTAL_ARM_MOTOR_PORT = 2;
    static final int VERTICAL_ARM_MOTOR_PORT = 3;
    
    static final int VERTICAL_ARM_SWITCH_PORT = 0;
    
    long timePassed = 0;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        
        //Connect to the robot
        jenny = new RobotDrive(LEFT_MOTOR, RIGHT_MOTOR);
        
        //Get the joysticks
        if((new Joystick(0)).getIsXbox()) {
        	x360Controller = new Joystick(0);
        	logitechJoystick = new Joystick(1);
        }
        else {
        	x360Controller = new Joystick(1);
        	logitechJoystick = new Joystick(0);
        }

        //Connect to the vertical arm motor
        verticalArmController = new Victor(VERTICAL_ARM_MOTOR_PORT);
        horizontalArmController = new Victor(HORIZONTAL_ARM_MOTOR_PORT);
        
        verticalSwitch = new DigitalInput(VERTICAL_ARM_SWITCH_PORT);
        
        /*
         * Set the turning sensitivity to 0.1 to reduce
         * the robot kicking when turning at high speeds 
         */
        jenny.setSensitivity(1);
        
        //Debug window
        timePassed = 0;
        
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
    	
    	//This is for driving with the Logitech Joystick
    	jenny.arcadeDrive(logitechJoystick);
    	
    	moveHorizontalArm();
    	moveVerticalArm();
    	printDebugInfo();
    	
    	timePassed++;
    	
    }
    
    public void moveVerticalArm() {
    	
    	if(x360Controller.getPOV() == 0) {
    		verticalArmController.set(-0.049);
    	}
    	else if(x360Controller.getPOV() == 180 ) {
    		verticalArmController.set(-0.4);
    	}
    	else if(verticalSwitch.get() == true){
    		verticalArmController.set(-0.2);
    	}
    	else {
    		verticalArmController.set(0.0);
    	}
    	
    }
    
    public void moveHorizontalArm() {
    	
    	
    	//Controller the arm for of the robot
    	if(logitechJoystick.getTrigger() == true) {
    		horizontalArmController.set(0.2);
    	}
    	else if(logitechJoystick.getRawButton(2) == true) { 
    		horizontalArmController.set(-0.2);
    	}
    	else {
    		horizontalArmController.set(0);
    	}
    }
    
    public void printDebugInfo() {

    	SmartDashboard.putNumber("TimePassed", timePassed);
    	SmartDashboard.putBoolean("Close Horizontal Arms", logitechJoystick.getTrigger());
    	SmartDashboard.putBoolean("Open Horizontal Arms", logitechJoystick.getRawButton(2));
    	SmartDashboard.putNumber("POV", logitechJoystick.getPOV());
    	SmartDashboard.putBoolean("Vertical Switch", verticalSwitch.get());
    	SmartDashboard.putNumber("Vertical Speed", verticalArmController.get());
    	
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    	
    	
    }
    
}

