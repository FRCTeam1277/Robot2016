package org.usfirst.frc1277.Robot2016.subsystems;

import org.usfirst.frc1277.Robot2016.RobotMap;
import org.usfirst.frc1277.Robot2016.commands.SwingArmCommand;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SwingArm extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private final SpeedController motor = RobotMap.swingArmMotor;
	private final DigitalInput limitSwitch = RobotMap.swingArmSwitch;
	private final Encoder encoder = RobotMap.swingArmEncoder;
	
	public void setMotor(double value) {
		
		SmartDashboard.putNumber("Arm Encoder", encoder.get());
		if (limitSwitch.get() && value < 0) {
			motor.set(value);
		} else if (value > 0) {
			motor.set(value);
		} else {
			motor.set(0);
		}
		if (!limitSwitch.get()) {
			encoder.reset();
		}
	}
	
	public boolean getSwitch() {
		return limitSwitch.get();
	}
	
	public void resetEncoder() {
		encoder.reset();
	}
	
	public int getEncoder() {
		return encoder.get();
	}
	
	public void reset() {
		
		while (limitSwitch.get()) {
			motor.set(-0.75);
		}
		motor.set(0);
		encoder.reset();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new SwingArmCommand());
    }
}

