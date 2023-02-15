package frc.robot.Subsystems;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class drive extends SubsystemBase {
  PWMVictorSPX TopLeft;
  PWMVictorSPX TopRight;
  PWMVictorSPX BottomLeft;
  PWMVictorSPX BottomRight;
  MotorControllerGroup Right;
  MotorControllerGroup Left;
  DifferentialDrive Tank_Drive;
  


  public drive() {
    TopLeft = new PWMVictorSPX(4);
    TopRight = new PWMVictorSPX(1);
    BottomLeft = new PWMVictorSPX(2);
    BottomRight = new PWMVictorSPX(3);

    Left = new MotorControllerGroup(TopLeft, BottomLeft);
    Right = new MotorControllerGroup(TopRight, BottomRight);

    Right.setInverted(true);

    Tank_Drive = new DifferentialDrive(Left, Right);
  }

  public void stop() {
    Tank_Drive.arcadeDrive(0, 0);
  }

  public void arcade_drive(double speed, double rotation) {
    SmartDashboard.putNumber("Speed", speed);
    SmartDashboard.putNumber("Rotation", rotation);
    Tank_Drive.arcadeDrive(-speed, -rotation, true);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
