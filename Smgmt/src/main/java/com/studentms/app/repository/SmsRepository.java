package com.studentms.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.studentms.app.entity.SmsEntity;



public interface SmsRepository extends JpaRepository<SmsEntity, Long> {
	@Query("SELECT s FROM SmsEntity s WHERE s.firstName LIKE %?1%" + " OR s.lastName LIKE %?1%"
			+ " OR s.course LIKE %?1%"+ " OR s.country LIKE %?1%")
	public List<SmsEntity> search(String keyword);

}
