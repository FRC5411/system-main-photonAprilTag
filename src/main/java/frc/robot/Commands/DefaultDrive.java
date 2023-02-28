package frc.robot.Commands;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.drive;

public class DefaultDrive extends CommandBase {
  DoubleSupplier m_speed;
  DoubleSupplier m_rotation;
  drive m_drive;

  public DefaultDrive(DoubleSupplier speed, DoubleSupplier rotation, drive Drive) {
    m_speed = speed;
    m_rotation  = rotation;
    m_drive = Drive;
    addRequirements(Drive);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    m_drive.arcadeDrive(m_speed.getAsDouble(), m_rotation.getAsDouble());
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
