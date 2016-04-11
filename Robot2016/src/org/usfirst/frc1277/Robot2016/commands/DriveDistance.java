package org.usfirst.frc1277.Robot2016.commands;

import org.usfirst.frc1277.Robot2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveDistance extends Command {

	public double count;
	
    public DriveDistance(double distance) {
    	
    	count = (distance / Robot.driveTrain.circumference) * 240;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	Robot.driveTrain.resetRightEncoder();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.resetRightEncoder();
    }
 
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Robot.driveTrain.drive(0.7, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        
    	return Robot.driveTrain.getRightEncoder() > count;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
