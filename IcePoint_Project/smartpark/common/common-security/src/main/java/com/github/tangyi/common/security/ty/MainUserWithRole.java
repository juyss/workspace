package com.github.tangyi.common.security.ty;

import com.github.tangyi.model.MainRole;
import com.github.tangyi.model.MainUser;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MainUserWithRole extends MainUser {
    public static final String IDENTIFIER_PREFIX = "";
    private List<MainRole> roles;
}
