package com.capstone.simplifiedcapstone.models;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.*;

@Entity
@Table(name="events")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class Event {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="event_id")
	private Long id;
	
	@Column(name="event_name",nullable=false)
	@NonNull
	private String name;
	
	@Column(name="date",nullable=false,columnDefinition="DATE")
	@NonNull
	private String date;
	
	@Column(name="time",nullable=false,columnDefinition="TIME")
	@NonNull
	private String time;
	
	@Column(name="location",nullable=false)
	@NonNull
	private String location;
	
	@Column(name="description",nullable=false)
	@NonNull
	private String description;
	
	@ManyToOne(targetEntity=User.class, cascade=CascadeType.ALL, fetch=FetchType.LAZY)
//	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinTable(name="user_events")
	private User user;
	
	@ManyToMany(targetEntity=Type.class, cascade=CascadeType.ALL)
	@JoinTable(name="event_types")
	private List<Type> types;
}
