package com.project.manager.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

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
