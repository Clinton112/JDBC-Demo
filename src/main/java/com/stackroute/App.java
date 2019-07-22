package com.stackroute;

import javax.sql.rowset.JdbcRowSet;
import java.sql.ResultSet;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        SimpleJdbcDemo simpleJdbcDemo = new SimpleJdbcDemo();
        simpleJdbcDemo.getEmployeeDetails();
        System.out.println("\n");
        simpleJdbcDemo.getEmployeeDetailsInReverse();
        System.out.println("\n");
        simpleJdbcDemo.getEmployeeDetailsFromSecondRowInReverse();
        System.out.println("\n");
        simpleJdbcDemo.getEmployeeDetailsByNameAndGender("Mark","Female");

        RowSetDemo.getEmployeeDetailsUsingJdbcRowSet();
        RowSetDemo.getEmployeeDetailsUsingCachedRowSet();

    }
}
