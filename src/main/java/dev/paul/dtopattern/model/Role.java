package dev.paul.dtopattern.model;

import java.util.Objects;

public class Role {

    private String id;
    private String name;

    public Role(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = Objects.requireNonNull(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name);
    }
}
