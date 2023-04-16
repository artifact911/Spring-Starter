package com.art.spring.http.controller;

import com.art.spring.database.repository.CompanyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class GreetingController {

    public ModelAndView hello(ModelAndView modelAndView, HttpServletRequest request, CompanyRepository companyRepository) {

        modelAndView.setViewName("greeting/hello");

        return modelAndView;
    }

    public ModelAndView bye(ModelAndView modelAndView) {

        modelAndView.setViewName("greeting/bye");

        return modelAndView;
    }
}
