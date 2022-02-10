package org.rebgy.curso.controllers;

import org.rebgy.curso.dao.UsuarioDao;
import org.rebgy.curso.models.User;
import org.rebgy.curso.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String loginUser(@RequestBody User user){
        User userL = usuarioDao.getUserByCredentials(user);

        if(userL != null){
            return jwtUtil.create(String.valueOf(userL.getId()), userL.getEmail());
        }else{
            return "FAIL";
        }
    }


}
