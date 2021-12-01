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

    @PostMapping("{id}")
    public UserDto createUser(@PathVariable Integer id, @RequestBody UserDto userDto) {
        return iUserService.createUser(id, userDto);
    }

    @GetMapping("{id}")
    public UserDto readUser(@PathVariable Integer id) {
        return iUserService.readUser(id);
    }

    @PutMapping("{id}")
    public UserDto update(@PathVariable Integer id, @RequestBody UserDto userDto) {
        return iUserService.update(id, userDto);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable("id") Integer id) {
        iUserService.deleteUser(id);
    }
}
