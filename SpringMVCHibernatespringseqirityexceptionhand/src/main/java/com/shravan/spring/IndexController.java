package com.shravan.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shravan.spring.model.Person;
import com.shravan.spring.model.PersonDetail;
import com.shravan.spring.service.PersonService;

@Controller
public class IndexController {
 
	@Autowired
	PersonService userService;

	@RequestMapping(value = "/userdtl", method = RequestMethod.POST)
	public String listPersons(HttpServletRequest request,HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		String password = request.getParameter("password");
		
		PersonDetail personDetail = userService.getPersonDtlById(id);
		if (personDetail != null) {
			return ("UserManagement"); 
		} else {
			 
			return ("error"); 
		}

	}

}