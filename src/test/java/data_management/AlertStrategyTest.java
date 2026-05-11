package data_management;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.alerts.Alert;
import com.alerts.strategy.BloodPressureStrategy;
import com.alerts.strategy.HeartRateStrategy;
import com.alerts.strategy.OxygenSaturationStrategy;
import com.data_management.Patient;
import com.data_management.PatientRecord;

public class AlertStrategyTest {

    @Test
    void bloodPressureStrategyCreatesCriticalSystolicAlert() {
        Patient patient = new Patient(1);

        List<PatientRecord> records = List.of(
                new PatientRecord(1, 190.0, "SystolicPressure", 1000L)
        );

        BloodPressureStrategy strategy = new BloodPressureStrategy();

        List<Alert> alerts = strategy.checkAlert(patient, records);

        assertEquals(1, alerts.size());
        assertEquals("Critical Systolic Blood Pressure",
                alerts.get(0).getCondition());
    }

    @Test
    void oxygenSaturationStrategyCreatesLowSaturationAlert() {
        Patient patient = new Patient(2);

        List<PatientRecord> records = List.of(
                new PatientRecord(2, 88.0, "Saturation", 1000L)
        );

        OxygenSaturationStrategy strategy = new OxygenSaturationStrategy();

        List<Alert> alerts = strategy.checkAlert(patient, records);

        assertEquals(1, alerts.size());
        assertEquals("Low Blood Saturation",
                alerts.get(0).getCondition());
    }

    @Test
    void heartRateStrategyCreatesAbnormalECGAlert() {
        Patient patient = new Patient(3);

        List<PatientRecord> records = List.of(
                new PatientRecord(3, 1.0, "EcG", 1L),
                new PatientRecord(3, 1.0, "EcG", 2L),
                new PatientRecord(3, 1.0, "EcG", 3L),
                new PatientRecord(3, 1.0, "EcG", 4L),
                new PatientRecord(3, 1.0, "EcG", 5L),
                new PatientRecord(3, 1.0, "EcG", 6L),
                new PatientRecord(3, 1.0, "EcG", 7L),
                new PatientRecord(3, 1.0, "EcG", 8L),
                new PatientRecord(3, 1.0, "EcG", 9L),
                new PatientRecord(3, 1.0, "EcG", 10L),
                new PatientRecord(3, 5.0, "EcG", 11L)
        );

        HeartRateStrategy strategy = new HeartRateStrategy();

        List<Alert> alerts = strategy.checkAlert(patient, records);

        assertEquals(1, alerts.size());
        assertEquals("Abnormal ECG",
                alerts.get(0).getCondition());
    }
}