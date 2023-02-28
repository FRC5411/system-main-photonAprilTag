package frc.robot.Subsystems;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
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

  public double coneminimizer(double x1, double x2, double x3, double x4, double x5, double x6, double value) {
    double dist1 = value - x1;
    double dist2 = value - x2;
    double dist3 = value - x3;
    double dist4 = value - x4;
    double dist5 = value - x5;
    double dist6 = value - x6;
    if(Math.abs(dist1) < Math.abs(0.45)) {
      return x1;
    }
    if(dist2 < 0.45 && dist2 > -0.265) {
      return x2;
    }
    if(dist3 < 0.265 && dist4 > -0.58) {
      return x3;
    }
    if(dist4 < 0.58 && dist5 > -0.265) {
      return x4;
    }
    if(dist5 < 0.265 && dist6 > -0.45) {
      return x5;
    }
    if(Math.abs(dist6) < Math.abs(0.45)) {
      return x6;
    }
    return 0;
  }

  public double cubeminimizer(double x1, double x2, double x3, double x4, double value) {
    double dist1 = value - x1;
    double dist2 = value - x2;
    double dist3 = value - x3;
    if(Math.abs(dist1) < Math.abs(0.835)) {
      return x1;
    }
    if(Math.abs(dist2) < Math.abs(0.835)) {
      return x2;
    }
    if(Math.abs(dist3) < Math.abs(0.835)) {
      return x3;
    }
    return 0;
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