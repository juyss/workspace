package com.icepoint.framework.data.annotation;

/**
 * 加密算法类型
 *
 * @author Jiawei Zhao
 */
public enum EncryptType {

    BCRYPT("bcrypt"),

    LDAP("ldap"),

    MD4("MD4"),

    MD5("MD5"),

    /**
     * 无加密
     */
    NOOP("noop"),

    PBKDF2("pbkdf2"),

    SCRYPT("scrypt"),

    SHA1("SHA-1"),

    SHA256("SHA-256"),

    ARGON2("argon2");

    private final String id;

    EncryptType(String id) {
        this.id = id;
    }

    public String id() {
        return this.id;
    }
}
