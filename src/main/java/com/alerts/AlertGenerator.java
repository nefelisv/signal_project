package com.alerts;

import java.util.ArrayList;
import java.util.List;

import com.alerts.factory.BloodPressureAlertFactory;
import com.alerts.factory.BloodOxygenAlertFactory;
import com.alerts.factory.ECGAlertFactory;
import com.data_management.DataStorage;
import com.data_management.Patient;
import com.data_management.PatientRecord;

/**
 * The {@code AlertGenerator} class is responsible for monitoring patient data
 * and generating alerts when certain predefined conditions are met. This class
 * relies on a {@link DataStorage} instance to access patient data and evaluate
 * it against specific health criteria.
 */
public class AlertGenerator {

    private DataStorage dataStorage;
    private List<Alert> triggeredAlerts = new ArrayList<>();


    //FACTORIES
    private final BloodPressureAlertFactory bloodPressureFactory =
    new BloodPressureAlertFactory();

    private final BloodOxygenAlertFactory bloodOxygenFactory =
    new BloodOxygenAlertFactory();

    private final ECGAlertFactory ecgFactory =
    new ECGAlertFactory();

    /**
     * Constructs an {@code AlertGenerator} with a specified {@code DataStorage}.
     * The {@code DataStorage} is used to retrieve patient data that this class
     * will monitor and evaluate.
     *
     * @param dataStorage the data storage system that provides access to patient
     *                    data
     */
    public AlertGenerator(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    /**
     * Evaluates the specified patient's data to determine if any alert conditions
     * are met. If a condition is met, an alert is triggered via the
     * {@link #triggerAlert}
     * method. This method should define the specific conditions under which an
     * alert
     * will be triggered.
     *
     * @param patient the patient data to evaluate for alert conditions
     */
    public void evaluateData(Patient patient) {
        List<PatientRecord> records = patient.getRecords(0, Long.MAX_VALUE);
        
        for(PatientRecord record : records){
            if(record.getRecordType().equals("SystolicPressure")){
                double value = record.getMeasurementValue();

                if(value >180 || value <90){
                    triggerAlert(
                            bloodPressureFactory.createAlert(
                                    String.valueOf(patient.getPatientId()),
                                    "Critical Systolic Blood Pressure",
                                    record.getTimestamp()
                            ) 
                        );
                }
            }
            if(record.getRecordType().equals("DiastolicPressure")){
                double value2 = record.getMeasurementValue();
                if(value2>120 || value2<60){
                    triggerAlert(
                            bloodPressureFactory.createAlert(
                                    String.valueOf(patient.getPatientId()),
                                    "Critical Diastolic Blood Pressure",
                                    record.getTimestamp()
                            )
                        );
                }
            }
        }

       
     List <PatientRecord> systolicRecords = new ArrayList<>();
       for(PatientRecord record: records){
            if(record.getRecordType().equals("SystolicPressure")){ 
             systolicRecords.add(record);
        }
    }
    for(int i = 2; i<systolicRecords.size(); i++){
        double first = systolicRecords.get(i-2).getMeasurementValue();
        double second =systolicRecords.get(i-1).getMeasurementValue();
        double third = systolicRecords.get(i).getMeasurementValue();

        if((second - first >10) && (third - second >10)){ 
            triggerAlert(
                        bloodPressureFactory.createAlert(
                                String.valueOf(patient.getPatientId()),
                                "Increasing Blood Pressure Trend",
                                systolicRecords.get(i).getTimestamp()
                        )
                    );
         }
        if((first- second > 10) && (second - third >10)){ 
            triggerAlert(
                        bloodPressureFactory.createAlert(
                                String.valueOf(patient.getPatientId()),
                                "Decreasing Blood Pressure Trend",
                                systolicRecords.get(i).getTimestamp()
                        )
                );
            }
         }

          List <PatientRecord> diastolicRecords = new ArrayList<>();
        for(PatientRecord record: records){
            if(record.getRecordType().equals("DiastolicPressure")){ 
              diastolicRecords.add(record);
        }
    }
    for(int i = 2; i<diastolicRecords.size(); i++){
        double first = diastolicRecords.get(i-2).getMeasurementValue();
        double second =diastolicRecords.get(i-1).getMeasurementValue();
        double third = diastolicRecords.get(i).getMeasurementValue();

        if((second - first >10) && (third - second >10)){ 
            triggerAlert(
                        bloodPressureFactory.createAlert(
                                String.valueOf(patient.getPatientId()),
                                "Increasing Diastolic Trend",
                                diastolicRecords.get(i).getTimestamp()
                        )
                );
         }
        if((first- second > 10) && (second - third >10)){ 
                            triggerAlert(
                        bloodPressureFactory.createAlert(
                                String.valueOf(patient.getPatientId()),
                                "Decreasing Diastolic Trend",
                                diastolicRecords.get(i).getTimestamp()
                        )
                );
            }
         }
         for(PatientRecord record:records){
            if(record.getRecordType().equals("Saturation")){
                double value = record.getMeasurementValue();
                  if(value<92){
                   triggerAlert(
                            bloodOxygenFactory.createAlert(
                                    String.valueOf(patient.getPatientId()),
                                    "Low Blood Saturation",
                                    record.getTimestamp()
                            )
                    );
                  }
               }
            }


            

     List <PatientRecord> saturationRecords = new ArrayList<>();
       for(PatientRecord record: records){
            if(record.getRecordType().equals("Saturation")){ 
              saturationRecords.add(record);
         }
       }
    for(int i = 1; i<saturationRecords.size(); i++){
       double current = saturationRecords.get(i).getMeasurementValue();
       long currentTime = saturationRecords.get(i).getTimestamp();

       for(int j =i -1; j>=0;j--){
        double previous = saturationRecords.get(j).getMeasurementValue();
       long previousTime= saturationRecords.get(j).getTimestamp();
       if(currentTime - previousTime <= 600000){
        if(previous -current >= 5){
            triggerAlert(
                                bloodOxygenFactory.createAlert(
                                        String.valueOf(patient.getPatientId()),
                                        "Rapid Blood Saturation Drop",
                                        currentTime
                                )
                        );
        }
       }else{
        break;
             }
           }
        }
       boolean lowSystolic = false;
       boolean lowSaturation = false;
       long alertTime = 0;
       for(PatientRecord record: records){
        if(record.getRecordType().equals("SystolicPressure")&& record.getMeasurementValue()<90){
            lowSystolic = true;
            alertTime = record.getTimestamp();
        }
        if(record.getRecordType().equals("Saturation") && record.getMeasurementValue()<92){
            lowSaturation = true;
        }
       }
       if(lowSystolic &&lowSaturation){
        triggerAlert(
                    bloodPressureFactory.createAlert(
                            String.valueOf(patient.getPatientId()),
                            "Hypotensive Hypoxemia",
                            alertTime
                    )
            );
       }
       //EGG alert
       List<PatientRecord> ecgRecords = new ArrayList<>();
        for(PatientRecord record :records){
            if(record.getRecordType().equals("EcG")){
                ecgRecords.add(record);
            }
        }
        double sum = 0;
        int windowSize = 10;
         for(int i =0; i<ecgRecords.size(); i++){
            sum+= ecgRecords.get(i).getMeasurementValue();
            if(i>= windowSize){
                sum -=ecgRecords.get(i-windowSize).getMeasurementValue();
            double average = sum / windowSize;
            if(ecgRecords.get(i).getMeasurementValue() > average *2){
           triggerAlert(
                            ecgFactory.createAlert(
                                    String.valueOf(patient.getPatientId()),
                                    "Abnormal ECG",
                                    ecgRecords.get(i).getTimestamp()
                            )
                    );
                }
               }
         }



        }
     
    

    /**
     * Triggers an alert for the monitoring system. This method can be extended to
     * notify medical staff, log the alert, or perform other actions. The method
     * currently assumes that the alert information is fully formed when passed as
     * an argument.
     *
     * @param alert the alert object containing details about the alert condition
     */
    private void triggerAlert(Alert alert) {
        triggeredAlerts.add(alert);
    System.out.println("patientID :" +alert.getPatientId());
    System.out.println("Condition :" +alert.getCondition());
    System.out.println("Time stamp" +alert.getTimestamp());
    }
    public List<Alert> getTriggeredAlerts() {
    return triggeredAlerts;
    }

}
