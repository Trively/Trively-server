package com.jida.dto.res.attraction;

import java.util.List;

import com.jida.domain.Attraction;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AttractionListResponseDto {
    private List<AttractionList> attractions;

    private AttractionListResponseDto(List<AttractionList> list) {
        this.attractions = list;
    }
    public static AttractionListResponseDto of(List<AttractionList> list) {
        return new AttractionListResponseDto(list);
    }

    @Getter
    @NoArgsConstructor
    public static class AttractionList{
        private long attractionId;
        private long typeId;
        private String name;
        private String address;
        private String image1;
        private String image2;
        private double latitude;
        private double longitude;
        private int sidoCode;

        public static AttractionList of(Attraction attraction) {
            AttractionList attractionList = new AttractionList();
            attractionList.attractionId = attraction.getAttractionId();
            attractionList.typeId = attraction.getTypeId();
            attractionList.name = attraction.getName();
            attractionList.address = attraction.getAddress();
            attractionList.image1 = attraction.getImage1();
            attractionList.image2 = attraction.getImage2();
            attractionList.latitude = attraction.getLatitude();
            attractionList.longitude = attraction.getLongitude();
            attractionList.sidoCode = attraction.getSidoCode();
            return attractionList;
        }
    }
}

