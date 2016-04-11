package org.usfirst.frc1277.Robot2016.commands;

import org.usfirst.frc1277.Robot2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ResetShoulder extends Command {

    public ResetShoulder() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.articulatingArm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.articulatingArm.setShoulderMotor(-0.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !Robot.articulatingArm.getShoulderSwitch();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.articulatingArm.resetEncoders();
    	Robot.articulatingArm.setShoulderMotor(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.articulatingArm.setShoulderMotor(0);
    }
}
