package co.atmax.rest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.atmax.model.LogIn;
import co.atmax.model.User;
import co.atmax.service.BookService;
import co.atmax.validation.service.BookUserValidator;

@RestController
public class BookController {

	@Autowired
	BookService service;

	@Autowired
	BookUserValidator validator;

	
	@GetMapping("/show")
	public ResponseEntity<String> getData()
	{
		return new ResponseEntity<String>("We are good to go....",HttpStatus.OK);
	}
	
	
	@PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> registerUser(@RequestBody User user) {
		ResponseEntity<String> rs = null;
		try {
			if (null != validator.validate(user)) {
				service.registerService(user);
				rs = new ResponseEntity<String>("User Resgistered Successfully", HttpStatus.OK);
				return rs;
			}
			rs = new ResponseEntity<String>("Either the fields are empty or invalid!!!", HttpStatus.OK);

		} catch (Exception e) {
			rs = new ResponseEntity<String>("Something Went Wrong Please Try Again Later!!!",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return rs;
	}

	@PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> loggedIn(@RequestBody LogIn in, HttpServletRequest req) {
		ResponseEntity<String> rs = null;
		try {
			if (null != validator.validateEmail(in.getEmail())) {
				if(service.loggedInService(in) == 1)
				{
					service.createSession(in.getEmail(), req);
					rs  = new ResponseEntity<String>("Successfully Logged In!!!",HttpStatus.OK);
					return rs;
				}			
				
				
			}
		} catch (Exception e) {
			rs = new ResponseEntity<String>("Credentials are invalid!!!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return rs;
	}

	@GetMapping(path = "/out", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> loggedOut(HttpServletRequest req) {
		ResponseEntity<String> rs = null;
		try
		{
			service.endSession(req);
			rs  = new ResponseEntity<String>("Successfully Logged Out!!!",HttpStatus.OK);
		}
		catch (Exception e) {
			rs  = new ResponseEntity<String>("There is internal problem in session logout logic!!!",HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		return rs;
	}

}
