package com.banurns.skladbanurnsrest.rest;


import com.banurns.skladbanurnsrest.dto.FlavorDto;
import com.banurns.skladbanurnsrest.dto.MiscellaneousDto;
import com.banurns.skladbanurnsrest.dto.RequestDto;
import com.banurns.skladbanurnsrest.model.Flavor;
import com.banurns.skladbanurnsrest.model.Miscellaneous;
import com.banurns.skladbanurnsrest.model.Request;
import com.banurns.skladbanurnsrest.model.Store;
import com.banurns.skladbanurnsrest.security.jwt.JwtUser;
import com.banurns.skladbanurnsrest.service.FlavorService;
import com.banurns.skladbanurnsrest.service.MiscellaneousService;
import com.banurns.skladbanurnsrest.service.RequestService;
import com.banurns.skladbanurnsrest.service.StoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/salesmanager/")
public class SalesManagerRest {

    private final RequestService requestService;

    private final MiscellaneousService miscellaneousService;

    private final FlavorService flavorService;

    private final StoreService storeService;

    @Autowired
    public SalesManagerRest(RequestService requestService , MiscellaneousService miscellaneousService , FlavorService flavorService , StoreService storeService) {
        this.requestService = requestService;
        this.storeService = storeService;
        this.miscellaneousService = miscellaneousService;
        this.flavorService = flavorService;
    }

    @PostMapping(value = "addRequest")
    public ResponseEntity addRequest(@RequestBody RequestDto requestDto){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        JwtUser jwtUser = (JwtUser) auth.getPrincipal();

        Request request = requestDto.toRequest();
        requestService.addRequest(request , jwtUser.getId());
        return new ResponseEntity<>("Request added successfully" , HttpStatus.OK);
    }

    @GetMapping(value = "getMisc")
    public ResponseEntity<List<MiscellaneousDto>> getActiveMisc(){
        List<Miscellaneous> misc =miscellaneousService.getActiveMiscs();
        List<MiscellaneousDto> dtoList = new ArrayList<MiscellaneousDto>();
        for ( Miscellaneous miss : misc ){
            dtoList.add(MiscellaneousDto.toDto(miss));
        }
        return new ResponseEntity<>(dtoList , HttpStatus.OK);
    }

    @GetMapping(value = "getFlavors")
    public ResponseEntity<List<FlavorDto>> getActiveFlavors(){
        List<Flavor> flavors =flavorService.getActiveFlavors();
        List<FlavorDto> dtoList = new ArrayList<FlavorDto>();
        for ( Flavor flavor : flavors ){
            dtoList.add(FlavorDto.toDto(flavor));
        }
        return new ResponseEntity<>(dtoList , HttpStatus.OK);
    }

    @GetMapping(value = "getAllStores")
    public ResponseEntity getAllStores(){
        List<Store> stores = storeService.getallStores();
        return new ResponseEntity<>(stores , HttpStatus.OK);
    }

    @GetMapping(value = "getStore/{id}")
    public ResponseEntity getStoreById(@PathVariable Long id){
        Store store = storeService.getStoreById(id);
        if (store == null){
            return new ResponseEntity<>("does not exist", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(store, HttpStatus.OK);
    }

}
