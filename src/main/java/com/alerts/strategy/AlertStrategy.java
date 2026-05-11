/**
 * Strategy interface for checking patient records and generating alerts.
 * Each implementation contains a different alert checking algorithm.
 */

package com.alerts.strategy;

import java.util.List;

import com.alerts.Alert;
import com.data_management.Patient;
import com.data_management.PatientRecord;

public interface AlertStrategy {

    /**
 * Checks patient records and returns alerts if any conditions are met.
 *
 * @param patient the patient being checked
 * @param records the patient records to evaluate
 * @return a list of generated alerts
 */

    List<Alert> checkAlert(
            Patient patient,
            List<PatientRecord> records
    );
}