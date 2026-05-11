package com.alerts.decorator;

import com.alerts.Alert;

public class RepeatedAlertDecorator extends AlertDecorator {

    public RepeatedAlertDecorator(Alert alert) {
        super(alert);
    }

    @Override
    public String getCondition() {
        return decoratedAlert.getCondition() + " [REPEATED]";
    }
}