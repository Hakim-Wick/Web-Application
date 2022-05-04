/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import model.Job;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thinh
 */
public class JobDAO extends DBContext {

    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Job> getAllJob() {
        List<Job> list = new ArrayList<>();
        String query = "SELECT * FROM [JobApplication].[dbo].[Job]";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Job(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
                        rs.getString(13), rs.getString(14), rs.getString(15)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Job> getTop6Job() {
        List<Job> list = new ArrayList<>();
        String query = "SELECT TOP(6) * FROM [JobApplication].[dbo].[Job]";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Job(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
                        rs.getString(13), rs.getString(14), rs.getString(15)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public void edit(String jId, String jName, String company, String logo, String categoryID, String type, String salary, String desc, String jd, String location, String publicDate, String closingDate, String phone, String email) {
        String query = "UPDATE [JobApplication].[dbo].[Job] SET JobName = ?, Company = ?, LogoCompany = ?,"
                + "CategoryID = ?, Type = ?, Salary = ?, Description = ?, JD = ?,"
                + "Location = ?, PublicDate = ?, ClosingDate = ?, Phone = ?, Email = ? WHERE JobID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, jName);
            ps.setString(2, company);
            ps.setString(3, logo);
            ps.setString(4, categoryID);
            ps.setString(5, type);
            ps.setString(6, salary);
            ps.setString(7, desc);
            ps.setString(8, jd);
            ps.setString(9, location);
            ps.setString(10, publicDate);
            ps.setString(11, closingDate);
            ps.setString(12, phone);
            ps.setString(13, email);
            ps.setString(14, jId);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void delete(String jId) {
        String query = "DELETE FROM [JobApplication].[dbo].[Job] WHERE JobID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, jId);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void add(int userId, String jName, String company, String logo, String categoryID, String type, String salary, String desc, String jd, String location, String publicDate, String closingDate, String phone, String email) {
        String query = "INSERT INTO [JobApplication].[dbo].[Job] VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setString(2, jName);
            ps.setString(3, company);
            ps.setString(4, logo);
            ps.setString(5, categoryID);
            ps.setString(6, type);
            ps.setString(7, salary);
            ps.setString(8, desc);
            ps.setString(9, jd);
            ps.setString(10, location);
            ps.setString(11, publicDate);
            ps.setString(12, closingDate);
            ps.setString(13, phone);
            ps.setString(14, email);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public int countJob() {
        String query = "SELECT COUNT(*) FROM [JobApplication].[dbo].[Job]";
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

    public int countJobForUserManage(int userId, int admin) {
        if (admin == 1) {
            return countJob();
        } else {
            String query = "SELECT COUNT(*) FROM [JobApplication].[dbo].[Job] WHERE UserID = ?";
            try {
                ps = connection.prepareStatement(query);
                ps.setInt(1, userId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (Exception e) {
            }
        }
        return 0;
    }

    public List<Job> pagingJobForManage(int index, int userId, int admin) {
        List<Job> list = new ArrayList<>();
        if (admin == 1) {
            list = pagingJobForAdmin(index);
        } else {
            String query = "WITH t AS (SELECT j.JobID, j.UserID, j.JobName, j.Company, j.LogoCompany,"
                    + "j.CategoryID, j.Type, j.Salary, j.Description, j.JD, j.Location,"
                    + "j.PublicDate, j.ClosingDate, j.Phone, j.Email, c.CategoryName "
                    + "FROM [JobApplication].[dbo].[Job] j INNER JOIN [JobApplication].[dbo].[Category] c ON j.CategoryID = c.CategoryID WHERE j.UserID = ?)\n"
                    + "SELECT * FROM t ORDER BY JobID OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
            try {
                ps = connection.prepareStatement(query);
                ps.setInt(1, (index - 1) * 12);
                ps.setInt(2, userId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    list.add(new Job(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
                            rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8),
                            rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
                            rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16)));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return list;
    }

    public List<Job> pagingJobForAdmin(int index) {
        List<Job> list = new ArrayList<>();
        String query = "WITH t AS (SELECT j.JobID, j.UserID, j.JobName, j.Company, j.LogoCompany,"
                + "j.CategoryID, j.Type, j.Salary, j.Description, j.JD, j.Location,"
                + "j.PublicDate, j.ClosingDate, j.Phone, j.Email, c.CategoryName "
                + "FROM [JobApplication].[dbo].[Job] j INNER JOIN [JobApplication].[dbo].[Category] c ON j.CategoryID = c.CategoryID)\n"
                + "SELECT * FROM t ORDER BY JobID OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, (index - 1) * 12);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Job(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
                        rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public int countJobByCategory(int CategoryId) {
        if (CategoryId == 0) {
            return countJob();
        } else {
            String query = "SELECT COUNT(*) FROM [JobApplication].[dbo].[Job] WHERE CategoryID = ?";
            try {
                ps = connection.prepareStatement(query);
                ps.setInt(1, CategoryId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (Exception e) {
            }
        }
        return 0;
    }

    public int countJobByLocation(String location) {
        String query = "SELECT COUNT(*) FROM [JobApplication].[dbo].[Job] WHERE Location = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, location);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public int countJobByTit(String name) {
        String query = "SELECT COUNT(*) FROM [JobApplication].[dbo].[Job] WHERE JobName LIKE ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, "%" + name + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public int countJobByCateTit(int categoryId, String name) {
        String query = "SELECT COUNT(*) FROM [JobApplication].[dbo].[Job] WHERE CategoyID = ? JobName LIKE ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, categoryId);
            ps.setString(2, "%" + name + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public int countJobByLocaTit(String location, String name) {
        String query = "SELECT COUNT(*) FROM [JobApplication].[dbo].[Job] WHERE Location = ? JobName LIKE ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, location);
            ps.setString(2, "%" + name + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public int countJobByCateLoca(int categoryId, String location) {
        String query = "SELECT COUNT(*) FROM [JobApplication].[dbo].[Job] WHERE CategoryID = ? AND Location = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, categoryId);
            ps.setString(2, location);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public int countJobBySearch(int categoryId, String location, String name) {
        if (categoryId == 0 && !location.equals("NONE") && name.isEmpty()) {
            return countJobByLocation(location);
        } else if (categoryId == 0 && location.equals("NONE") && name.isEmpty()) {
            return countJob();
        } else if (categoryId == 0 && location.equals("NONE") && !name.isEmpty()) {
            return countJobByTit(name);
        } else if (categoryId != 0 && location.equals("NONE") && name.isEmpty()) {
            return countJobByCategory(categoryId);
        } else if (categoryId != 0 && location.equals("NONE") && !name.isEmpty()) {
            return countJobByCateTit(categoryId, name);
        } else if (categoryId == 0 && !location.equals("NONE") && !name.isEmpty()) {
            return countJobByLocaTit(location, name);
        } else if (categoryId != 0 && !location.equals("NONE") && name.isEmpty()) {
            return countJobByCateLoca(categoryId, location);
        } else {
            String query = "SELECT COUNT(*) FROM [JobApplication].[dbo].[Job] WHERE CategoryID = ? AND Location = ? AND JobName LIKE ?";
            try {
                ps = connection.prepareStatement(query);
                ps.setInt(1, categoryId);
                ps.setString(2, location);
                ps.setString(3, "%" + name + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (Exception e) {
            }
        }
        return 0;
    }

    public int countJobByCateType(int categoryId, String type) {
        String query = "SELECT COUNT(*) FROM [JobApplication].[dbo].[Job] WHERE CategoryID = ? AND Type = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, categoryId);
            ps.setString(2, type);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public int countJobByLocaType(String location, String type) {
        String query = "SELECT COUNT(*) FROM [JobApplication].[dbo].[Job] WHERE Location = ? AND Type = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, location);
            ps.setString(2, type);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public int countJobByFilter(int categoryId, String location, String type1, String type2) {
        if (categoryId == 0 && !location.equals("NONE") && !type1.isEmpty() && !type2.isEmpty()) {
            return countJobByLocation(location);
        } else if (categoryId == 0 && location.equals("NONE") && !type1.isEmpty() && !type2.isEmpty()) {
            return countJob();
        } else if (categoryId != 0 && location.equals("NONE") && !type1.isEmpty() && !type2.isEmpty()) {
            return countJobByCategory(categoryId);
        } else if (categoryId != 0 && !location.equals("NONE") && !type1.isEmpty() && !type2.isEmpty()) {
            return countJobByCateLoca(categoryId, location);
        } else if (categoryId != 0 && location.equals("NONE") && !type1.isEmpty() && type2.isEmpty()) {
            return countJobByCateType(categoryId, type1);
        } else if (categoryId != 0 && location.equals("NONE") && type1.isEmpty() && !type2.isEmpty()) {
            return countJobByCateType(categoryId, type2);
        } else if (categoryId == 0 && !location.equals("NONE") && !type1.isEmpty() && type2.isEmpty()) {
            return countJobByLocaType(location, type1);
        } else if (categoryId == 0 && !location.equals("NONE") && type1.isEmpty() && !type2.isEmpty()) {
            return countJobByLocaType(location, type2);
        } else if (categoryId == 0 && location.equals("NONE") && !type1.isEmpty() && type2.isEmpty()) {
            type2 = type1;
        } else if (categoryId == 0 && location.equals("NONE") && type1.isEmpty() && !type2.isEmpty()) {
            type1 = type2;
        } else {
            String query = "SELECT COUNT(*) FROM [JobApplication].[dbo].[Job] WHERE Type = ? AND Type = ?";
            try {
                ps = connection.prepareStatement(query);
                ps.setInt(1, categoryId);
                ps.setString(2, location);
                ps.setString(3, type1);
                ps.setString(4, type2);
                rs = ps.executeQuery();
                while (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (Exception e) {
            }
        }
        return 0;
    }

    public List<Job> pagingByCateType(int index, int categoryId, String type) {
        List<Job> list = new ArrayList<>();
        String query = "SELECT * FROM [JobApplication].[dbo].[Job] WHERE CategoryID = ? AND Type = ? ORDER BY JobID OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, categoryId);
            ps.setString(2, type);
            ps.setInt(3, (index - 1) * 12);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Job(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
                        rs.getString(13), rs.getString(14), rs.getString(15)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Job> pagingByLocaType(int index, String location, String type) {
        List<Job> list = new ArrayList<>();
        String query = "SELECT * FROM [JobApplication].[dbo].[Job] WHERE Location = ? AND Type = ? ORDER BY JobID OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, location);
            ps.setString(2, type);
            ps.setInt(3, (index - 1) * 12);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Job(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
                        rs.getString(13), rs.getString(14), rs.getString(15)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Job> pagingByFilter(int index, int categoryId, String location, String type1, String type2) {
        List<Job> list = new ArrayList<>();
        if (categoryId == 0 && !location.equals("NONE") && !type1.isEmpty() && !type2.isEmpty()) {
            list = pagingByLocation(index, location);
        } else if (categoryId == 0 && location.equals("NONE") && !type1.isEmpty() && !type2.isEmpty()) {
            list = pagingJob(index);
        } else if (categoryId != 0 && location.equals("NONE") && !type1.isEmpty() && !type2.isEmpty()) {
            list = pagingByCategory(index, categoryId);
        } else if (categoryId != 0 && !location.equals("NONE") && !type1.isEmpty() && !type2.isEmpty()) {
            list = pagingByCateLoca(index, categoryId, location);
        } else if (categoryId != 0 && location.equals("NONE") && !type1.isEmpty() && type2.isEmpty()) {
            list = pagingByCateType(index, categoryId, type1);
        } else if (categoryId != 0 && location.equals("NONE") && type1.isEmpty() && !type2.isEmpty()) {
            list = pagingByCateType(index, categoryId, type2);
        } else if (categoryId == 0 && !location.equals("NONE") && !type1.isEmpty() && type2.isEmpty()) {
            list = pagingByLocaType(index, location, type1);
        } else if (categoryId == 0 && !location.equals("NONE") && type1.isEmpty() && !type2.isEmpty()) {
            list = pagingByLocaType(index, location, type2);
        } else if (categoryId == 0 && location.equals("NONE") && !type1.isEmpty() && type2.isEmpty()) {
            type2 = type1;
        } else if (categoryId == 0 && location.equals("NONE") && type1.isEmpty() && !type2.isEmpty()) {
            type1 = type2;
        } else {
            String query = "SELECT COUNT(*) FROM [JobApplication].[dbo].[Job] WHERE Type = ? AND Type = ? ORDER BY JobID OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
            try {
                ps = connection.prepareStatement(query);
                ps.setInt(1, categoryId);
                ps.setString(2, location);
                ps.setString(3, type1);
                ps.setString(4, type2);
                rs = ps.executeQuery();
                while (rs.next()) {
                    list.add(new Job(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
                            rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8),
                            rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
                            rs.getString(13), rs.getString(14), rs.getString(15)));
                }
            } catch (Exception e) {
            }
        }
        return list;
    }

    public Job getJobByID(String jId) {
        String query = "SELECT * FROM [JobApplication].[dbo].[Job] WHERE JobID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, jId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return (new Job(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
                        rs.getString(13), rs.getString(14), rs.getString(15)));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Job> pagingJob(int index) {
        List<Job> list = new ArrayList<>();
        String query = "SELECT * FROM [JobApplication].[dbo].[Job] ORDER BY JobID OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, (index - 1) * 12);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Job(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
                        rs.getString(13), rs.getString(14), rs.getString(15)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Job> pagingByCategory(int index, int categoryId) {
        List<Job> list = new ArrayList<>();
        if (categoryId == 0) {
            return pagingJob(index);
        } else {
            String query = "SELECT * FROM [JobApplication].[dbo].[Job] WHERE CategoryID = ? ORDER BY JobID OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
            try {
                ps = connection.prepareStatement(query);
                ps.setInt(1, categoryId);
                ps.setInt(2, (index - 1) * 12);
                rs = ps.executeQuery();
                while (rs.next()) {
                    list.add(new Job(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
                            rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8),
                            rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
                            rs.getString(13), rs.getString(14), rs.getString(15)));
                }
            } catch (Exception e) {
            }
        }
        return list;
    }

    public List<Job> pagingByLocation(int index, String location) {
        List<Job> list = new ArrayList<>();
        String query = "SELECT * FROM [JobApplication].[dbo].[Job] WHERE Location = ? ORDER BY JobID OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, location);
            ps.setInt(2, (index - 1) * 12);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Job(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
                        rs.getString(13), rs.getString(14), rs.getString(15)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Job> pagingByTit(int index, String name) {
        List<Job> list = new ArrayList<>();
        String query = "SELECT * FROM [JobApplication].[dbo].[Job] WHERE JobName LIKE ? ORDER BY JobID OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, "%" + name + "%");
            ps.setInt(2, (index - 1) * 12);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Job(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
                        rs.getString(13), rs.getString(14), rs.getString(15)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Job> pagingByCateLoca(int index, int categoryId, String location) {
        List<Job> list = new ArrayList<>();
        String query = "SELECT * FROM [JobApplication].[dbo].[Job] WHERE CategoryID = ? AND Location = ? ORDER BY JobID OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, categoryId);
            ps.setString(2, location);
            ps.setInt(3, (index - 1) * 12);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Job(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
                        rs.getString(13), rs.getString(14), rs.getString(15)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Job> pagingByCateTit(int index, int categoryId, String name) {
        List<Job> list = new ArrayList<>();
        String query = "SELECT * FROM [JobApplication].[dbo].[Job] WHERE CategoryID = ? AND JobName LIKE ? ORDER BY JobID OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, categoryId);
            ps.setString(2, "%" + name + "%");
            ps.setInt(3, (index - 1) * 12);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Job(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
                        rs.getString(13), rs.getString(14), rs.getString(15)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Job> pagingByLocaTit(int index, String location, String name) {
        List<Job> list = new ArrayList<>();
        String query = "SELECT * FROM [JobApplication].[dbo].[Job] WHERE JobName LIKE ? AND Location = ? ORDER BY JobID OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, location);
            ps.setString(2, "%" + name + "%");
            ps.setInt(3, (index - 1) * 12);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Job(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
                        rs.getString(13), rs.getString(14), rs.getString(15)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Job> pagingBySearch(int index, String name, int categoryId, String location) {
        List<Job> list = new ArrayList<>();
        if (categoryId == 0 && !location.equals("NONE") && name.isEmpty()) {
            list = pagingByLocation(index, location);
        } else if (categoryId == 0 && location.equals("NONE") && name.isEmpty()) {
            list = pagingJob(index);
        } else if (categoryId == 0 && location.equals("NONE") && !name.isEmpty()) {
            list = pagingByTit(index, name);
        } else if (categoryId != 0 && location.equals("NONE") && name.isEmpty()) {
            list = pagingByCategory(index, categoryId);
        } else if (categoryId != 0 && location.equals("NONE") && !name.isEmpty()) {
            list = pagingByCateTit(index, categoryId, name);
        } else if (categoryId == 0 && !location.equals("NONE") && !name.isEmpty()) {
            list = pagingByLocaTit(index, location, name);
        } else if (categoryId != 0 && !location.equals("NONE") && name.isEmpty()) {
            list = pagingByCateLoca(index, categoryId, location);
        } else {
            String query = "SELECT * FROM [JobApplication].[dbo].[Job] WHERE CategoryID = ?"
                    + "AND Location = ? AND JobName LIKE ? ORDER BY JobID OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY";
            try {
                ps = connection.prepareStatement(query);
                ps.setInt(1, categoryId);
                ps.setString(2, location);
                ps.setString(3, "%" + name + "%");
                ps.setInt(4, (index - 1) * 12);
                rs = ps.executeQuery();
                while (rs.next()) {
                    list.add(new Job(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
                            rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8),
                            rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
                            rs.getString(13), rs.getString(14), rs.getString(15)));
                }
            } catch (Exception e) {
            }
        }
        return list;
    }

}
