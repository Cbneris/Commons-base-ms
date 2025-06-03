package com.dervmark.commons.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dervmark.commons.service.CommonService;

public class CommonServiceImpl<E, R extends JpaRepository<E, String>> implements CommonService<E> {

	@Autowired
	protected R repository;
	
	@Override
	public List<E> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<E> findById(String id) {
		return repository.findById(id);
	}

	@Override
	public E save(E entity) {
		return repository.save(entity);
	}

	@Override
	public void deleteById(String id) {
		repository.deleteById(id);
	}

}
