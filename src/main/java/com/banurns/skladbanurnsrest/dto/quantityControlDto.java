package com.banurns.skladbanurnsrest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class quantityControlDto {
    private Long id;
    private int c;
    private Long quantity;
}
