package com.demo.banco.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "AUTH_INFO")
@Getter
@NoArgsConstructor
public class AuthInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String password;

    private String token;

    @JsonIgnore
    @OneToOne(mappedBy = "authInfo", fetch = FetchType.LAZY)
    private SystemUser user;

    public AuthInfo(SystemUser user, String password) {
        this.user = user;
        this.password = password;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
