package com.demo.banco.model;

import java.time.ZonedDateTime;
import java.util.UUID;

public abstract class SystemUser {

    private UUID id;
    private String password;
    private ZonedDateTime created;
    private ZonedDateTime lastLogin;
    private String token;
    private boolean isActive;

    public SystemUser(String password, ZonedDateTime created, boolean isActive) {
        this.id = UUID.randomUUID();
        this.password = password;
        this.created = created;
        this.isActive = isActive;
    }

    public UUID getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public ZonedDateTime getLastLogin() {
        return lastLogin;
    }

    public String getToken() {
        return token;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setLastLogin(ZonedDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }
}
