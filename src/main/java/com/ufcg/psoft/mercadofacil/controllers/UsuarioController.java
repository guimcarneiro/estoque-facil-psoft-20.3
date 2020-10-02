package com.ufcg.psoft.mercadofacil.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.psoft.mercadofacil.DTO.CriaUsuarioDTO;
import com.ufcg.psoft.mercadofacil.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping
	public ResponseEntity<?> criarUsuario(@RequestBody CriaUsuarioDTO criaUsuarioDTO) {
		return this.usuarioService.criarUsuario(criaUsuarioDTO);
	}
	
	@PutMapping
	public ResponseEntity<?> editarUsuario(@RequestBody CriaUsuarioDTO criaUsuarioDTO){
		return this.usuarioService.editarUsuario(criaUsuarioDTO);
	}
	
	@GetMapping
	public ResponseEntity<?> listarUsuarios() {
		return this.usuarioService.listarUsuarios();
	}
	
}
