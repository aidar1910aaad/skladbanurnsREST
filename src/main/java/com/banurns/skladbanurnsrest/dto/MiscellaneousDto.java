package com.banurns.skladbanurnsrest.dto;

import com.banurns.skladbanurnsrest.model.Miscellaneous;
import com.banurns.skladbanurnsrest.model.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MiscellaneousDto {
    private Long id;

    private String name;
    private String description;
    private Status status;

    private Long quantity;
    private String barcode;

    public Miscellaneous toMisc() {
        Miscellaneous misc = new Miscellaneous();
        misc.setName(this.name);
        misc.setDescription(this.description);
        misc.setStatus(this.status);
        misc.setId(this.id);
        misc.setQuantity(this.quantity);
        misc.setBarcode(this.barcode);
        return misc;
    }

    public static MiscellaneousDto toDto(Miscellaneous misc) {
        MiscellaneousDto dto = new MiscellaneousDto();
        dto.setId(misc.getId());
        dto.setName(misc.getName());
        dto.setDescription(misc.getDescription());
        dto.setStatus(misc.getStatus());
        dto.setBarcode(misc.getBarcode());
        dto.setQuantity(misc.getQuantity());
        return dto;
    }
}
