package co.atmax.service;

import javax.servlet.http.HttpServletRequest;

import co.atmax.model.LogIn;
import co.atmax.model.User;

public interface BookService {
	public Object registerService(User user);
	public int loggedInService(LogIn in);

	public Object loggedInService(User user);
	public void createSession(String email, HttpServletRequest req);
	public void endSession(HttpServletRequest req);
	public boolean isLoggedIn(HttpServletRequest req);
	public void postMessage(String message,  HttpServletRequest req);
}