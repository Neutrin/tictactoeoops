package model;

public class User{
    private String name;
    private static Long id = 0l;

    public User(String name) {
        this.name = name;
        this.id++;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Long getId() {
        return id;
    }

    public static void setId(Long id) {
        User.id = id;
    }




}