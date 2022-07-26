import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.Objects.isNull;

public class Horse {
    public static final Logger LOGGER = LoggerFactory.getLogger(Horse.class);

    private final String name;
    private final double speed;
    private double distance;

    public Horse(String name, double speed, double distance) {
        if (isNull(name)) {
            LOGGER.error("Name cannot be null");
            throw new IllegalArgumentException("Name cannot be null.");

        } else if (name.isBlank()) {
            LOGGER.error("Name cannot be blank.");
            throw new IllegalArgumentException("Name cannot be blank.");
        }
        if (speed < 0) {
            LOGGER.error("Speed cannot be negative.");
            throw new IllegalArgumentException("Speed cannot be negative.");
        }
        if (distance < 0) {
            LOGGER.error("Distance cannot be negative.");
            throw new IllegalArgumentException("Distance cannot be negative.");
        }

        this.name = name;
        this.speed = speed;
        this.distance = distance;
    }

    public Horse(String name, double speed) {
        this(name, speed, 0);
    }

    public String getName() {
        return name;
    }

    @SuppressWarnings({"unused"})
    public double getSpeed() {
        return speed;
    }

    public double getDistance() {
        return distance;
    }

    public void move() {
        distance += speed * getRandomDouble(0.2, 0.9);
    }

    public static double getRandomDouble(double min, double max) {
        return (Math.random() * (max - min)) + min;
    }
}
