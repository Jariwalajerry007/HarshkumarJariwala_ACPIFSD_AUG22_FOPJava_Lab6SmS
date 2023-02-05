package com.studentms.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentms.app.entity.SmsEntity;
import com.studentms.app.repository.SmsRepository;

@Service
public class SmsServiceImpl implements SmsService {

	@Autowired
	private SmsRepository repo;
   
	@Override
	public SmsEntity addStudent(SmsEntity s)
	{
		return repo.save(s); 
	}

	public List<SmsEntity> getAllStudent(String keyword) {
		if (keyword != null) {
			return repo.search(keyword);
		}
		return repo.findAll();
	}

	public SmsEntity getStudentById(Long id) {
		Optional<SmsEntity> s = repo.findById(id);
		if (s.isPresent()) {
			return s.get();
		}
		return null;
	}

	public void deleteStudent(Long id) {
		repo.deleteById(id);

	}

	public void viewStudent(Long id) {
		repo.findById(id);
	}

}


