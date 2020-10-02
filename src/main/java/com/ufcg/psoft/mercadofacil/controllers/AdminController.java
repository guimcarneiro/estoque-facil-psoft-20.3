package com.ufcg.psoft.mercadofacil.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.psoft.mercadofacil.DTO.EditaUsuarioViaAdminDTO;
import com.ufcg.psoft.mercadofacil.services.UsuarioService;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PutMapping("/usuario/{usuarioId}")
	public ResponseEntity<?> editarUsuarioViaAdmin(@RequestBody EditaUsuarioViaAdminDTO usuario, @PathVariable Long usuarioId){
		return this.usuarioService.editarUsuarioViaAdmin(usuario, usuarioId);
	}
	
}
