package com.example.clientrsocket;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BookPayload {
    private int id;
    private List<String> cat;
    private String name;
    private String author;
}
