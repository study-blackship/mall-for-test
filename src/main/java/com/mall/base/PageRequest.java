package com.mall.base;

import lombok.Data;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class PageRequest {
    private Integer page = 1;
    private Integer size = 10;
    private Sort.Direction direction = Sort.Direction.DESC;
    private List<String> properties = new ArrayList<>(Collections.singletonList("id"));

    public void setPage(Integer page) {
        this.page = page <= 0 ? 1 : page;
    }

    public void setSize(Integer size) {
        int DEFAULT_SIZE = 10;
        Integer MAX_SIZE = 100;
        this.size = size > MAX_SIZE ? DEFAULT_SIZE : size;
    }

    public org.springframework.data.domain.PageRequest of() {
        return org.springframework.data.domain.PageRequest.of(page - 1, size, direction, properties.toArray(new String[0]));
    }
}
