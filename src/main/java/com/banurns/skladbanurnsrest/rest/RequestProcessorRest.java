package com.banurns.skladbanurnsrest.rest;

import com.banurns.skladbanurnsrest.dto.RequestDto;
import com.banurns.skladbanurnsrest.dto.quantityControlDto;
import com.banurns.skladbanurnsrest.model.Flavor;
import com.banurns.skladbanurnsrest.model.Miscellaneous;
import com.banurns.skladbanurnsrest.model.Request;
import com.banurns.skladbanurnsrest.service.FlavorService;
import com.banurns.skladbanurnsrest.service.MiscellaneousService;
import com.banurns.skladbanurnsrest.service.RequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/reqprocessor/")
public class RequestProcessorRest {
    private final RequestService requestService;
    private final FlavorService flavorService;

    private final MiscellaneousService miscellaneousService;

    @Autowired
    public RequestProcessorRest(RequestService requestService , FlavorService flavorService , MiscellaneousService miscellaneousService) {
        this.requestService = requestService;
        this.flavorService = flavorService;
        this.miscellaneousService = miscellaneousService;
    }

    @PostMapping(value = "closeRequest/{id}")
    public ResponseEntity closeRequest(@PathVariable Long id){
        Request request = requestService.closeRequest(id);
        if (request == null){
            log.warn("Request not found");
            return new ResponseEntity<>("Request not found", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Request was closed" , HttpStatus.OK);
    }


    @PostMapping(value = "closeActive")
    public ResponseEntity closeActive(){
        List<Request> closed = requestService.closeActive();
        if (closed  == null){
            log.warn("error while closing active request");
            return new ResponseEntity<>("not success", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("all active requests was closed", HttpStatus.OK);
    }

    @GetMapping(value = "getAllRequests")
    public ResponseEntity<List<Request>> getAllRequest(){
        List<Request> requests = requestService.getRequestList();
        return new ResponseEntity<>(requests , HttpStatus.OK);
    }

    @GetMapping(value = "getRequest/{id}")
    public ResponseEntity getRequest(@PathVariable Long id){
        Request request = requestService.findById(id);
        log.warn("Request tryna");
        if (request == null){
            log.warn("Request not found");
            return new ResponseEntity<>("Request not found", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    @PostMapping(value = "quantityControl")
    public ResponseEntity cquantity(@RequestBody quantityControlDto q){
        if (q.getC() == 0){
            Flavor f = flavorService.control(q.getId() , q.getQuantity());
            if (f == null){
                return new ResponseEntity<>("bad info" , HttpStatus.BAD_REQUEST);
            }
        } else if (q.getC() == 1){
            Miscellaneous f = miscellaneousService.control(q.getId() , q.getQuantity());
            if (f == null){
                return new ResponseEntity<>("bad info" , HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("success" , HttpStatus.OK);
    }
}
