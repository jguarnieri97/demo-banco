package com.demo.banco.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "SYS_USER")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@NoArgsConstructor
public abstract class SystemUser {

    @Id
    @Column(name = "user_id")
    private String id;

    @Column(name = "created_at")
    private LocalDateTime created;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Column(name = "is_active")
    private boolean isActive;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "auth_id", referencedColumnName = "id")
    private AuthInfo authInfo;

    public SystemUser(String password, LocalDateTime created, boolean isActive) {
        this.id = UUID.randomUUID().toString();
        this.authInfo = new AuthInfo(this, password);
        this.created = created;
        this.isActive = isActive;
    }

    public void setToken(String token) {
        this.authInfo.setToken(token);
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

}
