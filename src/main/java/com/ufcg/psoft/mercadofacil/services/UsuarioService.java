package com.ufcg.psoft.mercadofacil.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.mercadofacil.DTO.CriaUsuarioDTO;
import com.ufcg.psoft.mercadofacil.DTO.UsuarioDTO;
import com.ufcg.psoft.mercadofacil.mappers.UsuarioMapper;
import com.ufcg.psoft.mercadofacil.models.Usuario;
import com.ufcg.psoft.mercadofacil.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public ResponseEntity<?> criarUsuario(CriaUsuarioDTO usuario) {
		Usuario usuarioNovo = UsuarioMapper.mapCriaUsuarioDTOToUsuario(usuario);
		
		Usuario usuarioSalvo = this.usuarioRepository.save(usuarioNovo);
		
		UsuarioDTO usuarioSalvoDTO = UsuarioMapper.mapUsuarioToUsuarioDTO(usuarioSalvo);
		
		return new ResponseEntity<UsuarioDTO>(usuarioSalvoDTO, HttpStatus.CREATED);
	}
	
	public ResponseEntity<?> listarUsuarios() {
		
		List<Usuario> usuarios = this.usuarioRepository.findAll();
		
		List<UsuarioDTO> usuariosDTO = new ArrayList<UsuarioDTO>();
		for (Usuario usuario : usuarios) {
			UsuarioDTO userDTO = UsuarioMapper.mapUsuarioToUsuarioDTO(usuario);
			usuariosDTO.add(userDTO);
		}
		
		return new ResponseEntity<List<UsuarioDTO>>(usuariosDTO, HttpStatus.OK);
	}
	
}
