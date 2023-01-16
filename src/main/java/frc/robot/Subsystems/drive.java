package frc.robot.Subsystems;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class drive extends SubsystemBase {
  WPI_VictorSPX TopLeft;
  WPI_VictorSPX TopRight;
  WPI_VictorSPX BottomLeft;
  WPI_VictorSPX BottomRight;
  DifferentialDrive Tank_Drive;


  public drive() {
    TopLeft = new WPI_VictorSPX(4);
    TopRight = new WPI_VictorSPX(1);
    BottomLeft = new WPI_VictorSPX(2);
    BottomRight = new WPI_VictorSPX(3);

    TopLeft.follow(BottomLeft);
    TopRight.follow(BottomRight);

    Tank_Drive = new DifferentialDrive(BottomLeft, BottomRight);
  }

  public void arcade_drive(double speed, double rotation) {
    Tank_Drive.arcadeDrive(speed, rotation, true);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
