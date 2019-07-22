package com.stackroute;
import java.sql.*;
import java.io.*;
class JdbcTransactionDemo
{
    public static void main(String args[])
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/data","root","Root@123");
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement=connection.prepareStatement("insert into employee values(?,?,?,?)");

            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
            while(true)
            {

                System.out.println("enter id");
                String stringId=bufferedReader.readLine();
                int id=Integer.parseInt(stringId);

                System.out.println("enter name");
                String stringName=bufferedReader.readLine();

                System.out.println("enter age");
                String stringAge=bufferedReader.readLine();
                int salary=Integer.parseInt(stringAge);

                System.out.println("enter Gender");
                String stringGender=bufferedReader.readLine();

                preparedStatement.setInt(1,id);
                preparedStatement.setString(2,stringName);
                preparedStatement.setInt(3,salary);
                preparedStatement.setString(4,stringGender);
                preparedStatement.executeUpdate();

                System.out.println("commit/rollback");
                String answer=bufferedReader.readLine();
                if(answer.equals("commit"))
                {
                    connection.commit();
                }
                if(answer.equals("rollback"))
                {
                    connection.rollback();
                }


                System.out.println("Want to add more records y/n");
                String stringAnswer=bufferedReader.readLine();
                if(stringAnswer.equals("n"))
                {
                    break;
                }

            }
            connection.commit();
            System.out.println("record successfully saved");

            connection.close();//before closing connection commit() is called
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }
}