package com.github.controller;

import com.github.dto.UserDto;
import com.github.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("user-management")
public class UserController {

    private final IUserService iUserService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(iUserService.createUser(userDto));
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("{userId}")
    public ResponseEntity<UserDto> readUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(iUserService.readUser(userId));
    }

    @PutMapping("{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Integer userId, @RequestBody UserDto userDto) {
        return ResponseEntity.ok(iUserService.update(userId, userDto));
    }

    @DeleteMapping("{userId}")
    public void deleteUser(@PathVariable Integer userId) {
        iUserService.deleteUser(userId);
    }

}
