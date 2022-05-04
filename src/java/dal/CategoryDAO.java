/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import model.Category;
import model.Job;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thinh
 */
public class CategoryDAO extends DBContext {

    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String query = "SELECT * FROM [JobApplication].[dbo].[Category]";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (Exception e) {
        }

        return list;
    }

    public String getCateNameByID(int id) {
        String query = "SELECT CategoryName FROM [JobApplication].[dbo].[Category] WHERE CategoryID = ?"; 
        try {
            ps = connection.prepareStatement(query); 
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public List<Category> CountJob() {
        List<Category> list = new ArrayList<>();
        String query = "WITH t AS (SELECT COUNT(JobID) AS TotalJob, CategoryID FROM [JobApplication].[dbo].[Job] GROUP BY CategoryID)\n" +
                "SELECT t.CategoryID, CategoryName, LogoCategory, TotalJob FROM t INNER JOIN [JobApplication].[dbo].[Category] c ON  c.CategoryID = t.CategoryID";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
        } catch (Exception e) {
        }

        return list;
    }

}
