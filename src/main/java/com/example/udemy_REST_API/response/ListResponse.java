package com.example.udemy_REST_API.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListResponse <T> {
    private List<T> list;
    private Long count;
}
