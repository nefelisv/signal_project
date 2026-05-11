package data_management;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.cardio_generator.HealthDataSimulator;
import com.data_management.DataStorage;

public class SingletonTest {

    @Test
    void dataStorageReturnsSameInstance() {
        DataStorage storage1 = DataStorage.getInstance();
        DataStorage storage2 = DataStorage.getInstance();

        assertSame(storage1, storage2);
    }

    @Test
    void healthDataSimulatorReturnsSameInstance() {
        HealthDataSimulator simulator1 = HealthDataSimulator.getInstance();
        HealthDataSimulator simulator2 = HealthDataSimulator.getInstance();

        assertSame(simulator1, simulator2);
    }
}