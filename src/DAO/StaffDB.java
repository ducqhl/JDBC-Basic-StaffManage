/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import DTO.Staff;
import java.sql.PreparedStatement;

/**
 *
 * @author Backf
 */
public class StaffDB {

    private static ArrayList<Staff> saveStaffData(ResultSet rs) {
        ArrayList<Staff> StaffList = new ArrayList<Staff>();
        try {
            while (rs.next()) {
                Staff item = new Staff();
                item.setStaffID(rs.getString("MANV"));
                item.setStaffName(rs.getString("HOTEN"));
                item.setSex(rs.getBoolean("PHAI"));
                item.setDOB(rs.getDate("NGAYSINH"));
                item.setAddress(rs.getString("DIACHI"));
                item.setSalary(rs.getInt("LUONG"));
                item.setDepartment(rs.getByte("PHONG"));

                StaffList.add(item);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return StaffList;
    }

    public static Staff find(String staffID) {
        Staff staff = null;
        Connection conn = Provider.connect();
        String sql = "SELECT * FROM NHANVIEN WHERE MANV = '" + staffID + "'";
        Statement stmt = null;
        ResultSet rs;
        //try create statement
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            System.out.println("Fail when try to connect database: " + e);
        }

        if (stmt != null) {
            //try execute query
            try {
                rs = stmt.executeQuery(sql);
                staff = saveStaffData(rs).get(0);
            } catch (SQLException e) {
                System.out.println("Execute query fail! " + e);
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Error when close connection, throws " + e);
                }
            }
        }

        return staff;
    }

    /**
     * @return list of staff
     */
    public static ArrayList<Staff> find_all() {
        ArrayList<Staff> StaffList = null;
        Connection conn = Provider.connect();
        String sql = "SELECT * FROM NHANVIEN";
        Statement stmt = null;
        ResultSet rs;
        //try create statement
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            System.out.println("Fail when try to connect database: " + e);
        }

        if (stmt != null) {
            //try execute query
            try {
                rs = stmt.executeQuery(sql);
                StaffList = saveStaffData(rs);
            } catch (SQLException e) {
                System.out.println("Execute query fail! " + e);
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Error when close connection, throws " + e);
                }
            }
        }

        return StaffList;
    }

    public static ArrayList<Staff> findByDepartment(String departmentName) {
        Integer dpmID = DepartmentDB.getDepartmentID(departmentName);
        ArrayList<Staff> StaffList = null;

        if (dpmID == null) {
            return null;
        }

        Connection conn = Provider.connect();

        if (conn != null) {
            String sql = "SELECT * FROM NHANVIEN WHERE PHONG LIKE ?";
            PreparedStatement stmt = null;

            //try create statement
            try {
                stmt = conn.prepareStatement(sql);
            } catch (SQLException e) {
                System.out.println("Fail when try to connect database: " + e);
            }

            if (stmt != null) {
                //try execute query
                try {
                    stmt.setInt(1, dpmID);
                    ResultSet rs = stmt.executeQuery();
                    StaffList = saveStaffData(rs);
                } catch (SQLException e) {
                    System.out.println("Execute query fail! " + e);
                } finally {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        System.out.println("Error when close connection, throws " + e);
                    }
                }
            }
        }

        return StaffList;
    }

    public static String addStaff(Staff staff) {
        String sql = "INSERT INTO NHANVIEN (`MANV`, `HOTEN`, `PHAI`, `NGAYSINH`, `DIACHI`, `LUONG`, `PHONG`)"
                + " VALUES (?, ?, ? , ?, ?, ?, ?)";
        PreparedStatement pstmt = null;
        String staffID = "";
        Integer effectedRow = 0;

        Connection conn = Provider.connect();
        if (conn != null) {
            //try create prepared statement
            try {
                pstmt = conn.prepareStatement(sql);
            } catch (SQLException e) {
                System.out.println("Fail when try create statement, throws " + e);
            }

            if (pstmt != null) {
                //try execute query
                try {
                    //set ? params
                    pstmt.setString(1, staff.getStaffID());
                    pstmt.setString(2, staff.getStaffName());
                    pstmt.setBoolean(3, staff.getSex());
                    pstmt.setDate(4, staff.getDOB());
                    pstmt.setString(5, staff.getAddress());
                    pstmt.setInt(6, staff.getSalary());
                    pstmt.setInt(7, staff.getDepartment());

                    effectedRow = pstmt.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("Fail when try execute query, throws " + e);
                } finally {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        System.out.println("Fail when try close connection, throws " + e);
                    }
                }
            }
        }

        if (effectedRow != 0) {
            staffID = StaffDB.getStaffID(staff.getStaffName());
        }

        return staffID;
    }

    public static String getStaffID(String staffName) {
        String sql = "SELECT MANV FROM NHANVIEN WHERE HOTEN LIKE ?";
        PreparedStatement pstmt = null;
        String staffID = "";

        Connection conn = Provider.connect();
        if (conn != null) {
            //try create prepared statement
            try {
                pstmt = conn.prepareStatement(sql);
            } catch (SQLException e) {
                System.out.println("Fail when try create statement, throws " + e);
            }

            if (pstmt != null) {
                //try execute query
                try {
                    //set ? params
                    pstmt.setString(1, "%" + staffName + "%");
                    ResultSet rs = pstmt.executeQuery();
                    if (rs.next()) {
                        staffID = rs.getString("MANV");
                    }
                } catch (SQLException e) {
                    System.out.println("Fail when try execute query, throws " + e);
                } finally {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        System.out.println("Fail when try close connection, throws " + e);
                    }
                }
            }
        }

        return staffID;
    }

    public static String updateStaff(Staff staff) {
        String sql = "UPDATE NHANVIEN SET"
                + " HOTEN = ?"
                + " ,PHAI = ?"
                + " ,NGAYSINH = ?"
                + " ,DIACHI = ?"
                + " ,LUONG = ?"
                + " ,PHONG = ?"
                + " WHERE MANV = ?";
        PreparedStatement pstmt = null;
        String staffID = "";
        Integer effectedRow = 0;

        Connection conn = Provider.connect();
        if (conn != null) {
            //try create prepared statement
            try {
                pstmt = conn.prepareStatement(sql);
            } catch (SQLException e) {
                System.out.println("Fail when try create statement, throws " + e);
            }

            if (pstmt != null) {
                //try execute query
                try {
                    //set ? params
                    pstmt.setString(1, staff.getStaffName());
                    pstmt.setBoolean(2, staff.getSex());
                    pstmt.setDate(3, staff.getDOB());
                    pstmt.setString(4, staff.getAddress());
                    pstmt.setInt(5, staff.getSalary());
                    pstmt.setInt(6, staff.getDepartment());
                    pstmt.setString(7, staff.getStaffID());

                    effectedRow = pstmt.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("Fail when try execute query, throws " + e);
                } finally {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        System.out.println("Fail when try close connection, throws " + e);
                    }
                }
            }
        }

        if (effectedRow != 0) {
            staffID = StaffDB.getStaffID(staff.getStaffName());
        }

        return staffID;
    }

    public static float computerAverageSalary() {
        String sql = "SELECT AVG(LUONG) AS LUONG_TB FROM NHANVIEN";
        float result = 0;
        Connection conn = Provider.connect();
        Statement stmt = null;
        //try create statement
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            System.out.println("Fail when try to connect database: " + e);
        }

        if (stmt != null) {
            //try execute query
            try {
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    result = rs.getFloat("LUONG_TB");
                }
            } catch (SQLException e) {
                System.out.println("Execute query fail! " + e);
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Error when close connection, throws " + e);
                }
            }
        }

        return result;
    }
}
