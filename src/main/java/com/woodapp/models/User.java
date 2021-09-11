package com.woodapp.models;

import com.woodapp.payload.request.RegisterRequest;
import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name="users", uniqueConstraints = { @UniqueConstraint(columnNames = "email")})
public class User  {

	@Id
	@Column(name = "user_id", updatable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@Column(nullable=false)
	private String firstName;

	@NotNull
	@Column(nullable=false)
	private String lastName;

	@NotNull
	@Email
	@Column(nullable=false, unique = true)
	public String email;

	@NotNull
	@Size(max=20)
	@Column(nullable=false, unique=true)
	private String password;

	private String gender;

//	(we'll need a placeholder like MM/DD/YY on the form)
	private String birthday;

	@Column(nullable = false)
	private Integer phoneNumber;

	@Column(nullable=false)
    private String streetAddress;

	private String city;
	private String state;
	private Integer zipCode;
	private LocalDate signUpDate = LocalDate.now();

	//will need to be a drop-down menu on the form
	private String membershipType;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "gym_id")
	private Gym gym;
	//This is to map the USERS table and the GYM_INFO table using their ids**

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "user_id")
//	private Role roles;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();


	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	public User() {
	}

	public User(String firstName, String lastName, String email, String password, String gender, String birthday,
				Integer phoneNumber, String streetAddress, String city, String state, Integer zipCode, LocalDate signUpDate, String membershipType) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.birthday = birthday;
		this.phoneNumber = phoneNumber;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.signUpDate = signUpDate;
		this.membershipType = membershipType;
	}

	public User(String email, String encode, String birthday, RegisterRequest registerRequest, String gender, String streetAddress, String state, Integer zipCode) {
	}

	@Bean(name="entityManagerFactory")
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

		return sessionFactory;
	}

}