/**
 * Factory for creating blood pressure alerts.
 */

package com.alerts.factory;

import com.alerts.Alert;
import com.alerts.BloodPressureAlert;

public class BloodPressureAlertFactory extends AlertFactory {

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

        return new BloodPressureAlert(
                patientId,
                condition,
                timestamp
        );
    }
}