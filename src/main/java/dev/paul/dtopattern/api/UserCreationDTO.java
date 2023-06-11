package dev.paul.dtopattern.api;

import java.util.List;

public class UserCreationDTO {

    private String name;
    private String password;
    private List<String> roles;

    UserCreationDTO() {}

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
