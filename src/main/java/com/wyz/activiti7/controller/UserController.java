package com.wyz.activiti7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wyz.activiti7.model.User;
import com.wyz.activiti7.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/delete/{id}")
    public void delete(@PathVariable("id") int id) {
        userService.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/insert")
    public void insert(@RequestBody User user) {
        userService.insert(user);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update/{id}")
    public void update(@RequestParam User user) {
        userService.update(user);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/select")
    public User select(@PathVariable("id") int id) {
        return userService.selectById(id);
    }

   
}