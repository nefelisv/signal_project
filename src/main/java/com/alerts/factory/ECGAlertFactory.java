/**
 * Factory for creating ECG alerts.
 */

package com.alerts.factory;

import com.alerts.Alert;
import com.alerts.ECGAlert;

public class ECGAlertFactory extends AlertFactory {

    /**
 * Creates a specific alert object for this factory type.
 *
 * @param patientId the id of the patient
 * @param condition the condition that caused the alert
 * @param timestamp the time when the alert happened
 * @return a new alert object
 */

    @Override
    public Alert createAlert(
            String patientId,
            String condition,
            long timestamp
    ) {

        return new ECGAlert(
                patientId,
                condition,
                timestamp
        );
    }
}