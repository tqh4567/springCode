package com.spring.config.annotation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.spring.config.annotation.service.BookService;

@Controller
public class BookController {
	@Autowired
	private BookService bookService;
}
