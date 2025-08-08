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
@Table(name = "users")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "username")
	private String username;

	@Column(name = "user_mail")
	private String userMail;

	@Column(name = "password")
	private String password;

	@Column(name = "department")
	private Integer department;

	@Column(name = "department_code")
	private String departmentCode;
	@Column(name = "code")
	private String code;

	@Column(name = "report_to")
	private Integer reportTo;

	@Column(name = "second_approval")
	private Integer secondApproval;

	@Column(name = "enabled")
	private Integer enabled;

	@CreationTimestamp
	@Column(name = "created_on")
	private Timestamp createdOn;

	@CreationTimestamp
	@Column(name = "updated_on")
	private Timestamp updatedOn;

	@Column(name = "role_id")
	private Integer roleId;

	@Column(name = "role_code")
	private String roleCode;

	@Column(name = "employee_code")
	private String employeeCode;

	@Column(name = "location_id")
	private String locationId;

	@Column(name = "location_code")
	private String locationCode;

	@Column(name = "toclass")
	private String toclass;

	@Column(name = "toclass_id")
	private Integer toclassId;

	@Column(name = "business")
	private String business;

	@Column(name = "business_id")
	private Integer businessId;

	@Column(name = "unit")
	private String unit;

	@Column(name = "unit_code")
	private String unitCode;

	public AppUser(Integer userId, String userName, String encryptedPassword) {
		this.id = userId;
		this.username = userName;
		this.password = encryptedPassword;
	}
}