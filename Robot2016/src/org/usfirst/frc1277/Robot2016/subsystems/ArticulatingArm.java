package org.usfirst.frc1277.Robot2016.subsystems;

import org.usfirst.frc1277.Robot2016.RobotMap;
import org.usfirst.frc1277.Robot2016.commands.ArticulatingArmCommand;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ArticulatingArm extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private final SpeedController shoulder = RobotMap.articulatingArmShoulder;
    private final SpeedController elbow = RobotMap.articulatingArmElbow;
    private final Encoder shoulderEncoder = RobotMap.shoulderEncoder;
    private final Encoder elbowEncoder = RobotMap.elbowEncoder;
    private final DigitalInput shoulderSwitch = RobotMap.shoulderSwitch;
    private final DigitalInput elbowSwitch = RobotMap.elbowSwitch;
    
    private final double encoderResolution = 1340.0;
    private final double toRadRatio = (Math.PI * 2.0) / encoderResolution;
    private final double startingAngle = Math.asin(1.625 / 16.5);
    private double[] posHand = {0.0, 0.0};
    private double[] posElbow = {0.0, 0.0};
    private final double L1 = 16.5;
    private final double L2 = 21.0;
    
	public void setMotors(double bottom, double top) {
		
		SmartDashboard.putNumber("Shoulder Encoder", shoulderEncoder.get());
		SmartDashboard.putNumber("Elbow Encoder", elbowEncoder.get());
		calcPosHand();
		SmartDashboard.putNumber("Hand X Position", posHand[0]);
		SmartDashboard.putNumber("Hand Y Position", posHand[1]);
		calcPosElbow();
		SmartDashboard.putNumber("Elbow X Position", posElbow[0]);
		SmartDashboard.putNumber("Elbow Y Position", posElbow[1]);
		
		double[] posHandPrediction = predictPosHand(bottom, top);
		double[] posElbowPrediction = predictPosElbow(bottom);
		double shoulderMultiplier = 1.0;
		double elbowMultiplier = 1.0;
		if ((posElbowPrediction[1] - posElbow[1]) < 0) {
			shoulderMultiplier = 0.77;
		} else if ((posElbowPrediction[1] - posElbow[1]) > 0) {
			shoulderMultiplier = 1.2;
		}
		if ((posHandPrediction[1] - posHand[1]) < 0) {
			elbowMultiplier = 0.77;
		} else if ((posHandPrediction[1] - posHand[1]) > 0) {
			elbowMultiplier = 1.2;
		} 
		
		double thresholdHand = ((-8.0 / 36.0) * posHand[1]) + 15.0;
		SmartDashboard.putNumber("Hand Threshold", thresholdHand);
		double thresholdElbow = ((-8.0 / 36.0) * posElbow[1]) + 15.0;
		if (posHand[0] >= thresholdHand && (posHandPrediction[0] - posHand[0]) > 0) {
			shoulder.set(0);
			elbow.set(0);
		} else if (posElbow[0] >= thresholdElbow && (posElbowPrediction[0] - posElbow[0]) > 0) {
			shoulder.set(0);
			elbow.set(0);
		} else {
			if (bottom == 1) {
				shoulder.set(.65 * shoulderMultiplier);
			} else if (shoulderSwitch.get() && bottom == -1) {
				shoulder.set(-.65 * shoulderMultiplier);
			} else if (!shoulderSwitch.get()) {
				shoulder.set(0);
				shoulderEncoder.reset();
			} else {
				shoulder.set(0);
			}
			
			if (top == 1) {
				elbow.set(.6 * elbowMultiplier);
			} else if (elbowSwitch.get() && top == -1) {
				elbow.set(-.6 * elbowMultiplier);
			} else if (!elbowSwitch.get()) {
				elbow.set(0);
				elbowEncoder.reset();
			} else {
				elbow.set(0);
			}
		}
	}
	
	private void calcPosHand() {
		
		double shoulderAngle = (-shoulderEncoder.get() * toRadRatio) + startingAngle;
		double elbowAngle = (-elbowEncoder.get() * toRadRatio) + startingAngle;
		posHand[0] = (L1 * Math.cos(Math.PI - shoulderAngle)) + (L2 * Math.cos(elbowAngle - shoulderAngle));
		posHand[1] = (L1 * Math.sin(Math.PI - shoulderAngle)) + (L2 * Math.sin(elbowAngle - shoulderAngle));
	}
	
	private void calcPosElbow() {
		
		double shoulderAngle = (-shoulderEncoder.get() * toRadRatio) + startingAngle;
		posElbow[0] = L1 * Math.cos(Math.PI - shoulderAngle);
		posElbow[1] = L1 * Math.sin(Math.PI - shoulderAngle);
	}
	
	private double[] predictPosHand(double bottom, double top) {
		
		double shoulderAngle = ((-shoulderEncoder.get() + bottom) * toRadRatio) + startingAngle;
		double elbowAngle = ((-elbowEncoder.get() + top) * toRadRatio) + startingAngle;
		double[] posHandPrediction = new double[2];
		posHandPrediction[0] = (L1 * Math.cos(Math.PI - shoulderAngle)) + (L2 * Math.cos(elbowAngle - shoulderAngle));
		posHandPrediction[1] = (L1 * Math.sin(Math.PI - shoulderAngle)) + (L2 * Math.sin(elbowAngle - shoulderAngle));
		return posHandPrediction;
	}
	
	private double[] predictPosElbow(double bottom) {
		
		double shoulderAngle = ((-shoulderEncoder.get() + bottom) * toRadRatio) + startingAngle;
		double[] posElbowPrediction = new double[2];
		posElbowPrediction[0] = L1 * Math.cos(Math.PI - shoulderAngle);
		posElbowPrediction[1] = L1 * Math.sin(Math.PI - shoulderAngle);
		return posElbowPrediction;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ArticulatingArmCommand());
    }
    
    public boolean getShoulderSwitch() {
    	return shoulderSwitch.get();
    }
    
    public boolean getElbowSwitch() {
    	return elbowSwitch.get();
    }
    
    public void resetEncoders() {
    	shoulderEncoder.reset();
    	elbowEncoder.reset();
    }
    
    public void setShoulderMotor(double value) {
    	shoulder.set(value);
    }
    
    public void setElbowMotor(double value) {
    	elbow.set(value);
    }
}

