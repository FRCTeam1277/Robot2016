package org.usfirst.frc1277.Robot2016.commands;

import org.usfirst.frc1277.Robot2016.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutonomousCommandGroup extends CommandGroup {
    
	private final DigitalInput switch1 = RobotMap.autonomousSwitch1;
	private final DigitalInput switch2 = RobotMap.autonomousSwitch2;
	
    public  AutonomousCommandGroup() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arms
    	SmartDashboard.putBoolean("Switch 1", switch1.get());
    	SmartDashboard.putBoolean("Switch 2", switch2.get());
    	if (!switch1.get() && !switch2.get()) {}
    	else if (switch1.get() && !switch2.get()) {
        	addSequential(new ResetSwingArm());
        	//addSequential(new ResetElbow());
        	// addSequential(new ResetShoulder());
        	addSequential(new DriveDistance(132)); 
    	} else if (!switch1.get() && switch2.get()) {
        	//addSequential(new ResetSwingArm());
        	//addSequential(new DriveDistance(39));
        	//addSequential(new MoveArm(770));
        	//addSequential(new DriveDistance(24));
        	//addParallel(new DriveDistance(96));
        	//addParallel(new ResetSwingArm());
    	}
    	
    }
}
