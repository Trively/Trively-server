package com.jida.controller;

import com.jida.dto.res.attraction.AttractionListResponse;
import com.jida.dto.res.attraction.AttractionListResponseDto;
import com.jida.service.AttractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.jida.constants.SuccessCode.ATTRACTION_LIST_READ_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/attraction")
public class AttractionController {

    private final AttractionService attractionService;
    @GetMapping("/{sidoCode}/{typeId}")
    public ResponseEntity<AttractionListResponse> showList(@PathVariable Integer sidoCode, @PathVariable Long typeId){
        Map<String, Object> map = new HashMap<>();
        map.put("sidoCode", sidoCode);
        map.put("typeId", typeId);
        AttractionListResponseDto responseDto = attractionService.showList(map);
        return AttractionListResponse.newResponse(ATTRACTION_LIST_READ_SUCCESS, responseDto);
    }
}
