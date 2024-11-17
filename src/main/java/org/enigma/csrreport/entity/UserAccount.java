package org.enigma.csrreport.entity;

import jakarta.persistence.*;
import lombok.*;
import org.enigma.csrreport.constant.Constant;
import org.enigma.csrreport.constant.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = Constant.USER_ACCOUNT_TABLE)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserAccount implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "fullName")
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> myRoles = List.of(role);
        return myRoles.stream().map(role -> new SimpleGrantedAuthority(role.name())).toList();
    }

}
