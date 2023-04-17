package com.art.spring.http.controller;

import com.art.spring.database.repository.CompanyRepository;
import com.art.spring.dto.UserReadDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api/v1")
@SessionAttributes({"user"})
public class GreetingController {

    @GetMapping("/hello/")
    public ModelAndView hello(ModelAndView modelAndView, HttpServletRequest request) {

        modelAndView.setViewName("greeting/hello");
        modelAndView.addObject("user", new UserReadDto(1L, "Ivan"));

        return modelAndView;
    }

    @GetMapping("/bye")
    public ModelAndView bye(@SessionAttribute("user") UserReadDto user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("greeting/bye");

        return modelAndView;
    }


    // перенесли пока, пока развлекаемся с новым /hello
    @GetMapping("/hello/{id}")
    public ModelAndView hello2(ModelAndView modelAndView, HttpServletRequest request,
                               // value в анноташке можно опустить, если нейминг переменной совпадает
                               @RequestParam Integer age,
                               @RequestHeader String accept,
                               @CookieValue("JSESSIONID") String jsessionId,
                               @PathVariable("id") Integer id) {

        // аналог аннотаций:
        String ageParamValue = request.getParameter("age");
        String acceptHeader = request.getHeader("accept");
        Cookie[] cookies = request.getCookies();

        modelAndView.setViewName("greeting/hello");

        return modelAndView;
    }
}
