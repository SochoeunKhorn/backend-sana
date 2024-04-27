package com.sochoeun.pagination;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface PageUtil {
    Integer DEFAULT_PAGE_LIMIT=5;
    Integer DEFAULT_PAGE_NUMBER=1;
    String PAGE_LIMIT="pageSize";
    String PAGE_NUMBER="pageNo";

    static Pageable getPageable(int pageNo, int pageSize){
        if(pageNo < DEFAULT_PAGE_NUMBER){
            pageNo = DEFAULT_PAGE_NUMBER;
        }
        if(pageSize <0 ){
            pageSize = DEFAULT_PAGE_LIMIT;
        }

        Pageable pageable;
        pageable = PageRequest.of(pageNo-1,pageSize);
        return pageable;
    }
}
