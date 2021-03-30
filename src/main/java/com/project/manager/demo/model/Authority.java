package com.project.manager.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "authority")
@Data
@NoArgsConstructor
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String authority;

//    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
//    private Set<User> users;

    @Override
    public String getAuthority() {
        return authority;
    }

    public Authority(String authority) {
        this.authority = authority;
    }

    public Authority(long id, String authority) {
        this.id = id;
        this.authority = authority;
    }
}
