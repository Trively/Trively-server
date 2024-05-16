package com.jida.dto.res.member;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokenDto {
    private String grantType;
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpiresIn;

    public static TokenDto of(String grantType, String accessToken, String refreshToken, Long accessTokenExpiresIn) {
        TokenDto tokenDto = new TokenDto();
        tokenDto.grantType = grantType;
        tokenDto.accessToken = accessToken;
        tokenDto.refreshToken = refreshToken;
        tokenDto.accessTokenExpiresIn = accessTokenExpiresIn;
        return tokenDto;
    }
}
