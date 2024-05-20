package com.jida.mapper;

import com.jida.domain.Attraction;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface AttractionMapper {
    List<Attraction> showList(Map<String, Object> map);
    Optional<Attraction> findById(Long attractionId);
    void addPlanCnt(List<Long> attractionIds);
}
