package co.atmax.model;

public class User {

	private String email;
	private String password;
	private int age;
	private String mobileno;
	
	public User()
	{}
	
	public User(String email, String password, int age, String mobileno) {
		this.email = email;
		this.password = password;
		this.age = age;
		this.mobileno = mobileno;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
}
