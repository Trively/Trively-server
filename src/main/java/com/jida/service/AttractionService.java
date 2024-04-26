package com.jida.service;

import com.jida.dto.res.attraction.AttractionListResponseDto;

import java.util.Map;

public interface AttractionService {
    AttractionListResponseDto showList(Map<String, Object> map);
}
