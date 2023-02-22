package frc.robot.Commands;
import frc.robot.Constants.AutoEngage;
import frc.robot.Subsystems.drive;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.kauailabs.navx.frc.AHRS;
public class AutoEngageCommand extends CommandBase {
  private drive robotDrive;
  private AHRS navX;
  private double errorPitch;
  private double errorYaw;
  private double drivePowerPitch;
  private double drivePowerYaw;
  /** Command to use Gyro data to resist the tip angle from the beam - to stabalize and balanace */
  public AutoEngageCommand(drive parameterSubsystem, AHRS parameternavX) {
    this.robotDrive = parameterSubsystem;
    this.navX = parameternavX;
    addRequirements(parameterSubsystem);
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    errorYaw = navX.getYaw() - AutoEngage.TARGET_YAW;
    errorPitch = navX.getPitch() - AutoEngage.TARGET_PITCH;
    drivePowerPitch = -Math.min(AutoEngage.DRIVEPOWER_KP * errorPitch, 1);
    drivePowerYaw = -Math.min(AutoEngage.DRIVEPOWER_KP * errorYaw, 1);
    robotDrive.arcadeDrive(drivePowerPitch, drivePowerYaw);
  }
  @Override
  public void end(boolean interrupted) {
    robotDrive.stop();
  }
  @Override
  public boolean isFinished() {
    return Math.abs(errorPitch) < AutoEngage.PITCH_TOLERANCE;
  }
}