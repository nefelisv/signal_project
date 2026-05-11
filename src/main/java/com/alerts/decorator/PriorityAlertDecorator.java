/**
 * Decorator that marks an alert as priority.
 */

package com.alerts.decorator;

import com.alerts.Alert;

public class PriorityAlertDecorator extends AlertDecorator {

    public PriorityAlertDecorator(Alert alert) {
        super(alert);
    }

    /**
 * Returns the alert condition with a priority tag.
 *
 * @return the decorated condition
 */

    @Override
    public String getCondition() {
        return "[PRIORITY] " + decoratedAlert.getCondition();
    }
}