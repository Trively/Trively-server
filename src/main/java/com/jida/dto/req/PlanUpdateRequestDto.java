package com.jida.dto.req;

import com.jida.domain.Attraction;
import com.jida.domain.Plan;
import com.jida.domain.PlanList;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlanUpdateRequestDto extends PlanSaveRequestDto{
    @NotNull
    private Long planListId;
}
