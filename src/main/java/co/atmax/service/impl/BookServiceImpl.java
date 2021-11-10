package co.atmax.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import co.atmax.dao.BookDao;
import co.atmax.model.LogIn;
import co.atmax.model.User;
import co.atmax.service.BookService;

public class BookServiceImpl implements BookService {

	@Autowired
	BookDao dao;

	@Override
	public Object registerService(User user) {
		// TODO Auto-generated method stub
		return dao.register(user);
	}

	@Override
	public int loggedInService(LogIn in) {
		// TODO Auto-generated method stub
		return dao.doLog(in);
	}

	@Override
	public Object loggedInService(User user) {
		// TODO Auto-generated method stub
		return dao.doLog(user);
	}

	@Override
	public void createSession(String email, HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.setAttribute("userid", email);
		session.setMaxInactiveInterval(60 * 60);
		Cookie ck = new Cookie("userid", email);
		ck.setMaxAge(60*60);
		System.out.println("Session Successfully created: " + session);
	}

	@Override
	public void endSession(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		Cookie[] cks = req.getCookies();
		System.out.println("====================");
		if(null != session && null!=cks)
		{
			for(Cookie ck : cks)
			{
				System.out.println(ck.getName()+"  "+ck.getValue());
			/*	if(ck.getName().equals("userid")) 
					System.out.println("Logout data: "+ck.getValue());
		*/		
				if(ck.getName().equals("JSESSIONID"))
					System.out.println("Id: "+ck.getValue());
				ck.setMaxAge(0);
			}
			Enumeration<String> e = session.getAttributeNames();
			while(e.hasMoreElements())
			{
				if(e.nextElement() == "userid")
				{
					session.invalidate();
					session.setMaxInactiveInterval(0);
				}
			}
			if(null != session)
			{
				session.invalidate();
			}
			session = null;
			cks = null;
			System.out.println("Session Succesfully Ended");
		}
	}
	
	@Override
	public boolean isLoggedIn(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if(null != session){
			return true;
		}
		return false;
	}
@Override
	public void postMessage(String message, HttpServletRequest req) {
		// TODO Auto-generated method stub
		if(isLoggedIn(req))
		{
			HttpSession session = req.getSession(false);
			Enumeration<String> e = session.getAttributeNames();
			String id = null;
			String value = null;
			while(e.hasMoreElements())
			{
				id = e.nextElement();
				if(id.equals("userid") || id == "userid")
				{
					value = session.getAttribute(id).toString();
					break;
				}
			}
			  LocalDateTime now = LocalDateTime.now();  
		       DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
		       String formatDateTime = now.format(format);  
		    dao.savePost(value, message, formatDateTime);
		}
	}
}
