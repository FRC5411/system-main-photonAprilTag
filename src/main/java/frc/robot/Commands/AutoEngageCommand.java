package frc.robot.Commands;
import frc.robot.Subsystems.drive;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.ctre.phoenix.sensors.Pigeon2;

public class AutoEngageCommand extends CommandBase {
  private drive robotDrive;
  private Pigeon2 pigeon2;
  private PIDController PID;

  public AutoEngageCommand(drive parameterSubsystem, Pigeon2 parameternavX) {
    PID = new PIDController(0.1, 0, 0.01);
    this.robotDrive = parameterSubsystem;
    this.pigeon2 = parameternavX;
    addRequirements(parameterSubsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    PID.calculate(pigeon2.getYaw(), 0);

  }
  
  @Override
  public void end(boolean interrupted) {
    robotDrive.stop();
  }
  @Override
  public boolean isFinished() {
    return PID.atSetpoint();
  }
}