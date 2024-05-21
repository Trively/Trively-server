package com.jida.mapper;

import com.jida.domain.Member;
import com.jida.domain.Plan;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface PlanMapper {
    void save(List<Plan> plans);
    List<Plan> selectAll(long planListId);
    void update(Plan plan);
    void deleteAllPlan(long planListId);

}
