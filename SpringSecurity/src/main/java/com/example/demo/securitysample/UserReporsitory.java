package com.example.demo.securitysample;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReporsitory extends JpaRepository<AppUser, Integer> {

	AppUser getById(Integer id);

	AppUser findByUsername(String username);

	AppUser findByUserMail(String mail);

	List<AppUser> findByRoleCode(String roleCode);

	List<AppUser> findByRoleId(Integer roleId);

	List<AppUser> findByUnitCodeAndLocationIdAndDepartmentAndRoleCode(String unitCode, String Locationid,
			Integer DeptCode, String Approver);

	List<AppUser> findByUnitCode(String unitCode);

	List<AppUser> findByUnitCodeAndLocationId(String unitCode, String locid);

	List<AppUser> findByUnitCodeAndLocationIdAndDepartment(String unitCode, String locid, Integer deptId);

}
