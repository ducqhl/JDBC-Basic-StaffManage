/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Backf
 */
public class Staff {

    private String staffID;
    private String staffName;
    private boolean sex;
    private Date DOB;
    private String address;
    private int salary;
    private int department;

    public Staff() {
    }

    public Staff(Staff staff) {
        this.staffID = staff.staffID;
        this.staffName = staff.staffName;
        this.sex = staff.sex;
        this.DOB = staff.DOB;
        this.address = staff.address;
        this.salary = staff.salary;
        this.department = staff.department;
    }

    public Staff(String staffID, String staffName, boolean sex, Date DOB, String address, int salary, byte department) {
        this.staffID = staffID;
        this.staffName = staffName;
        this.sex = sex;
        this.DOB = DOB;
        this.address = address;
        this.salary = salary;
        this.department = department;
    }

    @Override
    public String toString() {
        return staffID
                + "\t" + staffName
                + "\t" + sex
                + "\t" + DOB
                + "\t" + address
                + "\t" + salary
                + "\t" + department;
    }

    public boolean equals(Staff other) {
        if (this.salary != other.salary) {
            return false;
        }
        if (this.department != other.department) {
            return false;
        }
        if (!Objects.equals(this.staffID, other.staffID)) {
            return false;
        }
        if (!Objects.equals(this.staffName, other.staffName)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.DOB, other.DOB)) {
            return false;
        }
        return true;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public boolean getSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

}
