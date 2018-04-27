/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import java.util.ArrayList;
import DTO.Staff;
import DAO.StaffDB;

/**
 *
 * @author Backf
 */
public class StaffBUS {

    /**
     * Display list of staff
     *
     * @return
     */
    public static ArrayList<Staff> getStaffList() {
        return StaffDB.find_all();
    }

    public static ArrayList<Staff> getStaffOfDepartment(String departmentName) {
        if (departmentName == null || "".equals(departmentName)) {
            return null;
        }
        return StaffDB.findByDepartment(departmentName);
    }

    /**
     *
     * @param staff
     * @return StaffID if success, null if fail
     */
    public static String addStaff(Staff staff) {
        /*Check exception*/
        if (staff == null) {
            return null;
        }

        String staffID = staff.getStaffID();
        String staffName = staff.getStaffName();
        if (staffID == null || staffName == null || "".equals(staffID) || "".equals(staffName)) {
            return null;
        }

        return StaffDB.addStaff(staff);
    }

    /**
     *
     * @param staff
     * @return StaffID if effected row > 0, null if fail
     */
    public static String updateStaff(Staff staff) {
        /*Check exception*/
        if (staff == null) {
            return null;
        }

        return StaffDB.updateStaff(staff);
    }

    public static Staff getStaff(String staffID) {
        /*Check exception*/
        if (staffID == null || "".equals(staffID)) {
            return null;
        }

        return StaffDB.find(staffID);
    }
    
    public static float getAverageSalary(){
        return StaffDB.computerAverageSalary();
    }
}
