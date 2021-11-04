package com.github.dto;

import com.github.dao.IDatabase;
import com.github.entity.User;
import com.github.exceptions.NoSuchUserException;
import com.github.exceptions.WrongPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserDTO implements IUserDTO {

    private final IDatabase iDatabase;

    public UserDTO(IDatabase iDatabase) {
        this.iDatabase = iDatabase;
    }

    @Override
    public void deleteUser(String login, String password) {
        if (getAccess(login, password))
            iDatabase.getUsers().remove(getEntity(login));
        else
            throw new WrongPasswordException("Wrong password");

    }

    @Override
    public void updateLogin(String login, String newLogin) {
        getEntity(login).setLogin(newLogin);
    }

    @Override
    public void updatePassword(String login, String password) {
        getEntity(login).setPassword(password);
    }

    @Override
    public void updateEmail(String login, String email) {
        getEntity(login).setEmail(email);
    }

    @Override
    public String getEmail(String login) {
        return getEntity(login).getEmail();
    }

    @Override
    public void createUser(String login, String password, String email) {
        iDatabase.getUsers().add(new User(login, password, email));
    }

    private User getEntity(String login) {
        return iDatabase.getUsers().stream()
                .filter(user -> Objects.equals(user.getLogin(), login))
                .findFirst()
                .orElseThrow(() -> new NoSuchUserException("There is no users with " + login + " login."));
    }

    private boolean getAccess(String login, String password) {
        return Objects.equals(getEntity(login).getPassword(), password);
    }

}
