/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.StaffBUS;
import DTO.Staff;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Backf
 */
public class StaffGUI {

    public static void displayStaffList() {
        ArrayList<Staff> StaffList = new ArrayList<>();
        StaffList = StaffBUS.getStaffList();

        for (int i = 0; i < StaffList.size(); ++i) {
            Staff staff = StaffList.get(i);
            System.out.println(staff);
        }
    }

    public static void displayStaffByDepartment() {
        ArrayList<Staff> StaffList = new ArrayList<>();
        String departmentName = "";
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        //input data
        try {
            System.out.print("Enter Department Name: ");
            departmentName = in.readLine();
        } catch (IOException e) {
            System.out.println("Fail when input data, throws " + e);
        }

        if ("".equals(departmentName)) {
            System.out.println("Department Name is require");
            return;
        }

        StaffList = StaffBUS.getStaffOfDepartment(departmentName);

        for (int i = 0; i < StaffList.size(); ++i) {
            Staff staff = StaffList.get(i);
            if (true) {
                System.out.println(staff);
            }
        }
    }

    public static void addNewStaff() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Staff staff = new Staff();

        try {
            System.out.print("Enter Staff ID: ");
            staff.setStaffID(in.readLine());
            System.out.print("Enter Staff Name: ");
            staff.setStaffName(in.readLine());
            System.out.print("Enter Staff Sex ('1' for 'Male' and '0' for 'Female'): ");
            staff.setSex("1".equals(in.readLine()));
            System.out.print("Enter Staff DOB (yyyy-MM-dd): ");
            staff.setDOB(Date.valueOf(in.readLine()));
            System.out.print("Enter Staff Address: ");
            staff.setAddress(in.readLine());
            System.out.print("Enter Staff Salary: ");
            staff.setSalary(Integer.parseInt(in.readLine()));
            System.out.print("Enter Staff Department ID: ");
            staff.setDepartment(Integer.parseInt(in.readLine()));
        } catch (IOException e) {
            System.out.println("Fail when close buffer reader, throws: " + e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        //adđ new item
        String staffID = StaffBUS.addStaff(staff);

        if (staffID != null) {
            System.out.println("ADD NEW STAFF SUCCESS, ID = " + staffID);
        } else {
            System.out.println("ADD NEW STAFF FAIL");
        }
    }

    public static void updateStaffInformation() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Staff staff = null;
        Staff old_staff = null;

        String temp_str = "";

        try {
            //checking if input Staff is exists
            System.out.print("Enter Staff ID (require): ");
            temp_str = in.readLine();
            if (!"".equals(temp_str)) {
                staff = StaffBUS.getStaff(temp_str);
                if (staff == null) {
                    System.out.println("This Staff is not exists!");
                    return;
                }
                System.out.println(staff);
                old_staff = new Staff(staff);
            } else {
                System.out.println("StaffID is require!");
                return;
            }

            //Staff is exists, now change it information
            System.out.println("Input new data, press 'Enter' to skip");
            System.out.print("Name: ");
            temp_str = in.readLine();
            if (!"".equals(temp_str)) {
                staff.setStaffName(temp_str);
            }
            System.out.print("Sex ('1' for 'Male' and '0' for 'Female'): ");
            temp_str = in.readLine();
            if (!"".equals(temp_str)) {
                staff.setSex("1".equals(temp_str));
            }
            System.out.print("DOB (yyyy-MM-dd): ");
            temp_str = in.readLine();
            if (!"".equals(temp_str)) {
                staff.setDOB(Date.valueOf(temp_str));
            }
            System.out.print("Address: ");
            temp_str = in.readLine();
            if (!"".equals(temp_str)) {
                staff.setAddress(temp_str);
            }
            System.out.print("Salary: ");
            temp_str = in.readLine();
            if (!"".equals(temp_str)) {
                staff.setSalary(Integer.parseInt(in.readLine()));
            }
            System.out.print("Department ID: ");
            temp_str = in.readLine();
            if (!"".equals(temp_str)) {
                staff.setDepartment(Integer.parseInt(temp_str));
            }
        } catch (IOException e) {
            System.out.println("Fail when close buffer reader, throws: " + e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        if (staff != null && old_staff != null && staff.equals(old_staff)) {
            System.out.println("Nothing change!");
            return;
        }

        //update item
        String staffID = StaffBUS.updateStaff(staff);

        if (staffID != null) {
            System.out.println("UPDATE STAFF SUCCESS, ID = " + staffID);
        } else {
            System.out.println("UPDATE STAFF FAIL");
        }
    }

    public static void viewAverageSalary() {
        System.out.println("Average salary: " + StaffBUS.getAverageSalary());
    }
}
