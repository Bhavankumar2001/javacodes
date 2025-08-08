package com.example.demo.securitysample;

import java.sql.Timestamp;
//or better for future use:
import java.time.LocalDateTime;


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
@Table(name = "user_log")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "request_method")
	private String requestMethod;

	@Column(name = "status")
	private String status;
	@Column(name = "endpoint")
	private String endpoint;

	@Column(name = "request_body")
	private String requestBody;

	@Column(name = "response_body")
	private String responseBody;

	@Column(name = "remark")
	private String remark;

	@Column(name = "user")
	private String user;

	@Column(name = "ip")
	private String ip;

//	@CreationTimestamp
//	@Column(name = "created_on")
//	private Timestamp createdOn;
//
//	@CreationTimestamp
//	@Column(name = "updated_on")
//	private Timestamp updatedOn;
	
	@Column(name = "created_on", updatable = false)
	@CreationTimestamp
	private Timestamp createdOn;

	@Column(name = "updated_on")
	@CreationTimestamp
	private Timestamp updatedOn;

}
