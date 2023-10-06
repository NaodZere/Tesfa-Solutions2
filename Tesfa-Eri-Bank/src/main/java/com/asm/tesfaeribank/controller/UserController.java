package com.asm.tesfaeribank.controller;

import com.asm.tesfaeribank.dto.UserDto;
import com.asm.tesfaeribank.security.JwtGenerator;
import com.asm.tesfaeribank.security.ResponseDto;
import com.asm.tesfaeribank.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private JwtGenerator jwtGenerator;


    @GetMapping("/username/{username}")
    public ResponseEntity<UserDto> readByUsername(@PathVariable String username) {
        try {
            return new ResponseEntity<>(userService.findByUsername(username), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("allusers")
    public ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<>(userService.readAll(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public UserDto readById(@PathVariable String id) {
        return userService.readById(id);
    }

    @PostMapping("creatUser")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {

        try {
            return new ResponseEntity<>(userService.create(userDto), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public UserDto updateById(@RequestBody UserDto userDto, @PathVariable String id) {
        return userService.update(id, userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        userService.deleteById(id);


    }


    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody UserDto userDto) {

        try {


            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtGenerator.generateToken(authentication);
            return new ResponseEntity<>(new ResponseDto(token), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new ResponseDto("Error Parsing JWT"), HttpStatus.resolve(401));

        }
    }


}
