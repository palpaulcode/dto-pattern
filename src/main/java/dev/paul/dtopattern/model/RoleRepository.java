package dev.paul.dtopattern.model;

public interface RoleRepository {
    Role getRoleById(String id);
    Role getRoleByName(String name);
    void save(Role role);
}
