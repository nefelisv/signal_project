package com.alerts.strategy;

import java.util.ArrayList;
import java.util.List;

import com.alerts.Alert;
import com.alerts.factory.BloodOxygenAlertFactory;
import com.alerts.factory.BloodPressureAlertFactory;
import com.data_management.Patient;
import com.data_management.PatientRecord;

public class OxygenSaturationStrategy implements AlertStrategy {

    private final BloodOxygenAlertFactory bloodOxygenFactory =
            new BloodOxygenAlertFactory();

    private final BloodPressureAlertFactory bloodPressureFactory =
            new BloodPressureAlertFactory();

    @Override
    public List<Alert> checkAlert(Patient patient, List<PatientRecord> records) {

        List<Alert> alerts = new ArrayList<>();

        for (PatientRecord record : records) {

            if (record.getRecordType().equals("Saturation")) {
                double value = record.getMeasurementValue();

                if (value < 92) {
                    alerts.add(
                            bloodOxygenFactory.createAlert(
                                    String.valueOf(patient.getPatientId()),
                                    "Low Blood Saturation",
                                    record.getTimestamp()
                            )
                    );
                }
            }
        }

        List<PatientRecord> saturationRecords = new ArrayList<>();

        for (PatientRecord record : records) {
            if (record.getRecordType().equals("Saturation")) {
                saturationRecords.add(record);
            }
        }

        for (int i = 1; i < saturationRecords.size(); i++) {
            double current = saturationRecords.get(i).getMeasurementValue();
            long currentTime = saturationRecords.get(i).getTimestamp();

            for (int j = i - 1; j >= 0; j--) {
                double previous = saturationRecords.get(j).getMeasurementValue();
                long previousTime = saturationRecords.get(j).getTimestamp();

                if (currentTime - previousTime <= 600000) {
                    if (previous - current >= 5) {
                        alerts.add(
                                bloodOxygenFactory.createAlert(
                                        String.valueOf(patient.getPatientId()),
                                        "Rapid Blood Saturation Drop",
                                        currentTime
                                )
                        );
                    }
                } else {
                    break;
                }
            }
        }

        boolean lowSystolic = false;
        boolean lowSaturation = false;
        long alertTime = 0;

        for (PatientRecord record : records) {

            if (record.getRecordType().equals("SystolicPressure")
                    && record.getMeasurementValue() < 90) {
                lowSystolic = true;
                alertTime = record.getTimestamp();
            }

            if (record.getRecordType().equals("Saturation")
                    && record.getMeasurementValue() < 92) {
                lowSaturation = true;
            }
        }

        if (lowSystolic && lowSaturation) {
            alerts.add(
                    bloodPressureFactory.createAlert(
                            String.valueOf(patient.getPatientId()),
                            "Hypotensive Hypoxemia",
                            alertTime
                    )
            );
        }

        return alerts;
    }
}