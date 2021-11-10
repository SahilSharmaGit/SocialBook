package co.atmax.rest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.atmax.service.BookService;

@RestController
public class PostController {

	@Autowired
	BookService service;
	
	@PostMapping(path = "/create",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createPost(@RequestBody String message,HttpServletRequest req)
	{
		ResponseEntity<String> rs =  null;
		try
		{
			if(service.isLoggedIn(req))
			{
				service.postMessage(message, req);
				rs  =  new ResponseEntity<String>("Post Created Successfully!!!", HttpStatus.OK);
				return rs;
			}
			rs  =  new ResponseEntity<String>("Please Login to create post", HttpStatus.OK);
		}
		catch(Exception e)
		{
			rs  =  new ResponseEntity<String>("Something went wrong!!!", HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		return rs;
	}
}
