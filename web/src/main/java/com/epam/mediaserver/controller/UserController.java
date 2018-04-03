/*
package com.epam.mediaserver.controller;

import com.epam.mediaserver.constant.Attribute;
import com.epam.mediaserver.constant.Message;
import com.epam.mediaserver.entity.User;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpSession;

@Controller
@EnableWebMvc
@RequestMapping("/")
public class UserController {

    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST,
        value = "registration")
    public String signUp(
        @ModelAttribute("loginUser")
            String login,
        @ModelAttribute("passwordUser")
            String password,
        @ModelAttribute("nameUser")
            String name,
        @ModelAttribute("surnameUser")
            String surname,
        @ModelAttribute("emailUser")
            String email,
        HttpSession session) {

        try {
            User user = User.builder()
                .login(login)
                .password(password)
                .name(name)
                .surname(surname)
                .email(email)
                .build();

            session.setAttribute(Attribute.ATTRIBUTE_USER, userService.signUp(user));

        } catch (ServiceException e) {
            LOGGER.info(Message.CLIENT_SERVICE_ERROR);
            return "redirect:home";
        }
        return "redirect:user";
    }

    @RequestMapping(value = "signOut")
    public String signOut(HttpSession session) {

        session.setAttribute(Attribute.ATTRIBUTE_USER, null);

        return "redirect:/";
    }
}
*/
