package org.binar.bioskop.challenge4.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.binar.bioskop.challenge4.entity.UserEntity;
import org.binar.bioskop.challenge4.repository.UserRepository;
import org.binar.bioskop.challenge4.respon.Respon;
import org.binar.bioskop.challenge4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//path = "/users", consumes = MediaType.APPLICATION_JSON_VALUE
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @Operation(summary = "this is to create user from Database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "create user into from Database User",
                    content = {@Content(mediaType="application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Request Not Avilable",
                    content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity create(@RequestBody UserEntity userEntity){
       Respon ress = new Respon();
       ress.setResponCode("200");
       ress.setMessage("succes");
        ress.setData(userService.create(userEntity));
        return ResponseEntity.ok().body(ress);
    }

    @Operation(summary = "this is to update user from Database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "update user into from Database User",
                    content = {@Content(mediaType="application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Request Not Avilable",
                    content = @Content)
    })
    @PutMapping("/update/{user_id}")
    public ResponseEntity update(@PathVariable Long user_id, @RequestBody UserEntity userEntity){
        Respon ress = new Respon();
        ress.setResponCode("200");
        ress.setMessage("succes");
        ress.setData(userService.update(user_id, userEntity));
        return ResponseEntity.ok().body(ress);
    }

    @Operation(summary = "this is to Find All user from Database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Find All user into from Database User",
                    content = {@Content(mediaType="application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Request Not Avilable",
                    content = @Content)
    })
    @GetMapping("/all")
    public ResponseEntity findAll(){
        Respon ress = new Respon();
        ress.setResponCode("200");
        ress.setMessage("succes");
        ress.setData(userService.findAll());
        return ResponseEntity.ok().body(ress);
    }

    @Operation(summary = "this is to delete user from Database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "delete user into from Database User",
                    content = {@Content(mediaType="application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Request Not Avilable",
                    content = @Content)
    })
    @DeleteMapping("/delete/{user_id}")
    public ResponseEntity delete(@PathVariable Long user_id){
        Respon ress = new Respon();
        ress.setResponCode("200");
        ress.setMessage("succes");
        ress.setData(userService.delete(user_id));
        return ResponseEntity.ok().body(ress);
    }
}
