package databaseutilities;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;

public class QueryScript {
    public void geProductLine(String productLineName, Connection connection) {
        //Get products line

        Statement statement;
        ResultSet resultSet;
        CachedRowSet cachedRowSet;

        try {
            cachedRowSet = RowSetProvider.newFactory().createCachedRowSet();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String selectProductLine = String.format("select * from products where productLine='%s'", productLineName);
        try {
            resultSet = statement.executeQuery(selectProductLine);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            cachedRowSet.populate(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //Verify result

        int count = 0;

        if (resultSet == null) {
            System.out.println("No records found!!!");
        } else {

            while (true) {

                try {
                    if (cachedRowSet.next())
                        count++;
                    else break;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("The product line exists in the database");
            System.out.println("Total number of products in this product line is: " + count);

        }

    }

    public void getProduct(String productName, Connection connection) {

        Statement statement;
        ResultSet resultSet;
        CachedRowSet cachedRowSet;

        try {
            cachedRowSet = RowSetProvider.newFactory().createCachedRowSet();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String selectProduct = String.format("select * from products where productName='%s'", productName);

        try {
            resultSet = statement.executeQuery(selectProduct);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //verify the result set
        if (resultSet == null) {
            System.out.println("No records found!!!");
        } else {
            try {
                cachedRowSet.populate(resultSet);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            int count = 0;
            String name = null;
            while (true) {

                try {
                    if (!cachedRowSet.next()) {
                        break;
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                try {
                    String productCode = cachedRowSet.getString("productCode");
                    name = cachedRowSet.getString("productName");
                    String productLine = cachedRowSet.getString("productLine");
                    String productDescription = cachedRowSet.getString("productDescription");
                    int quantityInStock = cachedRowSet.getInt("quantityInStock");
                    double price = cachedRowSet.getDouble("buyPrice");

                    System.out.printf(" Product_Code: %s \n Product_Name: %s \n Product_Line: %s \n Product_Description: %s \n Quantity: %d \n Price: %.2f \n",
                            productCode, name, productLine, productDescription, quantityInStock, price);
                    count = cachedRowSet.getRow();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if (count >= 1 && name.equalsIgnoreCase(productName))
                System.out.println("Product exists in the database.");
        }


    }
}





