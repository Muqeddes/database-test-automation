package runner;

import databaseutilities.ConnectionManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.sql.Connection;


public class DatabaseTest extends ConnectionManager {
    Connection connection=null;
   @Test
    public void setup(){
       connection= connectToDatabase();

    }

    @AfterClass
    public void tearDown(){
       closeDatabaseConnection(connection);
    }
}
