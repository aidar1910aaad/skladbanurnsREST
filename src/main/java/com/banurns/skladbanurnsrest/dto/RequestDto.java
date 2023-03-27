package com.banurns.skladbanurnsrest.dto;

import com.banurns.skladbanurnsrest.model.Flavor;
import com.banurns.skladbanurnsrest.model.Miscellaneous;
import com.banurns.skladbanurnsrest.model.Request;
import com.banurns.skladbanurnsrest.model.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestDto {
    private Long id;
    private Long storeid;
    private String flavors;
    private String miscs;
    private Long creationuserid;
    private String description;
    private Status status;

    public Request toRequest(){
        Request request = new Request();
        request.setId(id);
        request.setStoreid(storeid);
        request.setFlavors(flavors);
        request.setMiscs(miscs);
        request.setCreationuserid(creationuserid);
        request.setDescription(description);
        request.setStatus(status);
        return request;
    }

    public static RequestDto fromRequest(Request request){
        RequestDto requestDto = new RequestDto();
        requestDto.setId(request.getId());
        requestDto.setStoreid(request.getStoreid());
        requestDto.setMiscs(request.getMiscs());
        requestDto.setFlavors(requestDto.getFlavors());
        requestDto.setCreationuserid(request.getCreationuserid());
        requestDto.setDescription(request.getDescription());
        requestDto.setStatus(request.getStatus());
        return requestDto;
    }
}
