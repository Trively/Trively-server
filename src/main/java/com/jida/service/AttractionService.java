package com.jida.service;

import com.jida.domain.Attraction;
import com.jida.dto.res.attraction.AttractionListResponseDto;

import java.util.List;
import java.util.Map;

public interface AttractionService {
    AttractionListResponseDto showList(Map<String, Object> map);
    Attraction findById(Long attractionId);
    void addPlanCnt(List<Long> attractionId);
}
