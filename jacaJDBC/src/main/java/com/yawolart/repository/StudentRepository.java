package com.yawolart.repository;

import com.yawolart.entity.Students;

import java.awt.dnd.DropTarget;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.LoggingPermission;

//methods in this class perform CRUD function
//here, SQL queries are not hard coded
//this is a better way to do CRUD
public class StudentRepository {
    //to create a table createStudentTable method in StudentRepositoryBasic
    //do the job perfectly

    //C - create
    public void addStudent(Students student){
        String url = "jdbc:mysql://localhost:3306/student_jdbc_demo";
        String username = LoginCredential.USERNAME;
        String password = LoginCredential.PASSWORD;
        //instead of hard code the SQL query here, taking the
        //student object as argument and will be put to String
        //using text block for java 15 & 16 or newer
        String sql = """
                INSERT INTO students (name,email,age) 
                VALUES('%s','%s',%d)
                """.formatted(
                        student.getName(),
                        student.getEmail(),
                        student.getAge()
                );

        //need to close the resource so use finally block or
        //try-with-resource
        //try-with- resource is recommended and its using here
        try(
                Connection connection = DriverManager
                        .getConnection(url,username,password);
                Statement statement = connection.createStatement();
                ){
            System.out.println("[addStudent] database connection successful");

            int result = statement.executeUpdate(sql);
            if(result <= 0)
                System.out.println("[addStudent] Failed to insert student data");
            else
                System.out.println("[addStudent] Row affected: " + result);

        }catch (SQLException ex){
            System.out.println("[addStudent] SQL error!");
            System.out.println("[addStudent] " + ex.getMessage());
        }
    }

    //R - read
    public void readStudentById(long id){
        String url = "jdbc:mysql://localhost:3306/student_jdbc_demo";
        String username = LoginCredential.USERNAME;
        String password = LoginCredential.PASSWORD;

        //using the Text block alone is also not good practice as
        //in some case data type cannot represent with formatted()
        //So, in this method we will use text block with PreparedStatement
        String sql = """
                SELECT id,name,email,age 
                FROM students
                WHERE id = ?
                """;

        try (
                Connection connection = DriverManager
                        .getConnection(url,username,password);
                PreparedStatement statement = connection.prepareStatement(sql);
                ){
            statement.setLong(1,id);
            System.out.println("[readStudentById] database connection successful");
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                System.out.print("[readStudentById] Data found: ");
                Students s1 = sqlToStudents(resultSet);
                System.out.println(s1);
            }
            else
                System.out.println("[readStudentById] Data not found");

        }catch (SQLException ex){
            System.out.println("[readStudentById] SQl error!");
            System.out.println("[readStudentById] " + ex.getMessage());
        }
    }

    public void readAllStudent(){
        String url = "jdbc:mysql://localhost:3306/student_jdbc_demo";
        String username = LoginCredential.USERNAME;
        String password = LoginCredential.PASSWORD;
        String sql = "SELECT id,name,email,age FROM students";

        try (
                Connection connection = DriverManager
                        .getConnection(url,username,password);
                Statement statement = connection.createStatement();
                ){
            System.out.println("[readAllStudent] databased connection successful");
            ResultSet resultSet = statement.executeQuery(sql);
            List<Students> students = new ArrayList<>();
            while(resultSet.next()){
                students.add(sqlToStudents(resultSet));
            }

            for(Students s : students){
                System.out.println(s);
            }

        }catch (SQLException ex){

        }
    }

    //U - update
    public void updateStudent(Students students, long id){
        String url = "jdbc:mysql://localhost:3306/student_jdbc_demo";
        String username = LoginCredential.USERNAME;
        String password = LoginCredential.PASSWORD;
        String sql = """
                UPDATE students 
                SET name = ? , email = ? , age = ? 
                WHERE id = ?
                """;

        try (
                Connection connection = DriverManager
                        .getConnection(url,username,password);
                PreparedStatement statement = connection
                        .prepareStatement(sql);
                ){
            System.out.println("[updateStudent] database connection successful");
            statement.setString(1, students.getName());
            statement.setString(2, students.getEmail());
            statement.setInt(3, students.getAge());
            statement.setLong(4,id);

            int result = statement.executeUpdate();

            if(result <= 0)
                System.out.println("[updateStudent] Data not found");
            else
                System.out.println("[updateStudent] Affected row: " + result);

        }catch (SQLException ex){
            System.out.println("[updateStudent] SQL error!");
            System.out.println("[updateStudent] " + ex.getMessage());
        }
    }

    //D - delete
    public void deleteStudent(long id) {
        String url = "jdbc:mysql://localhost:3306/student_jdbc_demo";
        String username = LoginCredential.USERNAME;
        String password = LoginCredential.PASSWORD;
        String sql = """
                DELETE FROM students 
                WHERE id = ?
                """;
        try(
                Connection connection = DriverManager
                        .getConnection(url,username,password);
                PreparedStatement statement = connection
                        .prepareStatement(sql);

                ){
            System.out.println("[deleteStudent] database connection successful");
            statement.setLong(1,id);

            int result = statement.executeUpdate();

            if(result <= 0)
                System.out.println("[deleteStudent] data not found");
            else
                System.out.println("[deleteStudent] Affected row: " + result);

        }catch (SQLException ex){
            System.out.println("[deleteStudent] SQL error!");
            System.out.println("[deleteStudent] " + ex.getMessage());
        }
    }


    //helper
    Students sqlToStudents(ResultSet r) throws SQLException {
        Students students = new Students();
        students.setId(r.getInt("id"));
        students.setName(r.getString("name"));
        students.setEmail(r.getString("email"));
        students.setAge(r.getInt("age"));
        return students;
    }
}
