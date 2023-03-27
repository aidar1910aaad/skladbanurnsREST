package com.banurns.skladbanurnsrest.dto;

import com.banurns.skladbanurnsrest.model.Flavor;
import com.banurns.skladbanurnsrest.model.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlavorDto {
    private Long id;
    private String name;
    private String description;

    private Long quantity;

    private String barcode;
    private Status status;

    public Flavor toFlavor(){
        Flavor flavor = new Flavor();
        flavor.setId(this.id);
        flavor.setName(this.name);
        flavor.setDescription(this.description);
        flavor.setStatus(this.status);
        flavor.setQuantity(this.quantity);
        flavor.setBarcode(this.barcode);
        return flavor;
    }

    public static FlavorDto toDto(Flavor flavor) {
        FlavorDto dto = new FlavorDto();
        dto.setName(flavor.getName());
        dto.setDescription(flavor.getDescription());
        dto.setStatus(flavor.getStatus());
        dto.setId(flavor.getId());
        dto.setQuantity(flavor.getQuantity());
        dto.setBarcode(flavor.getBarcode());
        return dto;
    }
}
