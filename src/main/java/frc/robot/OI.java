/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.commands.*;
import frc.robot.controls.ControllerBase;
import frc.robot.controls.XboxControllerPair;

/*
 * This class handles human input and maps it to commands.
 * OI stands for Operator Input
 * 
 */
public class OI {
	
	public static final int XBOX_CONTROLLER_1_PORT = 0;
	public static final int XBOX_CONTROLLER_2_PORT = 1;
	
	public ControllerBase controller;
	DriveTrigger driveTrigger;
	HabEnableTrigger habEnableTrigger;
	HabDisableTrigger habDisableTrigger;
	ArmTrigger armTrigger;
	GrabberTrigger grabberTrigger;
	HatchPopperTrigger hatchPopperTrigger;
	
	public OI() {
		// List of possible controllers
		//this.controller = new XboxController(OI.XBOX_CONTROLLER_1_PORT);
		this.controller = new XboxControllerPair(OI.XBOX_CONTROLLER_1_PORT, OI.XBOX_CONTROLLER_2_PORT);
		
		// Init triggers
		this.driveTrigger = new DriveTrigger();
		this.habEnableTrigger = new HabEnableTrigger();
		this.habDisableTrigger = new HabDisableTrigger();
		this.armTrigger = new ArmTrigger();
		this.grabberTrigger = new GrabberTrigger();
		this.hatchPopperTrigger = new HatchPopperTrigger();
	}

	// Maps triggers to commands.
	public void setTriggers() {
		this.driveTrigger.whileActive(new DirectDriveCommand());
		this.habEnableTrigger.whenActive(new HabEnableCommand());
		this.habDisableTrigger.whenActive(new HabDisableCommand());
		this.armTrigger.whileActive(new DirectArmCommand());
		this.grabberTrigger.whileActive(new DirectGrabberCommand());
		this.hatchPopperTrigger.whenActive(new PopHatchCommand());
	}
	
	class DriveTrigger extends Trigger {
		public boolean get() { return Robot.oi.controller.getDrive() != 0 || Robot.oi.controller.getTurn() != 0; }
	}
	
	class HabEnableTrigger extends Trigger {
		public boolean get() { return Robot.oi.controller.getHabEnable(); }
	}
	
	class HabDisableTrigger extends Trigger {
		public boolean get() { return Robot.oi.controller.getHabDisable(); }
	}
	
	class HabFrontTrigger extends Trigger {
		public boolean get() { return Robot.oi.controller.getHabFront(); }
	}
	
	class HabBackTrigger extends Trigger {
		public boolean get() { return Robot.oi.controller.getHabBack(); }
	}
	
	class ArmTrigger extends Trigger {
		public boolean get() { return Robot.oi.controller.getArm() != 0; }
	}
	
	class GrabberTrigger extends Trigger {
		public boolean get() { return Robot.oi.controller.getGrabber() != 0; }
	}
	
	class HatchPopperTrigger extends Trigger {
		public boolean get() { return Robot.oi.controller.getHatchPopper(); }
	}

}