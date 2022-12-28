package com.ecommerce.project.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.project.dto.CategoryDTO;
import com.ecommerce.project.entities.Category;
import com.ecommerce.project.repositories.CategoryRepository;
import com.ecommerce.project.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	@Transactional(readOnly = true)
	public Page<CategoryDTO> findAllPaged(PageRequest pageRequest){
		Page<Category> list = repository.findAll(pageRequest);	
		return list.map(x -> new CategoryDTO(x));	
	}
	
	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new CategoryDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public CategoryDTO insert(CategoryDTO obj) {
		Category entity = new Category();
		entity.setName(obj.getName());
		entity = repository.save(entity);
		
		return new CategoryDTO(entity);
	}

	@Transactional
	public CategoryDTO update(Long id, CategoryDTO obj) {
		try {
			Category entity = repository.getReferenceById(id);
			entity.setName(obj.getName());
			entity = repository.save(entity);
			
			return new CategoryDTO(entity);
		}
		catch(EntityNotFoundException e){
			throw new ResourceNotFoundException("Id " + id + " not found");
		}
			
	}
	
}
