package data_management;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.data_management.DataStorage;
import com.data_management.FileDataReader;
import com.data_management.PatientRecord;

public class FileDataReaderTest {
   @Test
    void testReadData() throws IOException{

        File  tempDr = new File("test_Output");
        tempDr.mkdir();
        File testFile = new File(tempDr , "test.txt");
        try(FileWriter writer = new FileWriter(testFile)){
            writer.write("1,98.6,BloodPressure,123450000000\n");
        }

        DataStorage storage = DataStorage.getInstance();
        FileDataReader reader = new FileDataReader(tempDr.getPath());
        reader.readData(storage);

        List<PatientRecord> records = storage.getRecords(1,123450000000L, 123450000000L);
        assertEquals(1, records.size());

        testFile.delete();
        tempDr.delete();

        









        
    }
    
}
