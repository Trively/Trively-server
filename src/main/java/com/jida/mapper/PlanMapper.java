package com.jida.mapper;

import com.jida.domain.Plan;
import com.jida.dto.req.PlanSaveRequestDto;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlanMapper {
    void save(List<Plan> plans);
    List<Plan> selectAll(long planListId);
    void update(Plan plan);
    void deleteAllPlan(long planListId);
    Optional<Plan> findById(long planId);
    void clickOpen(long planId);
}
