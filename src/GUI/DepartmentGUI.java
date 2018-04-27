/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.DepartmentBUS;
import DTO.Department;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Backf
 */
public class DepartmentGUI {

    public static void adđDepartment() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String dpmName = "";
        Integer dpmID;

        System.out.print("Enter Department Name: ");
        try {
            dpmName = in.readLine();
        } catch (IOException e) {
            System.out.println("Fail when read line, throws :" + e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                System.out.println("Fail when close buffer reader, throws: " + e);
            }
        }

        Department newDepartment = new Department(0, dpmName);
        //adđ new item
        dpmID = DepartmentBUS.addDepartment(newDepartment);

        if (dpmID != null) {
            System.out.println("ADD NEW DEPARTMENT SUCCESS, ID = " + dpmID);
        } else {
            System.out.println("ADD NEW DEPARTMENT FAIL");
        }
    }

    public static void displayDepartments() {
        ArrayList<Department> dpmList = DepartmentBUS.getDepartments();

        for (int i = 0; i < dpmList.size(); ++i) {
            Department dpmItem = dpmList.get(i);
            System.out.println(dpmItem);
        }
    }

    public static void showStatistic() {
        ArrayList<Department> dpmList = DepartmentBUS.getDepartments();

        System.out.println("Department Statistic:");
        for (int i = 0; i < dpmList.size(); ++i) {
            Department dpmItem = dpmList.get(i);
            Integer n = DepartmentBUS.getNumberOfStaffs(dpmItem.getDepartmentID());
            System.out.println(dpmItem.getDepartmentName() + ": " + n + " staffs");
        }
    }
}
