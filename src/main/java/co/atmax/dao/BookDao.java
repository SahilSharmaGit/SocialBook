package co.atmax.dao;

import java.time.LocalDateTime;

import co.atmax.model.LogIn;
import co.atmax.model.User;

public interface BookDao {

	public Object register(User user);
	public int doLog(LogIn in);
	
	public Object doLog(User user);
	public void savePost(String id, String message, String date);
}
