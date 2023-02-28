package frc.robot.Subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxPIDController.AccelStrategy;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class Arm extends SubsystemBase {
    private final CANSparkMax mBiscep;
    private final RelativeEncoder biscepEncoder;  
    private final SparkMaxPIDController biscepPID;  

    public Arm() {
        mBiscep = new CANSparkMax(12, MotorType.kBrushless);

        mBiscep.setIdleMode(IdleMode.kBrake);

        biscepEncoder = mBiscep.getEncoder();

        biscepEncoder.setPositionConversionFactor(360);

        biscepEncoder.setVelocityConversionFactor(360);

        biscepPID = mBiscep.getPIDController(); 

        configPID(0.1, 0.05, 0, 0.1, 0.11, 0, biscepEncoder, biscepPID);
    }

    public void setArm(double speed) {
        mBiscep.set(speed);
    }


    public void posArm(double angle) {
        biscepPID.setReference(angle, CANSparkMax.ControlType.kPosition);
    }

    public void stop() {
      setArm(0);
    }

    @Override  public void periodic() {
      SmartDashboard.putNumber("Arm Pos", biscepEncoder.getPosition());
    }
    
    @Override  public void simulationPeriodic() {}

    public void configPID(double kp, double kd, double FF, double maxV, double maxA, int profile, RelativeEncoder encoder, SparkMaxPIDController controller) {
        controller.setP(kp, profile);
        controller.setD(kd, profile);
        controller.setFF(FF, profile);
        controller.setSmartMotionMaxAccel(maxA, profile);
        controller.setSmartMotionMaxVelocity(maxV, profile);
        controller.setSmartMotionAccelStrategy(AccelStrategy.kSCurve, profile);
        controller.setFeedbackDevice(encoder);
    }
}