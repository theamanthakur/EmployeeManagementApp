package com.ttl.ritz;

public class User {
    String name;
    String Email;
    String Desig;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDesig() {
        return Desig;
    }

    public void setDesig(String desig) {
        Desig = desig;
    }

    public User(String name, String email, String Desig) {
        this.name = name;
        this.Email = email;
        this.Desig = Desig;
    }
}
