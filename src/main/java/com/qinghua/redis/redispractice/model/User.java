package com.qinghua.redis.redispractice.model;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private Long id;
    private List<Long> friends;
}
