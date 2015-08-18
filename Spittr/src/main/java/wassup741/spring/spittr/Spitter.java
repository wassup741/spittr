package wassup741.spring.spittr;

public class Spitter {
	private final Long id;
	private final String userName;
	private String firstName;
	private String lastName;
	private String password;

	public Spitter(String userName, String firstName, String lastName,
			String password) {
		this(null, userName, firstName, lastName, password);
	}

	public Spitter(Long id, String userName, String firstName, String lastName,
			String password) {
		super();
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

}
