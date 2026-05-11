/**
 * Base factory class for creating alerts.
 * Subclasses decide which specific alert type should be created.
 */

package com.alerts.factory;

import com.alerts.Alert;

/**
 * Creates an alert for a patient condition.
 *
 * @param patientId the id of the patient
 * @param condition the condition that caused the alert
 * @param timestamp the time when the alert happened
 * @return a new alert object
 */

public abstract class AlertFactory {

    public abstract Alert createAlert(
            String patientId,
            String condition,
            long timestamp
    );
}