package com.capstone.simplifiedcapstone.models;


import javax.persistence.*;
import lombok.*;

@Entity
@Table(name="usertypes")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class Type {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="type_id")
	private Long id;
	
	@Column(name="usertype_name",nullable=false)
	@NonNull
	private String typeName;
	
	@Column(name="wheelchair",nullable=false)
	@NonNull
	private Boolean wheelchairBound;
	
	public String toString() {
		return typeName;
	}
}
