package com.jida.mapper;

import com.jida.domain.Plan;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlanMapper {
    void save(List<Plan> plans);
}
