package com.demo.banco.model;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "SYS_USER")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class SystemUser {

    @Id
    @Column(name = "user_id")
    private UUID id;
    private String password;
    @Column(name = "created_at")
    private ZonedDateTime created;
    @Column(name = "last_login")
    private ZonedDateTime lastLogin;
    private String token;
    @Column(name = "is_active")
    private boolean isActive;

    public SystemUser() { }

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
