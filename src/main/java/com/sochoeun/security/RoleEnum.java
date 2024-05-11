package com.sochoeun.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.sochoeun.security.PermissionEnum.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum RoleEnum {
    ADMIN(
            Set.of(
                    ADMIN_READ,
                    ADMIN_WRITE,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,

                    STAFF_READ,
                    STAFF_WRITE,
                    STAFF_UPDATE,
                    STAFF_DELETE
            )
    ),
    STAFF(
            Set.of(
                    STAFF_READ,
                    STAFF_WRITE,
                    STAFF_UPDATE,
                    STAFF_DELETE
            )
    );

    private final Set<PermissionEnum> permissions;

    public Set<SimpleGrantedAuthority> getAuthorities(){
        Set<SimpleGrantedAuthority> grantedAuthorities = this.permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());

        /*
         * Role and Permission must be much together
         * */
        SimpleGrantedAuthority role = new SimpleGrantedAuthority("ROLE_"+ this.name());
        grantedAuthorities.add(role);
        System.out.println(grantedAuthorities);
        return grantedAuthorities;
    }

}
