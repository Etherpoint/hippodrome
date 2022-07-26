import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

class HorseTest {
    @Test
    void whenNullThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
    }

    @Test
    void whenNullgetMessage() {
        try {
            new Horse(null, 1, 1);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be null.", e.getMessage());
        }

    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   ", "\t\t", "\n\n\n\n"})
    void whenBlankNameThrowException(String parameter) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> new Horse(parameter, 1, 1));
        assertEquals("Name cannot be blank.", e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Лошадь", "Пони", "Зевс"})
    void getName(String name) throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse(name, 1, 1);
        Field field = Horse.class.getDeclaredField("name");
        field.setAccessible(true);
        String value = (String) field.get(horse);
        assertEquals(name, value);
    }

    @ParameterizedTest
    @ValueSource(doubles = {10, 500, 822})
    void getSpeed(double speed) throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("Лошадка", speed, 1);
        Field field = Horse.class.getDeclaredField("speed");
        field.setAccessible(true);
        double value = (double) field.get(horse);
        assertEquals(speed, value);
    }

    @Test
    void getDistance() {
        Horse horse = new Horse("Лошадка", 1, 823);
        assertEquals(823, horse.getDistance());
    }
    @Test
    void whenNoDistanceGetZero(){
        Horse horse = new Horse("Лошадка", 1);
        assertEquals(0, horse.getDistance());
    }

    @Test
    void move() {
    }

    @Test
    void whenUseMoveUseGetRandomDoubleToo() {
        try(MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)){
            new Horse("Лошадка", 1, 823).move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
}