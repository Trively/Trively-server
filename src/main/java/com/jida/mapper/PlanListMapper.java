package com.jida.mapper;

import com.jida.domain.Member;
import com.jida.domain.PlanList;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface PlanListMapper {
    void save(PlanList planList);
    Optional<PlanList> findById(Long planListId);
}
