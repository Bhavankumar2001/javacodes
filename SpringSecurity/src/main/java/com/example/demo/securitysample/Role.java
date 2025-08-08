package com.example.demo.securitysample;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "role")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "role")
	private String role;

	@Column(name = "role_code")
	private String roleCode;

	@Column(name = "remark")
	private String remark;

	@Column(name = "enabled")
	private Integer enabled;

	@CreationTimestamp
	@Column(name = "created_on")
	private Timestamp createdOn;

	@CreationTimestamp
	@Column(name = "updated_on")
	private Timestamp updatedOn;
}
