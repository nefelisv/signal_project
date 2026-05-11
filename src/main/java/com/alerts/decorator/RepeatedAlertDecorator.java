/**
 * Decorator that marks an alert as repeated.
 */

package com.alerts.decorator;

import com.alerts.Alert;

public class RepeatedAlertDecorator extends AlertDecorator {

    public RepeatedAlertDecorator(Alert alert) {
        super(alert);
    }

    /**
 * Returns the alert condition with a repeated tag.
 *
 * @return the decorated condition
 */

    @Override
    public String getCondition() {
        return decoratedAlert.getCondition() + " [REPEATED]";
    }
}