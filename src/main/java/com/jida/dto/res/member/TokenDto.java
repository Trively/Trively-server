package com.jida.dto.res.member;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
public class TokenDto {
    private final String grantType;
    private final String accessToken;
}


