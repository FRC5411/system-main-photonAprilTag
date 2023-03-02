package frc.robot.Subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxPIDController.AccelStrategy;
import com.revrobotics.SparkMaxPIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
    private final CANSparkMax mBiscep;
    private final DutyCycleEncoder biscepEncoder;
    private final ProfiledPIDController biscepPID2;

    public Arm() {
        mBiscep = new CANSparkMax(12, MotorType.kBrushless);

        mBiscep.setIdleMode(IdleMode.kBrake);

        biscepEncoder = new DutyCycleEncoder(9);

        biscepPID2 = new ProfiledPIDController(/*4.495605*/ 0.040, 0, 0.0, 
        new TrapezoidProfile.Constraints(360, 379));

        biscepPID2.setTolerance(2);
    }

    public void setArm(double speed) {
        mBiscep.set(speed);
    }


    public double posArm(double angle) {
//        biscepPID.setReference(angle, CANSparkMax.ControlType.kPosition);
        return biscepPID2.calculate(360 * biscepEncoder.getAbsolutePosition(), angle);
    }

    public double getABSPOS() {
        return biscepEncoder.getAbsolutePosition();
    }

    public void stop() {
      setArm(0);
    }

    @Override  public void periodic() {
      SmartDashboard.putNumber("Arm Pos", 360 * getABSPOS());
      SmartDashboard.putNumber("PID value", posArm(90));
    }
    
    @Override  public void simulationPeriodic() {}

    public void configPID(double kp, double kd, double FF, double maxV, double maxA, double tolerance, int profile, RelativeEncoder encoder, SparkMaxPIDController controller) {
        controller.setSmartMotionAllowedClosedLoopError(tolerance, profile);
        controller.setP(kp, profile);
        controller.setD(kd, profile);
        controller.setFF(FF, profile);
        controller.setSmartMotionMaxAccel(maxA, profile);
        controller.setSmartMotionMaxVelocity(maxV, profile);
        controller.setSmartMotionAccelStrategy(AccelStrategy.kSCurve, profile);
        controller.setFeedbackDevice(encoder);
    }
}