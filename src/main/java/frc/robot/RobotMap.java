/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

// Import Encoder 03/11/19
import edu.wpi.first.wpilibj.Encoder;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	public static final int LEFT_FRONT_DRIVE_TALON_PORT  = 3;
	public static final int LEFT_BACK_DRIVE_TALON_PORT   = 4;
	public static final int RIGHT_FRONT_DRIVE_TALON_PORT = 6;
	public static final int RIGHT_BACK_DRIVE_TALON_PORT  = 7;
	public static final int ARM_TALON_PORT               = 0;
	public static final int GRABBER_TALON_PORT           = 1;

	public static final int HAB_FRONT_SOLENOID_PORT = 0;
	public static final int HAB_BACK_SOLENOID_PORT  = 1;
	public static final int HATCH_SOLENOID_PORT     = 2;

	public static WPI_TalonSRX leftFrontDriveTalon;
	public static WPI_TalonSRX leftBackDriveTalon;
	public static WPI_TalonSRX rightFrontDriveTalon;
	public static WPI_TalonSRX rightBackDriveTalon;
	public static DifferentialDrive drive;
	public static WPI_AHRS navX;

	public static WPI_TalonSRX armTalon;
	public static WPI_TalonSRX grabberTalon;

	public static Solenoid habFrontSolenoid;
	public static Solenoid habBackSolenoid;
	public static Solenoid hatchSolenoid;
	
	// Declare Encoder 03/11/19
	public static Encoder armEncoder;
	public static final int PULSES_PER_REVOLUTION = 1024;
	public static final int DESIRED_PULSES = 256;
	
	/** IMPORTANT ENCODER INFORMATION (SRX MAG ENCODER)
	*	Pulses per revolution: 1024
	*	Pulses per degree: 1024 / 360
	*	Desired Angle: Number of Pulses / Pulses Per Revolution * 360
	*	
	*/
	
	// Initialize anything related to driving (motor controllers, encoders, etc.)
	public static void initDrive() {
		RobotMap.leftFrontDriveTalon = new WPI_TalonSRX(RobotMap.LEFT_FRONT_DRIVE_TALON_PORT);
		RobotMap.leftBackDriveTalon = new WPI_TalonSRX(RobotMap.LEFT_BACK_DRIVE_TALON_PORT);
		RobotMap.rightFrontDriveTalon = new WPI_TalonSRX(RobotMap.RIGHT_FRONT_DRIVE_TALON_PORT);
		RobotMap.rightBackDriveTalon = new WPI_TalonSRX(RobotMap.RIGHT_BACK_DRIVE_TALON_PORT);
		
		RobotMap.leftBackDriveTalon.follow(RobotMap.leftFrontDriveTalon);
		RobotMap.rightBackDriveTalon.follow(RobotMap.rightFrontDriveTalon);
		
		RobotMap.drive = new DifferentialDrive(RobotMap.leftFrontDriveTalon, RobotMap.rightFrontDriveTalon);

		RobotMap.leftFrontDriveTalon.setNeutralMode(NeutralMode.Coast);
		RobotMap.leftBackDriveTalon.setNeutralMode(NeutralMode.Coast);
		RobotMap.rightFrontDriveTalon.setNeutralMode(NeutralMode.Coast);
		RobotMap.rightBackDriveTalon.setNeutralMode(NeutralMode.Coast);
		
		RobotMap.drive.setDeadband(0);
	}

	public static void initHab() {
		RobotMap.habFrontSolenoid = new Solenoid(RobotMap.HAB_FRONT_SOLENOID_PORT);
		RobotMap.habBackSolenoid  = new Solenoid(RobotMap.HAB_BACK_SOLENOID_PORT);
	}
	
	public static void initCargo() {
		RobotMap.armTalon = new WPI_TalonSRX(RobotMap.ARM_TALON_PORT);
		RobotMap.grabberTalon = new WPI_TalonSRX(RobotMap.GRABBER_TALON_PORT);
	}
	
	public static void initHatch() {
		RobotMap.hatchSolenoid = new Solenoid(RobotMap.HATCH_SOLENOID_PORT);
	}

	public static void initSensors() {
		RobotMap.navX = new WPI_AHRS(Port.kMXP);
	}
	
	// Initialize Encoders 03/11/19
	public static void initEncoders() {
		RobotMap.armEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
		RobotMap.armEncoder.setMaxPeriod(.1);
		RobotMap.armEncoder.setMinRate(10);
		RobotMap.armEncoder.setDistancePerPulse(1 / RobotMap.PULSES_PER_REVOLUTION); //Set the Distance per Pulse 03/11/2019
		RobotMap.armEncoder.setReverseDirection(true);
		RobotMap.armEncoder.setSamplesToAverage(7);	
	}

}
