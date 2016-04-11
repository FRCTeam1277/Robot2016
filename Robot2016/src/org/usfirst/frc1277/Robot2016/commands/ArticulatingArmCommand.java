package org.usfirst.frc1277.Robot2016.commands;

import org.usfirst.frc1277.Robot2016.OI;
import org.usfirst.frc1277.Robot2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArticulatingArmCommand extends Command {

    public ArticulatingArmCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.articulatingArm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	double bottom = 0;
    	double top = 0;
    	if (OI.joystick.getRawButton(5)) {
    		bottom = 1;
    	} else if (OI.joystick.getRawButton(7)) {
    		bottom = -1;
    	}
    	if (OI.joystick.getRawButton(6)) {
    		top = 1;
    	} else if (OI.joystick.getRawButton(8)) {
    		top = -1;
    	}
    	Robot.articulatingArm.setMotors(bottom, top);
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
