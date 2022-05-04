/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import model.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class UserDAO extends DBContext {

    PreparedStatement ps = null;
    ResultSet rs = null;

    public Account login(String user, String pass) {
        String query = "SELECT * FROM [JobApplication].[dbo].[Account] WHERE UserName = ? AND Password = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void signUp(String user, String pass) {
        String query = "INSERT INTO [JobApplication].[dbo].[Account] VALUES (?, ?, 0);";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<Account> getAllAccounts() {
        List<Account> list = new ArrayList<>();
        String query = "SELECT * FROM [JobApplication].[dbo].[Account]";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void deleteAccount(String id) {
        String query = "DELETE FROM [JobApplication].[dbo].[Account] WHERE UserID = ?;";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return;
    }

    public void editAccount(String id, int isAdmin) {
        if (isAdmin == 0) {
            isAdmin = 1;
        } else {
            isAdmin = 0;
        }
        String query = "UPDATE [JobApplication].[dbo].[Account] SET isAdmin = ? WHERE UserID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, isAdmin);
            ps.setString(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public void changePass(int id, String pass) {
        String query = "UPDATE [JobApplication].[dbo].[Account] SET Password = ? WHERE UserID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, pass);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public Account getAccountByID(String id) {
        String query = "SELECT * FROM [JobApplication].[dbo].[Account] WHERE UserID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public int countAccount() {
        String query = "SELECT COUNT(*) FROM [JobApplication].[dbo].[Account]";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }
    
    public List<Account> pagingAccount(int index) {
        List<Account> list = new ArrayList<>();
        String query = "SELECT * FROM [JobApplication].[dbo].[Account] ORDER BY UserID OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, (index - 1) * 12);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
        } catch (Exception e) {
        }
        return list;
    }

}
