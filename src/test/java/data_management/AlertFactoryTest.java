package data_management;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.alerts.Alert;
import com.alerts.BloodOxygenAlert;
import com.alerts.BloodPressureAlert;
import com.alerts.ECGAlert;
import com.alerts.factory.AlertFactory;
import com.alerts.factory.BloodOxygenAlertFactory;
import com.alerts.factory.BloodPressureAlertFactory;
import com.alerts.factory.ECGAlertFactory;

public class AlertFactoryTest {

    @Test
    void bloodPressureFactoryCreatesBloodPressureAlert() {

        AlertFactory factory = new BloodPressureAlertFactory();

        Alert alert = factory.createAlert(
                "1",
                "High BP",
                1000L
        );

        assertNotNull(alert);
        assertTrue(alert instanceof BloodPressureAlert);
        assertEquals("1", alert.getPatientId());
        assertEquals("High BP", alert.getCondition());
        assertEquals(1000L, alert.getTimestamp());
    }

    @Test
    void bloodOxygenFactoryCreatesBloodOxygenAlert() {

        AlertFactory factory = new BloodOxygenAlertFactory();

        Alert alert = factory.createAlert(
                "2",
                "Low Oxygen",
                2000L
        );

        assertNotNull(alert);
        assertTrue(alert instanceof BloodOxygenAlert);
        assertEquals("2", alert.getPatientId());
        assertEquals("Low Oxygen", alert.getCondition());
        assertEquals(2000L, alert.getTimestamp());
    }

    @Test
    void ecgFactoryCreatesECGAlert() {

        AlertFactory factory = new ECGAlertFactory();

        Alert alert = factory.createAlert(
                "3",
                "Abnormal ECG",
                3000L
        );

        assertNotNull(alert);
        assertTrue(alert instanceof ECGAlert);
        assertEquals("3", alert.getPatientId());
        assertEquals("Abnormal ECG", alert.getCondition());
        assertEquals(3000L, alert.getTimestamp());
    }
}