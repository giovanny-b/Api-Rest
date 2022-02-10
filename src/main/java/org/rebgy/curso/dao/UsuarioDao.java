package org.rebgy.curso.dao;

import org.rebgy.curso.models.User;

import java.util.List;

public interface UsuarioDao {

    List<User> getUsers();

    void deleteUser(Long id);

    void registerUsers(User user);

    User getUserByCredentials(User user);
}