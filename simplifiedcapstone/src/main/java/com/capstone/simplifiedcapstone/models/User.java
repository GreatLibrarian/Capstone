package com.capstone.simplifiedcapstone.models;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.*;

@Entity
@Table(name="users")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Long id;
	
	@Column(name="email",nullable=false,unique=true)
	@NonNull
	private String email;
	
	@Column(name="password",nullable=false)
	@NonNull
	private String password;
	
	@Column(name="first_name",nullable=false)
	@NonNull
	private String firstName;
	
	@Column(name="last_name",nullable=false)
	@NonNull
	private String lastName;
	
	@ManyToMany(targetEntity=Role.class, cascade=CascadeType.ALL)
	@JoinTable(name="user_roles")
	@JoinColumn(name="role_id")
	private List<Role> roles;
	
	@ManyToMany(targetEntity=Type.class, cascade=CascadeType.ALL)
	@JoinTable(name="user_types")
	private List<Type> types;
	
	@ManyToMany(targetEntity=Event.class, cascade=CascadeType.ALL)
	@JoinTable(name="user_followed_events")
	private List<Event> followedEvents;
	
//	@OneToMany(targetEntity=Event.class, cascade=CascadeType.ALL)
//	@OnDelete(action=OnDeleteAction.CASCADE)
//	@JoinTable(name="user_events")
//	@JoinColumn(name="event_id")
//	private List<Event> events;
//	
	public void addRoles(List<Role> role) {
		roles.addAll(role);
	}
	
	public void addTypes(List<Type> type) {
		types.addAll(type);
	}
	
	public String getFullName() {
		return firstName + " " + lastName;
	}
}
