package com.github.tangyi.common.security.ty;

import lombok.Data;

@Data
public class MainAccessToken {
    private String accessToken;
    private String tokenType;
    private Long expiresIn;
    private String refreshToken;
    private Long createTime;
    private String tgc;

//            "accessToken": "743bad99-8dad-496a-8ac8-bbb9b32bacf2",
//                    "tokenType": "Bearer",
//                    "expiresIn": 43200,
//                    "refreshToken": "f8b027c5-efff-4b18-a24e-dd0db0d9a2c9"
}
