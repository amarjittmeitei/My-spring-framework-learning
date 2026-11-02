package com.yawolart.repository;

import com.yawolart.entity.Students;

import java.sql.*;

//methods in this class perform basic crud activity
//all SQL queries are hard coded
public class StudentRepositoryBasic {


    public void createStudentTable()
    {
        String url = "jdbc:mysql://localhost:3306/student_jdbc_demo";
        String username = LoginCredential.USERNAME;
        String password = LoginCredential.PASSWORD;

        try{
            //establishing connection to database
            Connection connection = DriverManager
                    .getConnection(url,username,password);
            System.out.println("[createStudent] Database connection successful");

            //executing statement
            String sql = "CREATE TABLE students(" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(100)," +
                    "email VARCHAR(100)," +
                    "age INT NOT NULL" +
                    ")";

            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(sql);
            if(result == 0)
                System.out.println("[createStudent] Table created");
        }
        catch (SQLException ex)
        {
            System.out.println("[createStudent] SQL error!");
            System.out.println("[createStudent] " +ex.getMessage());
        }
    }

    //C - Create
    public void addStudentBasic()
    {
        String url =  "jdbc:mysql://localhost:3306/student_jdbc_demo";
        String username = LoginCredential.USERNAME;
        String password = LoginCredential.PASSWORD;

        try{
            Connection connection = DriverManager
                    .getConnection(url,username,password);
            System.out.println("[addStudentBasic] database connection successful");

            String sql = "INSERT INTO students (name, email, age) " +
                    "VALUES ('Thoi', 'thoi@yawolart.com', 24)";

            Statement statement = connection.createStatement();

            int result = statement.executeUpdate(sql);

            if(result <= 0)
                System.out.println("[addStudentBasic] Failed to insert student data");
            else
                System.out.println("[addStudentBasic] Row(s) affected: " + result);

        }catch (SQLException ex){
            System.out.println("[addStudentBasic] SQL error!");
            System.out.println("[addStudentBasic] " +ex.getMessage());
        }
    }

    //R- Read
    public void readStudentBasic(){
        String url = "jdbc:mysql://localhost:3306/student_jdbc_demo";
        String username = LoginCredential.USERNAME;
        String password = LoginCredential.PASSWORD;

        try{
            Connection connection = DriverManager
                    .getConnection(url,username,password);
            System.out.println("[readStudentBasic] database connection successful");
            Statement statement = connection.createStatement();
            String sql = "SELECT id,name,email,age " +
                    "FROM students " +
                    "WHERE id = 2";

            ResultSet resultSet =  statement.executeQuery(sql);
            if(resultSet.next()){
                Students s1 = new Students();
                s1.setId(resultSet.getLong("id"));
                s1.setName(resultSet.getString("name"));
                s1.setEmail(resultSet.getString("email"));
                s1.setAge(resultSet.getInt("age"));

                System.out.print("[readStudentBasic] data found: ");
                System.out.println(s1);
            }
            else
                System.out.println("[readStudentBasic] data not found");

        }catch (SQLException ex){
            System.out.println("[readStudentBasic] SQL error!");
            System.out.println("[readStudentBasic] " + ex.getMessage());
        }
    }

    //U - Update
    public void updateBasic()
    {
        String url = "jdbc:mysql://localhost:3306/student_jdbc_demo";
        String username = LoginCredential.USERNAME;
        String password = LoginCredential.PASSWORD;
        try{
            Connection connection = DriverManager
                    .getConnection(url,username,password);
            System.out.println("[updateBasic] database connection successful");

            Statement statement = connection.createStatement();

            String sql = "UPDATE students " +
                    "SET age = 24 " +
                    "WHERE id = 2";

            int result = statement.executeUpdate(sql);
            if(result <= 0)
                System.out.println("[updateBasic] Failed to update");
            else
                System.out.println("[updateBasic] Row(s) affected " + result);

        }catch (SQLException ex){
            System.out.println("updateBasic] SQL error!");
            System.out.println("[updateBasic] " + ex.getMessage());
        }
    }

    //D - Delete
    public void deleteBasic(){
        String url = "jdbc:mysql://localhost:3306/student_jdbc_demo";
        String username = LoginCredential.USERNAME;
        String password = LoginCredential.PASSWORD;

        try {
            Connection connection = DriverManager
                    .getConnection(url,username,password);
            System.out.println("[deleteBasic] databased connection successful");

            Statement statement = connection.createStatement();

            String sql = "DELETE FROM students " +
                    "WHERE id = 3";

            int result = statement.executeUpdate(sql);

            if(result <= 0)
                System.out.println("[deleteBasic] Deletion failed");
            else
                System.out.println("[deleteBasic] Row(s) affected: " + result);


        }catch (SQLException ex){
            System.out.println("[deleteBasic] SQL error!");
            System.out.println("[deleteBasic] " + ex.getMessage());
        }
    }
}
