package org.usfirst.frc1277.Robot2016.commands;

import org.usfirst.frc1277.Robot2016.OI;
import org.usfirst.frc1277.Robot2016.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SwingArmCommand extends Command {

    public SwingArmCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.swingArm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if (OI.joystick.getRawButton(5)) {
    		Robot.swingArm.setSolenoids(true);
    	}
    	
    	if (OI.joystick.getRawButton(7)) {
    		Robot.swingArm.setSolenoids(false);
    	}
   	
    	if (OI.joystick.getRawButton(1)) {
    		Robot.swingArm.setMotor(0.75);
    	} else if (OI.joystick.getRawButton(3)) {
    		Robot.swingArm.setMotor(-0.75);
    	} else {
    		Robot.swingArm.setMotor(0);
    	}
    	
    	SmartDashboard.putString("Solenoids", Robot.swingArm.getSolenoids() ? "On" : "Off");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
