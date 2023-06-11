package dev.paul.dtopattern.model;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class User {

    private static SecretKeySpec KEY = initKey();

    static SecretKeySpec initKey() {
        try {
            SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();
            return new SecretKeySpec(secretKey.getEncoded(), "AES");
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }
    }

    private String id;
    private String name;
    private String password;
    private List<Role> roles;

    public User(String name, String password, List<Role> roles) {
        this.name = Objects.requireNonNull(name);
        this.password = this.encrypt(password);
        this.roles = Objects.requireNonNull(roles);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return Collections.unmodifiableList(roles);
    }


    /*public void setRoles(List<Role> roles) {
        this.roles = roles;
    }*/
    public void addRole(Role role) {
        roles.add(role);
    }

    String encrypt(String password) {
        Objects.requireNonNull(password);
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, KEY);
            final byte[] encryptedBytes = cipher.doFinal(password.getBytes(StandardCharsets.UTF_8));
            return new String(encryptedBytes, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException |
                 NoSuchPaddingException |
                 InvalidKeyException |
                 IllegalBlockSizeException |
                 BadPaddingException e) {
            // do nothing
            return "";
        }
    }
}
