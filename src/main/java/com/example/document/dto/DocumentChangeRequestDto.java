package com.example.document.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Edi on 30.07.2019.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentChangeRequestDto {
    private String name;
    private String info;
}
