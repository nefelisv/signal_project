package data_management;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.alerts.Alert;
import com.alerts.BloodPressureAlert;
import com.alerts.decorator.PriorityAlertDecorator;
import com.alerts.decorator.RepeatedAlertDecorator;

public class AlertDecoratorTest {

    @Test
    void priorityDecoratorAddsPriorityTag() {
        Alert alert = new BloodPressureAlert(
                "1",
                "Critical Systolic Blood Pressure",
                1000L
        );

        Alert decoratedAlert = new PriorityAlertDecorator(alert);

        assertEquals(
                "[PRIORITY] Critical Systolic Blood Pressure",
                decoratedAlert.getCondition()
        );

        assertEquals("1", decoratedAlert.getPatientId());
        assertEquals(1000L, decoratedAlert.getTimestamp());
    }

    @Test
    void repeatedDecoratorAddsRepeatedTag() {
        Alert alert = new BloodPressureAlert(
                "1",
                "Critical Systolic Blood Pressure",
                1000L
        );

        Alert decoratedAlert = new RepeatedAlertDecorator(alert);

        assertEquals(
                "Critical Systolic Blood Pressure [REPEATED]",
                decoratedAlert.getCondition()
        );

        assertEquals("1", decoratedAlert.getPatientId());
        assertEquals(1000L, decoratedAlert.getTimestamp());
    }

    @Test
    void decoratorsCanBeCombined() {
        Alert alert = new BloodPressureAlert(
                "1",
                "Critical Systolic Blood Pressure",
                1000L
        );

        Alert decoratedAlert =
                new PriorityAlertDecorator(
                        new RepeatedAlertDecorator(alert)
                );

        assertEquals(
                "[PRIORITY] Critical Systolic Blood Pressure [REPEATED]",
                decoratedAlert.getCondition()
        );
    }
}