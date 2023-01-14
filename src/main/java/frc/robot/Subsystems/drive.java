package frc.robot.Subsystems;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class drive extends SubsystemBase {
  WPI_TalonFX TopLeft;
  WPI_TalonFX TopRight;
  WPI_TalonFX BottomLeft;
  WPI_TalonFX BottomRight;
  DifferentialDrive Tank_Drive;


  public drive() {
    TopLeft = new WPI_TalonFX(0);
    TopRight = new WPI_TalonFX(1);
    BottomLeft = new WPI_TalonFX(2);
    BottomRight = new WPI_TalonFX(4);

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
