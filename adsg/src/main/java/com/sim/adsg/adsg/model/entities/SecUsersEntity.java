package com.sim.adsg.adsg.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "SEC_USERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SecUsersEntity implements UserDetails {

    @Id
    @Column(name = "USER_CODE")
    private String userCode;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_TYPE")
    private String userType;

    @Column(name = "IS_GOV_EMPLOYEE")
    private String isGovEmployee;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return "password";
    }
    @Override
    public String getUsername() {
        return userCode;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
