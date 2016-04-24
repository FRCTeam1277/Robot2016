package org.usfirst.frc1277.Robot2016;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

    public static SpeedController driveTrainrightMotor;
    public static SpeedController driveTrainleftMotor;
    public static SpeedController swingArmMotor;
    
    public static Relay solenoids;
    public static RobotDrive driveTrainRobotDrive;
    
    public static Encoder leftEncoder;
    public static Encoder rightEncoder;
    public static Encoder swingArmEncoder;
    
    public static DigitalInput swingArmSwitch;
    public static DigitalInput autonomousSwitch1;
    public static DigitalInput autonomousSwitch2;
    
    public static DigitalInput test;
    
    public static Relay arduino;

    public static void init() {
    	
        driveTrainrightMotor = new Spark(1);
        LiveWindow.addActuator("Drive Train", "Right Motor", (Spark) driveTrainrightMotor);
        rightEncoder = new Encoder(24, 25);
        rightEncoder.setMaxPeriod(300);
        rightEncoder.setMinRate(0.0001);
        LiveWindow.addSensor("Drive Train", "Right Encoder", (Encoder) rightEncoder);
        driveTrainleftMotor = new Spark(0);
        LiveWindow.addActuator("Drive Train", "Left Motor", (Spark) driveTrainleftMotor);
        leftEncoder = new Encoder(22, 23);
        leftEncoder.setMaxPeriod(300);
        leftEncoder.setMinRate(0.0001);
        LiveWindow.addSensor("Drive Train", "Left Encoder", (Encoder) leftEncoder);
        driveTrainRobotDrive = new RobotDrive(driveTrainleftMotor, driveTrainrightMotor);
        
        swingArmMotor = new VictorSP(3);
        LiveWindow.addActuator("Swing Arm", "Motor", (VictorSP) swingArmMotor);
        swingArmEncoder = new Encoder(0, 1);
        LiveWindow.addSensor("Swing Arm", "Encoder", (Encoder) swingArmEncoder);
        swingArmSwitch = new DigitalInput(6);
        LiveWindow.addSensor("Swing Arm", "Limit Switch", (DigitalInput) swingArmSwitch);
        
        solenoids = new Relay(1, Relay.Direction.kForward);
        LiveWindow.addActuator("Swing Arm", "Solenoids", solenoids);
        
        autonomousSwitch1 = new DigitalInput(21);
        LiveWindow.addSensor("Autonomous Switches", "Switch #1", (DigitalInput) autonomousSwitch1);
        autonomousSwitch2 = new DigitalInput(9);
        LiveWindow.addSensor("Autonomous Switches", "Switch #2", (DigitalInput) autonomousSwitch2);
        
        arduino = new Relay(0);
        arduino.set(Relay.Value.kForward);
    }
}