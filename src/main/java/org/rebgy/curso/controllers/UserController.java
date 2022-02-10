package org.rebgy.curso.controllers;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.rebgy.curso.dao.UsuarioDao;
import org.rebgy.curso.models.User;
import org.rebgy.curso.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/user/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id){
        User user = new User();
        user.setId(id);
        user.setNombre("Juan");
        user.setApellido("Bernal");
        user.setTelefono("3213124124");
        user.setEmail("giobernal@gmail.com");
        user.setPassword("234324142");
        return user;
    }
    @RequestMapping(value = "api/users", method = RequestMethod.GET)
    public List<User> getUser(@RequestHeader(value = "Authorization") String token){
        if(!tokenValidate(token))
            return null;

        return usuarioDao.getUsers();
    }

    private boolean tokenValidate(String token){
        String idUser = jwtUtil.getKey(token);

        return idUser != null;
    }

    @RequestMapping(value = "api/users", method = RequestMethod.POST)
    public void registerUser(@RequestBody User user){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1,1024, 1,user.getPassword());
        user.setPassword(hash);

        usuarioDao.registerUsers(user);
    }

    @RequestMapping(value = "user2")
    public User modifyUser() {
        User user = new User();
        user.setNombre("Andres");
        user.setApellido("Rueda");
        user.setTelefono("43422343242");
        user.setEmail("ruedaa@gmail.com");
        user.setPassword("6733456346");
        return user;
    }

    @RequestMapping(value = "api/user/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@RequestHeader(value = "Authorization") String token,
                           @PathVariable Long id) {

        if(!tokenValidate(token))
            return;
        usuarioDao.deleteUser(id);
    }
    @RequestMapping(value = "user1")
    public User searchUser() {
        User user = new User();
        user.setNombre("Juan");
        user.setApellido("Bernal");
        user.setTelefono("3213124124");
        user.setEmail("giobernal@gmail.com");
        user.setPassword("234324142");
        return user;
    }
}
