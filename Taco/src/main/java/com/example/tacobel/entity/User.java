package com.example.tacobel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "\"user\"")
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    public User(String username, String password, String name, String secondName, String email, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.secondName = secondName;
        this.email = email;
        this.roles = roles;
        this.status = false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;

    private String password;

    private String name;

    private String secondName;

    @Column(unique = true)
    private String email;

    private Boolean status;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "users_roles",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private Set<Role> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
        return status;
    }

    @Override
    public String toString() {
        return  id + "\n" +
                username + "\n" +
                password + "\n" +
                name + "\n" +
                secondName + "\n";

    }
}
