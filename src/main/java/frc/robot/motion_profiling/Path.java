package frc.robot.motion_profiling;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.trajectory.constraint.CentripetalAccelerationConstraint;
import frc.robot.Robot;

import static frc.robot.Robot.robotConstants;

/**
 * Class for creating a new path for motion profiling path following.  
 */
public class Path {
    private Trajectory trajectory;
    private boolean reversed;
    private static final double DEFAULT_START_PATH_VELOCITY = 0.0;
    private static final double DEFAULT_END_PATH_VELOCITY = 0.0;

    /**
     * Creates new path for motion profiling with default configuration. 
     * @param waypoints the path waypoints
     */
    public Path(Waypoint... waypoints) {
        this(false, DEFAULT_START_PATH_VELOCITY, DEFAULT_END_PATH_VELOCITY, waypoints);
    }

    /**
     * Creates new path for motion profiling with velocity default configuration.
     * @param reversed whether the path is reversed
     * @param waypoints the path waypoints 
     */
    public Path(boolean reversed, Waypoint... waypoints) {
        this(reversed, DEFAULT_START_PATH_VELOCITY, DEFAULT_END_PATH_VELOCITY, waypoints);
    }

    /**
     * Creates new path for motion profiling with start velocity default value of zero.
     * @param reversed whether the path is reversed
     * @param endVelocity the velocity at the end of the path
     * @param waypoints the path waypoints 
     */
    public Path(boolean reversed, double endVelocity, Waypoint... waypoints) {
        this(reversed, DEFAULT_START_PATH_VELOCITY, endVelocity, waypoints);
    }

    /**
     * Creates new path with reversed, start velocity & end velocity params. 
     * @param reversed whether the path is reversed
     * @param startVelocity the velocity at the start of the path 
     * @param endVelocity the velocity at the end of the path 
     * @param waypoints the path waypoints 
     */
    public Path(boolean reversed, double startVelocity, double endVelocity, Waypoint... waypoints) {
        this.reversed = reversed;
        TrajectoryConfig config = new TrajectoryConfig(robotConstants.motionProfilingConstants.MAX_VELOCITY, robotConstants.motionProfilingConstants.MAX_ACCELERATION)
                .addConstraint(new CentripetalAccelerationConstraint(robotConstants.motionProfilingConstants.MAX_CENTRIPETAL_ACCELERATION))
                .setKinematics(Robot.drivetrain.getKinematics())
                .setReversed(reversed)
                .setStartVelocity(startVelocity)
                .setEndVelocity(endVelocity);

        trajectory = TrajectoryGenerator.generateTrajectory(Arrays.asList(waypoints), config);
    }

    /**
     * @param pathName the name of the path to load from the filesystem.
     */
    public Path(String pathName) {
        var path = Paths.get(Filesystem.getDeployDirectory() +
                "/paths/" + pathName);
        try {
            trajectory = TrajectoryUtil.fromPathweaverJson(path);
        } catch (IOException e) {
            System.err.println("could not load path from: " + path.toString()
                    + " initializing with empty path instead of loading");
            trajectory =  new Trajectory(List.of(new Trajectory.State()));
        }
    }

    public Trajectory getTrajectory() {
        return trajectory;
    }

    public boolean isReversed() {
        return reversed;
    }

    /**
     * Return the time of the robot to drive the path.
     * @return path time in seconds 
     */
    public double getPathTime() {
        return trajectory.getTotalTimeSeconds();
    }
}
