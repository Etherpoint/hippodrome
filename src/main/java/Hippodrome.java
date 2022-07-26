import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Objects.isNull;

@SuppressWarnings("ClassCanBeRecord")
public class Hippodrome {

    public static final Logger LOGGER = LoggerFactory.getLogger(Hippodrome.class);
    private final List<Horse> horses;

    public Hippodrome(List<Horse> horses) {
        if (isNull(horses)) {
            LOGGER.error("Horses cannot be null.");
            throw new IllegalArgumentException("Horses cannot be null.");
        } else if (horses.isEmpty()) {
            LOGGER.error("Horses cannot be empty.");
            throw new IllegalArgumentException("Horses cannot be empty.");
        }

        this.horses = horses;
    }

    public List<Horse> getHorses() {
        return Collections.unmodifiableList(horses);
    }

    public void move() {
        horses.forEach(Horse::move);
    }
    public Horse getWinner() {
        //noinspection OptionalGetWithoutIsPresent
        return horses.stream()
                .max(Comparator.comparing(Horse::getDistance))
                .get();
    }
}
