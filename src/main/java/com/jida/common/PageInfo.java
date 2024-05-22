package com.jida.common;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PageInfo implements Serializable {
    private final int pageIndex;
    private final int pageSize;
    private final int totalCount;

    public int getTotalPage() {
        return (int) Math.ceil((double) totalCount / pageSize);
    }
}
