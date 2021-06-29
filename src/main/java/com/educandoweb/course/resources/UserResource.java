package com.educandoweb.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		
		List<User> list = service.findAll();  
				
		return ResponseEntity.ok().body(list);
		
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){ //Faz com que o Long id seja reconhecido como uma variável da url
																
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj); 
		
	}
	
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj){ //Para dizer que o obj vai chegar em JSON quando for fazer a requisição
															 // e vai ser desserializado para um objeto User
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri(); 
		//O padrão http quando você retorna um 201 é esperado que a resposta contenha um cabeçalho "location" contendo o endereço do novo recurso
		
		return ResponseEntity.created(uri).body(obj); //O created espera um objeto URI
		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
	
		service.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> Update(@PathVariable Long id, @RequestBody User obj){
		
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
		
	}
	
}
