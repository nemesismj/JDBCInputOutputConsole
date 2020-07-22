package com.company;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
       input();
    }
    public static void input() throws SQLException{

        Scanner scan = new Scanner(System.in);
        System.out.println("1 for Input Data;2 for Output Data; and any other button exit");
        int k = scan.nextInt();

            if (k == 1) {
                DBInput();

            } else if (k == 2) {
                DBOutput();

            } else {
                System.out.println("System Exit");
                System.exit(0);
        }
    }
    public static void DBInput() throws SQLException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter First Name:");
        String firstName = scan.nextLine();
        System.out.println("Enter Last Name:");
        String lastName = scan.nextLine();
        PreparedStatement stmt = null;
        DBConnection db = new DBConnection();
        try{
            Statement st = db.getConnection().createStatement();
            String sql="insert into students(firstName,lastName) values(?, ?)";
            stmt = db.getConnection().prepareStatement(sql);
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.execute();
            System.out.println("Inserted Successfully");
            input();
        } catch (SQLException se){
            System.out.println(se.getMessage());
        }
    }
    public static void DBOutput() throws SQLException {
        DBConnection db = new DBConnection();
        try {
            Statement st = db.getConnection().createStatement();
            String query = "SELECT * FROM students";
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                Data s = new Data();
                s.setId(rs.getInt("id"));
                s.setFirstName(rs.getString("firstName"));
                s.setLastName(rs.getString("lastName"));
                System.out.println(s);
            }
            input();
        } catch (SQLException ex) {
            System.out.println("Failed");
        }
    }
}
