package com.github.controller;

import com.github.dto.UserDto;
import com.github.service.api.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("user-management")
public class UserController {

    private final IUserService iUserService;

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        return iUserService.createUser(userDto);
    }

    @GetMapping("{userId}")
    public UserDto readUser(@PathVariable Integer userId) {
        return iUserService.readUser(userId);
    }

    @PutMapping("{userId}")
    public UserDto update(@PathVariable Integer userId, @RequestBody UserDto userDto) {
        return iUserService.update(userId, userDto);
    }

    @DeleteMapping("{userId}")
    public void deleteUser(@PathVariable Integer userId) {
        iUserService.deleteUser(userId);
    }
}
