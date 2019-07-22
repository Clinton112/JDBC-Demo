package com.stackroute;

import java.sql.*;

public class ResultSetMetadataDemo
{
    static
    {
        //Registering The Driver Class

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Unable To Load The Driver class");
        }
    }

    public static void main(String[] args)
    {
        Connection connection = null;

        Statement statement = null;

        ResultSet resultSet = null;

        try
        {
            //Database Credentials

            String URL = "jdbc:mysql://localhost:3306/data";

            String username = "root";

            String password = "Root@123";

            //Creating The Connection Object

            connection = DriverManager.getConnection(URL, username, password);

            //Creating The Statement Object

            statement = connection.createStatement();

            //Constructing The SQL Query

            String sql = "SELECT * FROM employee";

            //Executing The Query

            resultSet = statement.executeQuery(sql);

            //getting ResultSetMetaData object

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            //getting number of columns in 'rs'

            int colCount = resultSetMetaData.getColumnCount();

            System.out.println("Number Of Columns : "+colCount);

            System.out.println("Column Details :");

            for (int i = 1; i <= colCount; i++)
            {
                //getting column name of index 'i'

                String colName = resultSetMetaData.getColumnName(i);

                //getting column's data type of index 'i'

                String colType = resultSetMetaData.getColumnTypeName(i);

                System.out.println(colName+" is of type "+colType);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            //Closing The DB Resources

            //Closing the ResultSet object

            try
            {
                if(resultSet!=null)
                {
                    resultSet.close();
                    resultSet=null;

                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }

            //Closing the Statement object
            try
            {
                if(statement!=null)
                {
                    statement.close();
                    statement=null;
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }

            //Closing the Connection object

            try
            {
                if(connection!=null)
                {
                    connection.close();
                    connection=null;
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
}
