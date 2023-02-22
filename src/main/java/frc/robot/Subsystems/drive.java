package frc.robot.Subsystems;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;

public class drive extends SubsystemBase {
  PWMVictorSPX TopLeft;
  PWMVictorSPX TopRight;
  PWMVictorSPX BottomLeft;
  PWMVictorSPX BottomRight;
  MotorControllerGroup Right;
  MotorControllerGroup Left;
  DifferentialDrive Tank_Drive;
  AHRS m_gyro;

  public drive(AHRS gyro) {
    TopLeft = new PWMVictorSPX(4);
    TopRight = new PWMVictorSPX(1);
    BottomLeft = new PWMVictorSPX(2);
    BottomRight = new PWMVictorSPX(3);

    m_gyro = gyro;

    Left = new MotorControllerGroup(TopLeft, BottomLeft);
    Right = new MotorControllerGroup(TopRight, BottomRight);

    Right.setInverted(true);

    Tank_Drive = new DifferentialDrive(Left, Right);
  }

  public void stop() {
    Tank_Drive.arcadeDrive(0, 0);
  }

  public void arcadeDrive(double speed, double rotation) {
    SmartDashboard.putNumber("Speed", speed);
    SmartDashboard.putNumber("Rotation", rotation);
    Tank_Drive.arcadeDrive(-speed, -rotation, true);
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
/**
   * Align the robot with a given apriltag target
   * @param limelight - The limelight subsystem responsible for tracking targets
   * @author Cody Washington
   */
  public void apriltagAlignment(Limelight limelight)
  {
    //Case one; target oriented to direct (Within 5 degrees of error)
    if(Math.abs(limelight.getYaw()) > 15)
    {
      limelight.getTarget2d().getTranslation().getDistance(limelight.getPose().getTranslation());
      switch(0)
      {
        case 0:
          while(Math.round(limelight.getTarget2d().getTranslation().getDistance(limelight.getPose().getTranslation())) != Math.round(1))
          {
            if(Math.round(limelight.getTarget2d().getTranslation().getDistance(limelight.getPose().getTranslation())) > Math.round(1))
              arcadeDrive(1, 0);
            else
              arcadeDrive((-1), 0);
          }
        case 1:
          while(Math.round(limelight.getTarget2d().getTranslation().getDistance(limelight.getPose().getTranslation())) != Math.round(1))
          {
            if(Math.round(limelight.getTarget2d().getTranslation().getDistance(limelight.getPose().getTranslation())) > Math.round(1))
              arcadeDrive(1, 0);
            else
              arcadeDrive((-1), 0);
          }
        case 2:
          while(Math.round(limelight.getTarget2d().getTranslation().getDistance(limelight.getPose().getTranslation())) != Math.round(1))
          {
            if(Math.round(limelight.getTarget2d().getTranslation().getDistance(limelight.getPose().getTranslation())) > Math.round(1))
              arcadeDrive(1, 0);
            else
              arcadeDrive((-1), 0);
          }
      }
    }
    //Case two; target oriented to perpendicular (Within 5 degrees of error)
    else if (Math.abs(Math.round(limelight.getPose().minus(limelight.getTarget2d()).getRotation().getDegrees())) < 15)
    {
      switch("CUBE")
      {
        case "CONE":
          while(Math.round(limelight.getTarget2d().getTranslation().getDistance(limelight.getPose().getTranslation())) != Math.round(1))
          {
            if(Math.round(limelight.getTarget2d().getTranslation().getDistance(limelight.getPose().getTranslation())) > Math.round(1))
              arcadeDrive(1, 0);
            else
              arcadeDrive((-1), 0);
          }
        case "CUBE":
          while(Math.round(limelight.getTarget2d().getTranslation().getDistance(limelight.getPose().getTranslation())) != Math.round(1))
          {
            if(Math.round(limelight.getTarget2d().getTranslation().getDistance(limelight.getPose().getTranslation())) > Math.round(1))
              arcadeDrive(1, 0);
            else
              arcadeDrive((-1), 0);
          }
      }
      arcadeDrive(0.0, (limelight.getTarget2d().getRotation().getDegrees() > 0)? (-90): (90));
      switch(1)
      {
        case 0:
          while(Math.round(limelight.getTarget2d().getTranslation().getDistance(limelight.getPose().getTranslation())) != Math.round(1))
          {
            if(Math.round(limelight.getTarget2d().getTranslation().getDistance(limelight.getPose().getTranslation())) > Math.round(1))
              arcadeDrive(1, 0);
            else
              arcadeDrive((-1), 0);
          }
        case 1:
          while(Math.round(limelight.getTarget2d().getTranslation().getDistance(limelight.getPose().getTranslation())) != Math.round(1))
          {
            if(Math.round(limelight.getTarget2d().getTranslation().getDistance(limelight.getPose().getTranslation())) > Math.round(1))
              arcadeDrive(1, 0);
            else
              arcadeDrive((-1), 0);
          }
        case 2:
          while(Math.round(limelight.getTarget2d().getTranslation().getDistance(limelight.getPose().getTranslation())) != Math.round(1))
          {
            if(Math.round(limelight.getTarget2d().getTranslation().getDistance(limelight.getPose().getTranslation())) > Math.round(1))
              arcadeDrive(1, 0);
            else
              arcadeDrive((-1), 0);
          }
      }
    }
    //Case three; target oriented other
    else
    {
      Pose2d PerpendicularPose = new Pose2d(((limelight.getPose().getY() * limelight.getTarget2d().getRotation().getDegrees()) - (limelight.getPose().getRotation().getDegrees() * limelight.getTarget2d().getY())),
      ((limelight.getPose().getRotation().getDegrees() * limelight.getTarget2d().getX()) - (limelight.getPose().getX() * limelight.getTarget2d().getRotation().getDegrees())), 
      (new Rotation2d((limelight.getPose().getX() * limelight.getTarget2d().getY()) - (limelight.getPose().getY() * limelight.getTarget2d().getX()))));
      arcadeDrive(0, PerpendicularPose.getRotation().getDegrees());
      switch("CONE")
      {
        case "CONE":
          while(Math.round(limelight.getTarget2d().getTranslation().getDistance(limelight.getPose().getTranslation())) != Math.round(1))
          {
            if(Math.round(limelight.getTarget2d().getTranslation().getDistance(limelight.getPose().getTranslation())) > Math.round(1))
              arcadeDrive(1, 0);
            else
              arcadeDrive((-1), 0);
          }
        case "CUBE":
          while(Math.round(limelight.getTarget2d().getTranslation().getDistance(limelight.getPose().getTranslation())) != Math.round(1))
          {
            if(Math.round(limelight.getTarget2d().getTranslation().getDistance(limelight.getPose().getTranslation())) > Math.round(1))
              arcadeDrive(1, 0);
            else
              arcadeDrive((-1), 0);
          }
      }
      arcadeDrive(0.0, (limelight.getTarget2d().getRotation().getDegrees() > 0)? (-90): (90));
      switch(1)
      {
        case 0:
          while(Math.round(limelight.getTarget2d().getTranslation().getDistance(limelight.getPose().getTranslation())) != Math.round(1))
          {
            if(Math.round(limelight.getTarget2d().getTranslation().getDistance(limelight.getPose().getTranslation())) > Math.round(1))
              arcadeDrive(1, 0);
            else
              arcadeDrive((-1), 0);
          }
        case 1:
          while(Math.round(limelight.getTarget2d().getTranslation().getDistance(limelight.getPose().getTranslation())) != Math.round(1))
          {
            if(Math.round(limelight.getTarget2d().getTranslation().getDistance(limelight.getPose().getTranslation())) > Math.round(1))
              arcadeDrive(1, 0);
            else
              arcadeDrive((-1), 0);
          }
        case 2:
          while(Math.round(limelight.getTarget2d().getTranslation().getDistance(limelight.getPose().getTranslation())) != Math.round(1))
          {
            if(Math.round(limelight.getTarget2d().getTranslation().getDistance(limelight.getPose().getTranslation())) > Math.round(1))
              arcadeDrive(1, 0);
            else
              arcadeDrive((-1), 0);
          }
      }
    }
  }
}
