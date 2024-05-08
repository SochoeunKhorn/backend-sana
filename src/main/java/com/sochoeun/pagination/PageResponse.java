package com.sochoeun.pagination;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PageResponse {
    private List<?> list;
    private Pagination pagination;

    public PageResponse(Page<?> page){
        this.list = page.getContent();
        this.pagination = Pagination.builder()
                .pageSize(page.getSize())
                .pageNumber(page.getNumber()+1)
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .numberOfElements(page.getNumberOfElements())
                .empty(page.isEmpty())
                .first(page.isFirst())
                .last(page.isLast())
                .build();
    }
}
