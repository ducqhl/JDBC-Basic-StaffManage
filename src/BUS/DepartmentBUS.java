/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import java.util.ArrayList;
import DTO.Department;
import DAO.DepartmentDB;
/**
 *
 * @author Backf
 */
public class DepartmentBUS {

    /**
     * Display list of Staff of passed department parameter
     */
    public static ArrayList<Department> getDepartments() {
        return DepartmentDB.find_all();
    }

    public static Integer addDepartment(Department dpm) {
        /*Check exception*/
        if (dpm == null) {
            return null;
        }
        
        String dpmName = dpm.getDepartmentName();
        if (dpmName == null || "".equals(dpmName)) {
            return null;
        }
        
        //standardized name of department
        dpm.setDepartmentName(dpmName.trim());
        
        return DepartmentDB.addDepartment(dpm);
    }
    
    public static Integer getNumberOfStaffs(int departmentID){
        return DepartmentDB.countStaffsInDepartment(departmentID);
    }
}
