package com.wj.spring.mvc.rest.ctrl;

import com.wj.spring.mvc.rest.model.User;
import com.wj.spring.mvc.rest.service.UserService;
import com.wj.spring.mvc.rest.util.EgWebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserCtrl {
    private UserService userService;

    @Autowired
    public UserCtrl(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password) {
        userService.regist(username, password);
        return EgWebUtils.createSuccessJSON();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        User user = userService.login(username, password);
        return EgWebUtils.createCommonResultJSON("user", user);
    }

    @RequestMapping(value = "/all")
    public String all() {
        List<User> users = userService.getAll();
        return EgWebUtils.createCommonResultJSON("user", users);
    }


}
