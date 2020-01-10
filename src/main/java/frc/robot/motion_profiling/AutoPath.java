package frc.robot.motion_profiling;

import frc.robot.motion_profiling.Path;
import frc.robot.motion_profiling.Waypoint;

import static frc.robot.Robot.fieldConstants;;

/**
 * This enum represent and hold the instances of auto paths
 */
public enum AutoPath {
    ExamplePath(new Path(new Waypoint(0,0,0), new Waypoint(fieldConstants.feederConstants.SIDE_WALL_TO_MIDDLE_FEEDER,
        fieldConstants.feederConstants.ROCKET_TO_FEEDER, 90))), // this example uses field constans :)
    ExampleReversePath(new Path(true, new Waypoint(0,0,0), new Waypoint(3,2,90)));

    private final Path path;

    AutoPath(Path path) {
        this.path = path;
    }

    public Path getPath() {
        return path;
    }
}
