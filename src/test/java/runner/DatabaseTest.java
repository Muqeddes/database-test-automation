package runner;

import databaseutilities.ApplicationConfig;
import databaseutilities.ConnectionManager;
import databaseutilities.QueryScript;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;


public class DatabaseTest extends ConnectionManager {
    Connection connection=null;
    QueryScript queryScript;

   @BeforeClass
    public void setup(){
       connection= connectToDatabase();
       queryScript=new QueryScript();

    }

    @Test
    public void getProductLineTest(){

       queryScript.geProductLine(ApplicationConfig.readFromConfig("ProductLine"), connection);

    }

    @Test
    public void getProductTest(){
       queryScript.getProduct(ApplicationConfig.readFromConfig("ProductName"),connection);
    }



    @AfterClass
    public void tearDown(){
       closeDatabaseConnection(connection);

    }
}
