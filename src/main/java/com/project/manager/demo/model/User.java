package com.project.manager.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private long id;
    private String username;
    private String password;
    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;
    @JsonProperty("isAdmin")
    private boolean isAdmin;

    @JoinTable
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Authority> authorities;

    @OneToOne
    private Student student;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(Student student, String password, List<Authority> authorities) {
        this.username = student.getEmail();
        this.password = password;
        this.authorities = authorities;
        this.student = student;
    }

    public User(String username, String password, List<Authority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public User(String username,
                String password,
                boolean isAccountNonExpired,
                boolean isAccountNonLocked,
                boolean isCredentialsNonExpired,
                boolean isEnabled,
                List<Authority> authorities) {

        this.username = username;
        this.password = password;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
        this.authorities = authorities;
    }

    public void update(User user) {
        this.username = user.username;
        this.password = user.password;
        this.authorities = user.authorities;
        this.isAccountNonExpired = user.isAccountNonExpired;
        this.isEnabled = user.isEnabled;
        this.isAccountNonLocked = user.isAccountNonLocked;
        this.isCredentialsNonExpired = user.isCredentialsNonExpired;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public boolean isAdmin() {
        return authorities.parallelStream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
    }


}
