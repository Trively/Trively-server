package com.jida.controller;

import com.jida.dto.res.attraction.AttractionListResponse;
import com.jida.dto.res.attraction.AttractionListResponseDto;
import com.jida.service.AttractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.jida.constants.SuccessCode.ATTRACTION_LIST_READ_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/attraction")
public class AttractionController {

    private final AttractionService attractionService;
    @GetMapping()
    public ResponseEntity<AttractionListResponse> showList(@RequestParam(required = false) Integer sidoCode,
                                                           @RequestParam(required = false) Long[] typeIds,
                                                           @RequestParam(required = false) String search,
                                                           @RequestParam(required = false) Long lastAttractId){
        Map<String, Object> map = new HashMap<>();
        map.put("sidoCode", sidoCode);
        map.put("typeIds", typeIds);
        map.put("search", search);
        map.put("lastAttractId", lastAttractId);
        AttractionListResponseDto responseDto = attractionService.showList(map);
        return AttractionListResponse.newResponse(ATTRACTION_LIST_READ_SUCCESS, responseDto);
    }
}
