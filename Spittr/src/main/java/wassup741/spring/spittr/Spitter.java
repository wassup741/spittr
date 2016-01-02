package wassup741.spring.spittr;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Email;

public class Spitter {
	private Long id;

	@NotNull
	@Size(min = 5, max = 16, message = "{username.size}")
	private String username;
	
	@NotNull
	@Size(min = 5, max = 25, message = "{password.size}")
	private String password;
	
	@NotNull
	@Size(min = 2, max = 30, message = "{firstName.size}")
	private String firstName;
	
	@NotNull
	@Size(min = 2, max = 30, message = "{lastName.size}")
	private String lastName;
	
	@NotNull
	@Email(message = "{email.valid}")
	private String email;

	public Spitter() {

	}

	public Spitter(String username, String firstName, String lastName,
			String password) {
		this(null, username, firstName, lastName, password);
	}

	public Spitter(Long id, String username, String firstName, String lastName,
			String password) {
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}

	public String getUsername() {
		return username;
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

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Spitter == false) {
			return false;
		}
		Spitter that = (Spitter) obj;

		return new EqualsBuilder().append(username, that.username).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(username).toHashCode();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
