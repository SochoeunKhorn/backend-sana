package com.sochoeun.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum PermissionEnum {
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_DELETE("admin:delete"),

    STAFF_READ("staff:read"),
    STAFF_WRITE("staff:write"),
    STAFF_UPDATE("staff:update"),
    STAFF_DELETE("staff:delete")
    ;

    private final String permission;
}
