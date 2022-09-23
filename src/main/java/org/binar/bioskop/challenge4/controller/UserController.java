package org.binar.bioskop.challenge4.controller;

import org.binar.bioskop.challenge4.entity.UserEntity;
import org.binar.bioskop.challenge4.repository.UserRepository;
import org.binar.bioskop.challenge4.respon.Respon;
import org.binar.bioskop.challenge4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
//path = "/users", consumes = MediaType.APPLICATION_JSON_VALUE
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public Respon create(@RequestBody UserEntity userEntity){
       Respon ress = new Respon();
       ress.setResponCode("200");
       ress.setMessage("succes");
        ress.setData(userService.create(userEntity));
        return ress;
    }

    @PutMapping("/update/{user_id}")
    public Respon update(@PathVariable Long user_id, @RequestBody UserEntity userEntity){
        Respon ress = new Respon();
        ress.setResponCode("200");
        ress.setMessage("succes");
        ress.setData(userService.update(user_id, userEntity));
        return ress;
    }

    @GetMapping("/all")
    public Respon findAll(){
        Respon ress = new Respon();
        ress.setResponCode("200");
        ress.setMessage("succes");
        ress.setData(userService.findAll());
        return ress;
    }

    @DeleteMapping("/delete/{user_id}")
    public Respon delete(@PathVariable Long user_id){
        Respon ress = new Respon();
        ress.setResponCode("200");
        ress.setMessage("succes");
        ress.setData(userService.delete(user_id));
        return ress;
    }
}
