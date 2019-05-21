package com.spring.config.annotation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.config.annotation.dao.BookDao;

@Service
public class BookService {
	@Autowired
	private BookDao bookDao;
}
