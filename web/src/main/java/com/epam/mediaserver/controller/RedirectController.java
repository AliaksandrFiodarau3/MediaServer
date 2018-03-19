package com.epam.mediaserver.controller;


import com.epam.mediaserver.constant.Attribute;
import com.epam.mediaserver.constant.Message;
import com.epam.mediaserver.entity.User;
import com.epam.mediaserver.exception.ServiceException;
import com.epam.mediaserver.service.impl.UserTableService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
@EnableWebMvc
@RequestMapping("/")
public class RedirectController {

    private static final Logger LOGGER = LogManager.getLogger(RedirectController.class);

    @Autowired
    private UserTableService userService;

    @RequestMapping(method = RequestMethod.POST,
        value = "login")
    public RedirectView signIn(
        @RequestParam("loginUser")
            String login,
        @RequestParam("passwordUser")
            String password,
        HttpSession session) {

        try {
            User account = userService.signIn(login, (long) password.hashCode());
            session.setAttribute(Attribute.ATTRIBUTE_USER, account);

            if (account.getAdminRoot()) {
                return new RedirectView("admin");
            } else {
                return new RedirectView("user");
            }

        } catch (ServiceException e) {
            LOGGER.info(Message.AUTHORIZATION_ERROR, e);
            return new RedirectView("home");
        }
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
            session.setAttribute(Attribute.ATTRIBUTE_USER, userService.signUp(login, password, name, surname, email));
        } catch (ServiceException e) {
            LOGGER.info(Message.CLIENT_SERVICE_ERROR);
            return "redirect:home";
        }
        return "redirect:user";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String homePage() {
        return "home";
    }

    @RequestMapping(value = "signOut", method = RequestMethod.GET)
    public String signOut(HttpSession session) {

        session.setAttribute(Attribute.ATTRIBUTE_USER, null);

        return "redirect:/";
    }

}
