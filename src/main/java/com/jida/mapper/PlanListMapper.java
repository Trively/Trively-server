package com.jida.mapper;

import com.jida.domain.PlanList;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface PlanListMapper {
    void save(PlanList planList);
    Optional<PlanList> findById(Long planListId);
    void delete(Long planListId);
    void update(Map<String, Object> map);
    List<PlanList> findAllByMemberId(Long memberId);
}
