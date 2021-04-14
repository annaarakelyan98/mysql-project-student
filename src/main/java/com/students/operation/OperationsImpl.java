package com.students.operation;

import com.students.connection.Connections;
import com.students.model.Students;
import com.students.validation.ValidationImpl;
import com.students.validation.Validations;

import java.sql.*;
import java.util.Scanner;

public class OperationsImpl implements Operations {
    Scanner scanner = new Scanner(System.in);

    String INSERT_INTO = "INSERT INTO students values(?,?,?,?,?)";
    String GET_All = "SELECT * FROM students";
    String DELETE = "DELETE FROM students";
    String GET_BY_ID = "SELECT * FROM students where id = ?";
    String DELETE_BY_ID = "DELETE FROM students WHERE id = ?";

    @Override
    public void insertInto() {
        Students students = new Students();

        Validations validations = new ValidationImpl();
        try {
            Connection connection = Connections.getObj();
            PreparedStatement obj = connection.prepareStatement(INSERT_INTO);

            System.out.println("Enter data!");

            System.out.print("name: ");
            students.setName(scanner.nextLine());

            System.out.print("surname: ");
            students.setSurname(scanner.nextLine());

            System.out.print("city: ");
            students.setCity(scanner.nextLine());

            System.out.print("email: ");
            students.setMail(scanner.nextLine());

            System.out.print("id: ");
            students.setId(scanner.nextInt());

            boolean valid = validations.valid(students);

            if (valid) {
                System.out.println("You have presented exceptions");
            } else { setStudentsData(students, obj);
                obj.execute();
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public void getAll() {
        Connection connection = Connections.getObj();
        Students students = new Students();
        try {
            PreparedStatement obj = connection.prepareStatement(GET_All);
            ResultSet resultSet = obj.executeQuery();

            while (resultSet.next()) {
                System.out.println(getStudentsData(students, resultSet));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public void getById() {
        Connection connection = Connections.getObj();
        Students students = new Students();
        try {
            System.out.print("Enter id: ");
            int i = scanner.nextInt();

            PreparedStatement obj = connection.prepareStatement(GET_BY_ID);

            obj.setInt(1, i);

            ResultSet resultSet = obj.executeQuery();

            while (resultSet.next()) {
                System.out.println(getStudentsData(students, resultSet));
            }

            if (students.getId() != i) {
                System.out.println("id not exists!");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void delete() {
        Connection connection = Connections.getObj();
        try {
            PreparedStatement obj = connection.prepareStatement(DELETE);
            obj.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public void deleteById() {
        Connection connection = Connections.getObj();
        try {
            PreparedStatement obj = connection.prepareStatement(DELETE_BY_ID);

            System.out.print("Enter id: ");
            int index = scanner.nextInt();

            obj.setInt(1, index);

            obj.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public void exit() {
        System.out.println("Exit!");
    }

    private static Students getStudentsData(Students students, ResultSet resultSet) {
        try {
            students.setId(resultSet.getInt("id"));
            students.setName(resultSet.getString("Name"));
            students.setSurname(resultSet.getString("Surname"));
            students.setCity(resultSet.getString("City"));
            students.setMail(resultSet.getString("Email"));

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return students;
    }

    private static void setStudentsData(Students students, PreparedStatement obj) {
        try {
            obj.setInt(1, students.getId());
            obj.setString(2, students.getName());
            obj.setString(3, students.getSurname());
            obj.setString(4, students.getCity());
            obj.setString(5, students.getMail());

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
