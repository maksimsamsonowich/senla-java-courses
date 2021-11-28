package com.github.controller;

import com.github.dto.UserDto;
import com.github.service.api.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("user")
public class UserController {

    private final IUserService iUserService;

    @PostMapping("register")
    public UserDto createUser(@RequestBody UserDto userDto) {
        return iUserService.createUser(userDto);
    }

    @GetMapping("view/{id}")
    public UserDto readUser(@PathVariable Integer id) {
        return iUserService.readUser(id);
    }

    @PutMapping("edit/{id}")
    public UserDto update(@PathVariable Integer id,
                          @RequestBody UserDto userDto) {
        return iUserService.update(userDto);
    }

    @DeleteMapping("delete/{id}")
    public void deleteUser(@PathVariable Integer id,
                           @RequestBody UserDto userDto) {
        iUserService.deleteUser(userDto);
    }
}
