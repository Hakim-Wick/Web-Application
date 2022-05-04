package model;

public class Category {

    private int id, countjob;
    private String name, icon;

    public Category() {
    }

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(int id, String name, String icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }

    public Category(int id, String name, String icon, int countjob) {
        this.id = id;
        this.countjob = countjob;
        this.name = name;
        this.icon = icon;
    }

    public int getCountjob() {
        return countjob;
    }

    public void setCountjob(int countjob) {
        this.countjob = countjob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
