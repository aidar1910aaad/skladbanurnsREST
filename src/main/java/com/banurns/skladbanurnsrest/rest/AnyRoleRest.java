package com.banurns.skladbanurnsrest.rest;

import com.banurns.skladbanurnsrest.dto.RoleDto;
import com.banurns.skladbanurnsrest.model.Role;
import com.banurns.skladbanurnsrest.security.jwt.JwtUser;
import com.banurns.skladbanurnsrest.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "api/anyrole/")
public class AnyRoleRest {
    private final UserService userService;
    @Autowired
    public AnyRoleRest(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "getInfo")
    public ResponseEntity getRole(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        JwtUser jwtUser = (JwtUser) auth.getPrincipal();
        List<Role> roles = userService.getRolesById(jwtUser.getId());
        RoleDto roleDto = RoleDto.fromRole(roles.get(0));
        return new ResponseEntity<>(roleDto , HttpStatus.OK);
    }


}
