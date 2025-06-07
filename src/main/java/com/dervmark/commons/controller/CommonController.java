package com.dervmark.commons.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.dervmark.commons.response.EntityResponse;
import com.dervmark.commons.response.ResponseBuilder;
import com.dervmark.commons.service.CommonService;

public class CommonController <E, S extends CommonService<E>>{
	
	@Autowired
	protected S service;
	
	@GetMapping
	public ResponseEntity<?> findAll(){
        List<E> lista = service.findAll();
        return ResponseBuilder.success("Lista obtenida correctamente", lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EntityResponse<Object>> findById(@PathVariable String id) {
	    Optional<E> optional = service.findById(id);
	    return optional
	            .<ResponseEntity<EntityResponse<Object>>>map(entity -> ResponseBuilder.success("Entidad encontrada", entity))
	            .orElseGet(() -> ResponseBuilder.notFound("No se encontró entidad con id " + id));
	}
		
	@PostMapping("/guardar")
	public ResponseEntity<?> guarda(@RequestBody E entity){
		E saved = service.save(entity);
        return ResponseBuilder.created("Se ha guardado exitosamente", saved);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable String id){
		Optional<E> optional = service.findById(id);
		 
		if (optional.isPresent()) {
			service.deleteById(id);
			// 200 OK indicando éxito de eliminación
			return ResponseBuilder.success("Entidad eliminada con éxito", null);
		} else {
			return ResponseBuilder.notFound("No se encontró entidad con id " + id);
		}
	}
	
}
