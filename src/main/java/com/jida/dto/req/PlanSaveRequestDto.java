package com.jida.dto.req;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class PlanSaveRequestDto {
    private List<Plans> plans;

    @Getter
    @NoArgsConstructor
    public static class Plans{
        @NotNull(message = "여행지 id 값은 필수입니다.")
        private Long attractionId;
        private LocalDateTime planDate;
        private int orders;
    }
}
