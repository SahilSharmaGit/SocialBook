package co.atmax.validation.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import co.atmax.model.User;

public class BookUserValidator {

	public User validate(User user) {
System.out.println(user.getEmail()+" "+user.getPassword()+" "+user.getAge()+" "+user.getMobileno());
		if (user != null) {
			
			// validating fields
			String email = validateEmail(user.getEmail());
			String password = validatePassword(user.getPassword());
			int age = validateAge(user.getAge());
			String mobile = validateMobile(user.getMobileno());
			System.out.println("*************"+email+" "+password+" "+age+" "+mobile+"**********");
			// if success then forward to register
			if (null != email && null != password && age>0 && null != mobile ) {
				return user;
			}
		}
		return null;
	}
// VALIDATION LOGIC:
	public String validateEmail(String email) {
		String regex = "^(.+)@(.+)$";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()) {
			return email;
		}
		return null;
	}

	public String validatePassword(String password) {
		if (password.length() >= 10) {
			return password;
		}
		return null;
	}

	public String validateMobile(String mobile) {
		if (mobile.length() == 10) {
			return mobile;
		}
		return null;
	}

	public int validateAge(int age) {
		if (age > 1) {
			return age;
		}
		return 0;
	}
}
