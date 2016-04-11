package org.usfirst.frc1277.Robot2016.commands;

import org.usfirst.frc1277.Robot2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ResetElbow extends Command {

    public ResetElbow() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.articulatingArm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.articulatingArm.setElbowMotor(-0.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !Robot.articulatingArm.getElbowSwitch();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.articulatingArm.resetEncoders();
    	Robot.articulatingArm.setElbowMotor(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.articulatingArm.setElbowMotor(0);
    }
}
