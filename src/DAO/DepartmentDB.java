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
import DTO.Department;
import java.sql.PreparedStatement;

/**
 *
 * @author Backf
 */
public class DepartmentDB {

    private static ArrayList<Department> saveDepartmentData(ResultSet rs) {
        ArrayList<Department> DpmList = null;

        if (rs != null) {
            DpmList = new ArrayList<Department>();
            try {
                while (rs.next()) {
                    Department item = new Department();
                    item.setDepartmentID(rs.getInt("MAPHG"));
                    item.setDepartmentName(rs.getString("TENPHG"));
                    DpmList.add(item);
                }
            } catch (SQLException e) {
                System.out.println("Fail when try to parse result set, throws " + e);
            }
        }

        return DpmList;
    }

    public static ArrayList<Department> find_all() {
        ArrayList<Department> DpmList = null;
        Connection conn = Provider.connect();
        String sql = "SELECT * FROM PHONGBAN";
        Statement stmt = null;
        ResultSet rs;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            System.out.println("Fail when try connect database, throws " + e);
        }

        if (stmt != null) {
            try {
                rs = stmt.executeQuery(sql);
                DpmList = saveDepartmentData(rs);
                return DpmList;
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

        return DpmList;
    }

    /**
     * 
     * @param departmentName
     * @return id of department passed if exist, return null if this department not exist
     */
    public static Integer getDepartmentID(String departmentName) {
        String sql = "SELECT MAPHG FROM PHONGBAN WHERE TENPHG LIKE ?";
        PreparedStatement stmt = null;
        Integer departmentID = null;

        Connection conn = Provider.connect();
        if (conn != null) {
            //try create prepared statement
            try {
                stmt = conn.prepareStatement(sql);
            } catch (SQLException e) {
                System.out.println("Fail when try create statement, throws " + e);
            }

            if (stmt != null) {      
                //try execute query
                try {
                    //set ? = departmentName
                    stmt.setString(1, "%" + departmentName + "%");
                    ResultSet rs = stmt.executeQuery();
                    if(rs.next()){
                        departmentID = rs.getInt("MAPHG");
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

        return departmentID;
    }
    
    public static Integer addDepartment(Department dpm){
        String sql = "INSERT INTO PHONGBAN(TENPHG) VALUES (?)";
        PreparedStatement pstmt = null;
        Integer departmentID = null;
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
                    //set ? = departmentName
                    pstmt.setString(1, dpm.getDepartmentName());
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

        if(effectedRow != 0){
            departmentID = DepartmentDB.getDepartmentID(dpm.getDepartmentName());
        }
        
        return departmentID;
    }
    
    public static Integer countStaffsInDepartment(int departmentID){
        String sql = "SELECT COUNT(*) AS SL_NHANVIEN FROM NHANVIEN WHERE PHONG = " + departmentID;
        
        Connection conn = Provider.connect();
        Statement stmt = null;
        Integer result = 0;
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
                    result = rs.getInt("SL_NHANVIEN");
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
