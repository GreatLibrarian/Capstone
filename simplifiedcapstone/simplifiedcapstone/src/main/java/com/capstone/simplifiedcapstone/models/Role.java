package com.capstone.simplifiedcapstone.models;


import javax.persistence.*;
import lombok.*;

@Entity
@Table(name="roles")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class Role {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="role_id")
	private Long id;
	
	@Column(name="user_role",nullable=false)
	@NonNull
	private String role;
	
	public String toString() {
		return role;
	}
}
