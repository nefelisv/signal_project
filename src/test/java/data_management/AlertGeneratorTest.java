package data_management;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.alerts.Alert;
import com.alerts.AlertGenerator;
import com.data_management.DataStorage;
import com.data_management.Patient;

public class AlertGeneratorTest {

    @Test
    void testCriticalSystolicBloodPressure(){
        Patient patient = new Patient(1);
        patient.addRecord(185.0, "SystolicPressure", 1000);
        AlertGenerator generator = new AlertGenerator(DataStorage.getInstance());
        List <Alert> alerts = generator.getTriggeredAlerts();
        generator.evaluateData(patient);
        assertTrue(alerts.stream().anyMatch( a-> a.getCondition().equals("Critical Systolic Blood Pressure")));
    }


    @Test
    void testCriticalDiastolicBloodPressure(){
        Patient patient = new Patient(1);
        patient.addRecord(125.0, "DiastolicPressure", 1000);
        AlertGenerator generator = new AlertGenerator(DataStorage.getInstance());
         generator.evaluateData(patient);
        List <Alert> alerts = generator.getTriggeredAlerts();
        assertTrue(alerts.stream().anyMatch( a-> a.getCondition().equals("Critical Diastolic Blood Pressure")));
    }
    @Test
    void testIncreasingSystolicTrend(){
        Patient patient = new Patient(1);
        patient.addRecord(100.0, "SystolicPressure", 1000);
         patient.addRecord(115.0, "SystolicPressure", 2000);
         patient.addRecord(130.0, "SystolicPressure", 3000);
        AlertGenerator generator = new AlertGenerator(DataStorage.getInstance());
        generator.evaluateData(patient);
        List <Alert> alerts = generator.getTriggeredAlerts();
        assertTrue(alerts.stream().anyMatch( a-> a.getCondition().equals("Increasing Blood Pressure Trend")));
    }
    @Test
    void testLowBloodSaturation(){
        Patient patient = new Patient(1);
        patient.addRecord(88.0, "Saturation", 1000);
        AlertGenerator generator = new AlertGenerator(DataStorage.getInstance());
        generator.evaluateData(patient);
        List <Alert> alerts = generator.getTriggeredAlerts();
        assertTrue(alerts.stream().anyMatch( a-> a.getCondition().equals("Low Blood Saturation")));
    }
     @Test
    void testRapidSaturationDrop(){
        Patient patient = new Patient(1);
        patient.addRecord(98.0, "Saturation", 1000);
        patient.addRecord(92.0, "Saturation", 200000);
        AlertGenerator generator = new AlertGenerator(DataStorage.getInstance());
        generator.evaluateData(patient);
        List <Alert> alerts = generator.getTriggeredAlerts();
        assertTrue(alerts.stream().anyMatch( a-> a.getCondition().equals("Rapid Blood Saturation Drop")));
    }

        @Test
    void ttestHypotensiveHypoxemia(){
        Patient patient = new Patient(1);
        patient.addRecord(85.0, "SystolicPressure", 1000);
        patient.addRecord(88.0, "Saturation", 1000);
        AlertGenerator generator = new AlertGenerator(DataStorage.getInstance());
        generator.evaluateData(patient);
        List <Alert> alerts = generator.getTriggeredAlerts();
        assertTrue(alerts.stream().anyMatch( a-> a.getCondition().equals("Hypotensive Hypoxemia")));
     }     
    }
    
    

