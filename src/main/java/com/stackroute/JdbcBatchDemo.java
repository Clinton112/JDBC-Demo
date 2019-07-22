package com.stackroute;
import java.sql.*;
import java.io.*;
class JdbcBatchDemo
{
    public static void main(String args[])
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/data","root","Root@123");

            PreparedStatement preparedStatement=connection.prepareStatement("insert into employee values(?,?,?,?)");

            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
            while(true)
            {

                System.out.println("Enter ID");
                String stringId=bufferedReader.readLine();
                int id=Integer.parseInt(stringId);

                System.out.println("Enter Name");
                String stringName=bufferedReader.readLine();

                System.out.println("Enter Age");
                String stringSalary=bufferedReader.readLine();
                int salary=Integer.parseInt(stringSalary);

                System.out.println("Enter Gender");
                String stringGender=bufferedReader.readLine();

                preparedStatement.setInt(1,id);
                preparedStatement.setString(2,stringName);
                preparedStatement.setInt(3,salary);
                preparedStatement.setString(4,stringGender);

                preparedStatement.addBatch();
                System.out.println("Want to add more records (y/n)");
                String ans=bufferedReader.readLine();
                if(ans.equals("n")||(ans.equals("N")))
                {
                    break;
                }

            }
            preparedStatement.executeBatch();

            System.out.println("record successfully saved");

            connection.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }
}
