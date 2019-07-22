package com.stackroute;

import com.sun.rowset.JdbcRowSetImpl;

import javax.sql.RowSetEvent;
import javax.sql.RowSetListener;
import javax.sql.rowset.JdbcRowSet;
import com.sun.rowset.CachedRowSetImpl;

import java.sql.*;

//import com.sun.rowset.JdbcRowSetImpl;
public class RowSetDemo
{
    private JdbcRowSet rowSet=null;
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;
    private static CachedRowSetImpl cachedRowSet;



    public static void getEmployeeDetailsUsingJdbcRowSet()
    {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "Root@123");)
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            resultSet=statement.executeQuery("select * from employee");
            try (JdbcRowSet rowSet = new JdbcRowSetImpl(connection)) {
                rowSet.setCommand("Select * from employee");
                rowSet.execute();
                rowSet.addRowSetListener(new RowSetListener() {
                    @Override
                    public void rowSetChanged(RowSetEvent event) {
                        System.out.println("rowset changed");
                    }

                    @Override
                    public void rowChanged(RowSetEvent event) {
                        System.out.println("row changed");
                    }

                    @Override
                    public void cursorMoved(RowSetEvent event) {
                        System.out.println("cursor moved");
                    }
                });
                while (rowSet.next()) {
                    System.out.println("Id:" + rowSet.getInt(1) + " Name " + rowSet.getString(2));
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void getEmployeeDetailsUsingCachedRowSet() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "Root@123");
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from employee");
            cachedRowSet=new CachedRowSetImpl();
            cachedRowSet.populate(resultSet);

            /*cachedRowSet.setUrl("jdbc:mysql://localhost:3306/EMPLOYEE");
            cachedRowSet.setUsername("root");
            cachedRowSet.setPassword("Root@123");
            cachedRowSet.setCommand("Select * from EMPLOYEE");
            cachedRowSet.setCommand("Select * from EMPLOYEE");
            cachedRowSet.execute();*/
            cachedRowSet.addRowSetListener(new ActionListerRowSet());
            while (cachedRowSet.next())
            {
                System.out.println("Id: " + cachedRowSet.getInt(1) + " Name: " + cachedRowSet.getString(2));

            }



        }catch (SQLException e){
            System.out.println(e);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }

    public RowSetDemo(JdbcRowSet rowSet, ResultSet resultSet) {
        this.rowSet = rowSet;
        this.resultSet = resultSet;
    }
}