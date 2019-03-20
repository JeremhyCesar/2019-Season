package frc.robot.commands;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetArmCommand extends Command {

  public SetArmCommand() {
    requires(Robot.armSubsystem);
  }
  
  protected void initialize() {
    if(Robot.oi.controller == null) throw new NullPointerException("Controller was null.");
  }
  
  protected void execute() {
  
  }
  
  protected boolean isFinished() {
    return false;
  }
  
  protected void end() {
    
  }

}
