/**
 * Base decorator for alerts.
 * It wraps an existing alert and allows extra behavior to be added.
 */

package com.alerts.decorator;

import com.alerts.Alert;

public abstract class AlertDecorator extends Alert {

    protected Alert decoratedAlert;

    /**
 * Creates a decorator around an existing alert.
 *
 * @param alert the alert to decorate
 */

    public AlertDecorator(Alert alert) {
        super(alert.getPatientId(), alert.getCondition(), alert.getTimestamp());
        this.decoratedAlert = alert;
    }

    @Override
    public String getPatientId() {
        return decoratedAlert.getPatientId();
    }

    @Override
    public String getCondition() {
        return decoratedAlert.getCondition();
    }

    @Override
    public long getTimestamp() {
        return decoratedAlert.getTimestamp();
    }
}