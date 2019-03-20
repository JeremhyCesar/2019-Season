package frc.robot.commands;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.RobotMap;
import static frc.robot.RobotMap;

public class SetArmCommand extends Command {
  
  double currentAngle = armEncoder.get() / PULSES_PER_REVOLUTION;
  
  int error = 0;
  int P, I, D = 1;
  int integral, previous_error, setpoint = 0;
  int rcw = 0;

  public SetArmCommand() {
    requires(Robot.armSubsystem);
  }
  
  protected void initialize() {
    if(Robot.oi.controller == null) throw new NullPointerException("Controller was null.");
  }
  
  protected void execute() {
     error = DESIRED_PULSES - currentAngle; // Error = Target - Actual
     this.integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
     derivative = (error - this.previous_error) / .02;
     this.rcw = P*error + I*this.integral + D*derivative;
  }
  
  protected boolean isFinished() {
    return false;
  }
  
  protected void end() {
    
  }

}
