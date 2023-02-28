package frc.robot.Commands;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.Limelight;
import frc.robot.Subsystems.drive;

public class distalign extends CommandBase {
  private drive mTank_Drive;
  private Limelight m_Cams;
  private PIDController mPID;

  public distalign(Limelight cams, drive Tank, PIDController pid) {
    mTank_Drive = Tank;
    m_Cams = cams;
    mPID = pid;
    addRequirements(Tank);
  }

  @Override
  public void initialize() {
    mTank_Drive.stop();
  }

  @Override
  public void execute() {
    mTank_Drive.arcadeDrive(-mPID.calculate( mTank_Drive.aAlign(m_Cams.getTarget2d(), 2.6), 0), 0);
  }

  @Override
  public void end(boolean interrupted) {
    mTank_Drive.stop();
  }

  @Override
  public boolean isFinished() {
    return mPID.atSetpoint();
  }
}
