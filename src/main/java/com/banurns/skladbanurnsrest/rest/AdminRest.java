package com.banurns.skladbanurnsrest.rest;

import com.banurns.skladbanurnsrest.dto.*;
import com.banurns.skladbanurnsrest.model.*;
import com.banurns.skladbanurnsrest.security.jwt.JwtUser;
import com.banurns.skladbanurnsrest.service.FlavorService;
import com.banurns.skladbanurnsrest.service.MiscellaneousService;
import com.banurns.skladbanurnsrest.service.StoreService;
import com.banurns.skladbanurnsrest.service.UserService;
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
@RequestMapping(value = "api/admin/")
public class AdminRest {
    private final UserService userService;
    private final FlavorService flavorService;
    private final MiscellaneousService miscellaneousService;
    private final StoreService storeService;

    @Autowired
    public AdminRest(UserService userService , FlavorService flavorService , MiscellaneousService miscellaneousService , StoreService storeService) {
        this.userService = userService;
        this.storeService = storeService;
        this.flavorService = flavorService;
        this.miscellaneousService = miscellaneousService;
    }

    @GetMapping(value = "users/{id}")
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        AdminUserDto result = AdminUserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "deleteUser/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>("norm" , HttpStatus.OK);
    }

    @GetMapping(value = "getAllUsers")
    public ResponseEntity<List<AdminUserDto>> getAllUsers(){
        List<User> users = userService.getAll();
        List<AdminUserDto> result = new ArrayList<>();
        for( User user : users ){
            result.add(AdminUserDto.fromUser(user));
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }


    @RequestMapping(value = "/register" , method = RequestMethod.POST)
    public ResponseEntity registerSM(@RequestBody RegisterDto dto) {
        if(userService.findByUsername(dto.getUsername()) != null) {
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        User user = dto.toUser();
        userService.register(user , dto.getRoleId());

        return new ResponseEntity<>("Success!", HttpStatus.OK);
    }


    @PostMapping(value = "addFlavor")
    public ResponseEntity addFlavor(@RequestBody FlavorDto flavordto){
        Flavor flavor = flavordto.toFlavor();
        Flavor flav = flavorService.addFlavor(flavor);
        return new ResponseEntity<>("norm" , HttpStatus.OK);
    }

    @PostMapping(value = "addMisc")
    public ResponseEntity addMisc(@RequestBody MiscellaneousDto dto){
        Miscellaneous misc = dto.toMisc();
        Miscellaneous savemisc = miscellaneousService.addMisc(misc);
        return new ResponseEntity<>("norm" , HttpStatus.OK);
    }

    @PostMapping(value = "deleteFlavor/{id}")
    public ResponseEntity deleteFlavor(@PathVariable Long id){
        flavorService.removeFlavorById(id);
        return new ResponseEntity<>("norm" , HttpStatus.OK);
    }

    @PostMapping(value = "deleteMisc/{id}")
    public ResponseEntity deleteMisc(@PathVariable Long id){
        miscellaneousService.removeMiscById(id);
        return new ResponseEntity<>("norm" , HttpStatus.OK);
    }

    @PostMapping(value ="disableFlavor/{id}")
    public ResponseEntity disableFlavor(@PathVariable Long id){
        flavorService.disableFlavorById(id);
        return new ResponseEntity<>("norm" , HttpStatus.OK);
    }

    @PostMapping(value ="enableFlavor/{id}")
    public ResponseEntity enableFlavor(@PathVariable Long id){
        flavorService.EnableFlavorById(id);
        return new ResponseEntity<>("norm" , HttpStatus.OK);
    }

    @PostMapping(value ="disableMisc/{id}")
    public ResponseEntity disableMisc(@PathVariable Long id){
        miscellaneousService.disableMiscById(id);
        return new ResponseEntity<>("norm" , HttpStatus.OK);
    }

    @PostMapping(value ="enableMisc/{id}")
    public ResponseEntity enableMisc(@PathVariable Long id){
        miscellaneousService.EnableMiscById(id);
        return new ResponseEntity<>("norm" , HttpStatus.OK);
    }

    @GetMapping(value = "getFlavors")
    public ResponseEntity<List<FlavorDto>> getFlavors(){
        List<Flavor> flavors =flavorService.getFlavors();
        List<FlavorDto> dtoList = new ArrayList<FlavorDto>();
        for ( Flavor flavor : flavors ){
            dtoList.add(FlavorDto.toDto(flavor));
        }
        return new ResponseEntity<>(dtoList , HttpStatus.OK);
    }

    @GetMapping(value = "getActiveFlavors")
    public ResponseEntity<List<FlavorDto>> getActiveFlavors(){
        List<Flavor> flavors =flavorService.getActiveFlavors();
        List<FlavorDto> dtoList = new ArrayList<FlavorDto>();
        for ( Flavor flavor : flavors ){
            dtoList.add(FlavorDto.toDto(flavor));
        }
        return new ResponseEntity<>(dtoList , HttpStatus.OK);
    }

    @GetMapping(value = "getActiveMisc")
    public ResponseEntity<List<MiscellaneousDto>> getActiveMisc(){
        List<Miscellaneous> misc =miscellaneousService.getActiveMiscs();
        List<MiscellaneousDto> dtoList = new ArrayList<MiscellaneousDto>();
        for ( Miscellaneous miss : misc ){
            dtoList.add(MiscellaneousDto.toDto(miss));
        }
        return new ResponseEntity<>(dtoList , HttpStatus.OK);
    }

    @GetMapping(value = "getAllMisc")
    public ResponseEntity<List<MiscellaneousDto>> getAllMisc(){
        List<Miscellaneous> misc =miscellaneousService.getMiscs();
        List<MiscellaneousDto> dtoList = new ArrayList<MiscellaneousDto>();
        for ( Miscellaneous miss : misc ){
            dtoList.add(MiscellaneousDto.toDto(miss));
        }
        return new ResponseEntity<>(dtoList , HttpStatus.OK);
    }

    @PostMapping(value = "addStore")
    public ResponseEntity addStore(@RequestBody StoreDto storeDto){
        Store store = storeDto.toStore();
        storeService.addStore(store);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PostMapping(value = "deleteStore/{id}")
    public ResponseEntity deleteStore(@PathVariable Long id){
        storeService.deleteStore(id);
        return new ResponseEntity<>("Success", HttpStatus.OK);
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
