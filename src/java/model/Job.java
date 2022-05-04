/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author thinh
 */
public class Job {
    private int jId, userId, categoryId;
    private String jName, company, logo, type, salary, desc, jd, location, publicDate, closingDate, phone, email, cateName;

    public Job() {
    }
    
    public Job(int jId, int userId, String jName, String company, String logo, int categoryId, String type, String salary, String desc, String jd, String location, String publicDate, String closingDate, String phone, String email) {
        this.jId = jId;
        this.userId = userId;
        this.categoryId = categoryId;
        this.jName = jName;
        this.company = company;
        this.logo = logo;
        this.type = type;
        this.salary = salary;
        this.desc = desc;
        this.jd = jd;
        this.location = location;
        this.publicDate = publicDate;
        this.closingDate = closingDate;
        this.phone = phone;
        this.email = email;
    }
    
    public Job(int jId, int userId, String jName, String company, String logo, int categoryId, String type, String salary, String desc, String jd, String location, String publicDate, String closingDate, String phone, String email, String cateName) {
        this.jId = jId;
        this.userId = userId;
        this.categoryId = categoryId;
        this.jName = jName;
        this.company = company;
        this.logo = logo;
        this.type = type;
        this.salary = salary;
        this.desc = desc;
        this.jd = jd;
        this.location = location;
        this.publicDate = publicDate;
        this.closingDate = closingDate;
        this.phone = phone;
        this.email = email;
        this.cateName = cateName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public int getjId() {
        return jId;
    }

    public void setjId(int jId) {
        this.jId = jId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getjName() {
        return jName;
    }

    public void setjName(String jName) {
        this.jName = jName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getJd() {
        return jd;
    }

    public void setJd(String jd) {
        this.jd = jd;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(String publicDate) {
        this.publicDate = publicDate;
    }

    public String getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(String closingDate) {
        this.closingDate = closingDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
    
}
