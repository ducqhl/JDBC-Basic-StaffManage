/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author Backf
 */
public class Index {

    public static void main(String[] args) {
        int userCase = chooseFuntion();
        while (userCase != 0) {
            doTask(userCase);
        }
    }

    public static int chooseFuntion() {
        System.out.println("Choose funtionto do");
        System.out.println("1. Display Department List");
        System.out.println("2. Display Staff List of a Department");
        System.out.println("3. Add new Department");
        System.out.println("4. Add new Staff");
        System.out.println("5. Update information of a Staff");
        System.out.println("6. Show average salary");
        System.out.println("7. Show department statistic");
        System.out.println("0. Quit");

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int userCase = 0;
        try {
            System.out.print(">>> ");
            userCase = Integer.parseInt(in.readLine());
        } catch (Exception e) {
            System.out.println("Fail input, throws " + e);
        } finally {
            try {
                in.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        if (userCase < 0 || userCase > 7) {
            userCase = 0;
        }

        return userCase;
    }

    public static void doTask(int userCase) {
        switch (userCase) {
            case 0:
                break;
            case 1:
                System.out.println("1. Display Department List");
                DepartmentGUI.displayDepartments();
                break;
            case 2:
                System.out.println("2. Display Staff List of a Department");
                StaffGUI.displayStaffByDepartment();
                break;
            case 3:
                System.out.println("3. Add new Department");
                DepartmentGUI.adđDepartment();
                break;
            case 4:
                System.out.println("4. Add new Staff");
                StaffGUI.addNewStaff();
                break;
            case 5:
                System.out.println("5. Update information of a Staff");
                StaffGUI.updateStaffInformation();
                break;
            case 6:
                System.out.println("6. Show average salary");
                StaffGUI.viewAverageSalary();
                break;
            case 7:
                System.out.println("7. Show department statistic");
                DepartmentGUI.showStatistic();
                break;
            default:
                break;
        }
    }
}
