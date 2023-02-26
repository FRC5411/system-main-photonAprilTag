package frc.robot.Subsystems;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.geometry.Pose2d;

public class drive extends SubsystemBase {
  WPI_TalonSRX TopLeft;
  WPI_TalonSRX TopRight;
  WPI_TalonSRX BottomLeft;
  WPI_TalonSRX BottomRight;
  DifferentialDrive Tank_Drive;
  AHRS m_gyro;

  public drive(AHRS gyro) {
    TopLeft = new WPI_TalonSRX(12);
    TopRight = new WPI_TalonSRX(15);
    BottomLeft = new WPI_TalonSRX(13);
    BottomRight = new WPI_TalonSRX(14);

    m_gyro = gyro;

    TopLeft.follow(BottomLeft);
    TopRight.follow(BottomRight);

    TopLeft.setNeutralMode(NeutralMode.Brake);
    TopRight.setNeutralMode(NeutralMode.Brake);
    BottomLeft.setNeutralMode(NeutralMode.Brake);
    BottomRight.setNeutralMode(NeutralMode.Brake);
    TopRight.setInverted(true);
    BottomRight.setInverted(true);

    Tank_Drive = new DifferentialDrive(BottomLeft, BottomRight);
  }

  public void stop() {
    Tank_Drive.arcadeDrive(0, 0);
  }

  public void arcadeDrive(double speed, double rotation) {
    SmartDashboard.putNumber("Speed", speed);
    SmartDashboard.putNumber("Rotation", rotation);
    Tank_Drive.arcadeDrive(-speed, -rotation, true);
  }

  public double aAlign(Pose2d target, double offset) {
    double angle = Math.toDegrees(Math.atan2(target.getY() - offset, target.getX()));
    double real_angle = target.getRotation().getDegrees();
    return angle + real_angle;
  }
  
  public double tAlign(Pose2d target, double offset) {
    double dist  = target.getY() - offset;
    return dist;
  }

  @Override
  public void periodic() {
/*    SmartDashboard.putNumber("Gyro Yaw", m_gyro.getYaw());
    SmartDashboard.putNumber("Gyro Pitch", m_gyro.getPitch());
    SmartDashboard.putNumber("Gyro Roll", m_gyro.getRoll());
    SmartDashboard.putNumber("Right upper motor", 0);
    SmartDashboard.putNumber("Left upper motor", 0);
    SmartDashboard.putNumber("Right lower mtor", 0);
    SmartDashboard.putNumber(getName(), 0);*/
  }
}