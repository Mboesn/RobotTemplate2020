package frc.robot.constants;

/**
 * The RobotConstants maps constants to a variable name.
 */
public abstract class RobotConstants extends RobotMap {
    public DrivetrainConstants drivetrainConstants = new DrivetrainConstants(); 
    public VisionConstants visionConstants = new VisionConstants();
    // Example: 
    public static class DrivetrainConstants {
        public double WHEEL_DIAMETER; 
        public double WHEEL_BASE_WIDTH;
        public double ROBOT_LENGTH;
        public double ROBOT_WIDTH;
        public double LEFT_ENCODER_METER_PER_TICKS;
        public double RIGHT_ENCODER_METER_PER_TICKS;
    }

    public static class VisionConstants {
        public double DISTANCE_CALCULATION_A_COEFFICIENT;
        public double DISTANCE_CALCULATION_B_COEFFICIENT;
        public double LIMELIGHT_OFFSET_X;
        public double LIMELIGHT_OFFSET_Y;
    }

    // More static class here! 

}
