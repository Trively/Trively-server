package com.jida.service;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;
import static com.jida.dto.res.attraction.AttractionListResponseDto.*;

import com.jida.dto.res.attraction.AttractionListResponseDto;
import com.jida.mapper.AttractionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {
    private final AttractionMapper attractionMapper;
    @Override
    public AttractionListResponseDto showList(Map<String, Object> map) {
        List<AttractionList> list = new ArrayList<>();
        list = attractionMapper.showList(map).stream()
                .map(AttractionList::of)
                .collect(Collectors.toList());
        return AttractionListResponseDto.of(list);

    }
}
