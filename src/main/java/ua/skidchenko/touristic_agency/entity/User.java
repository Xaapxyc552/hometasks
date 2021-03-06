package ua.skidchenko.touristic_agency.entity;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ua.skidchenko.touristic_agency.entity.enums.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

@Entity
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false, length = 30)
    private String username;

    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column(name = "email", unique = true, length = 60)
    private String email;

    @Column(name = "firstname", length = 20)
    private String firstname;

    @Column(name = "account_non_expired", nullable = false)
    private boolean accountNonExpired;

    @Column(name = "account_non_locked", nullable = false)
    private boolean accountNonLocked;

    @Column(name = "credentials_non_expired", nullable = false)
    private boolean credentialsNonExpired;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "money", nullable = false)
    private Long money;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>(Collections.singleton(role));
    }

    public void setMoney(Long money) {
        if(money < 0) throw new IllegalArgumentException("Money value cannot be negative!");
        this.money = money;
    }
}

