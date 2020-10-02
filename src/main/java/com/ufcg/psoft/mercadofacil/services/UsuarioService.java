package com.ufcg.psoft.mercadofacil.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.mercadofacil.DTO.CriaUsuarioDTO;
import com.ufcg.psoft.mercadofacil.DTO.EditaUsuarioViaAdminDTO;
import com.ufcg.psoft.mercadofacil.DTO.UsuarioDTO;
import com.ufcg.psoft.mercadofacil.mappers.UsuarioMapper;
import com.ufcg.psoft.mercadofacil.models.Usuario;
import com.ufcg.psoft.mercadofacil.repositories.UsuarioRepository;
import com.ufcg.psoft.mercadofacil.utils.CustomErrorType;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public ResponseEntity<?> criarUsuario(CriaUsuarioDTO usuario) {
		Usuario usuarioNovo = UsuarioMapper.mapCriaUsuarioDTOToUsuario(usuario);
		
		if(usuarioRepository.existsByCpf(usuario.getCpf())) {
			return new ResponseEntity<CustomErrorType>(new CustomErrorType("Usuário já cadastrado"), HttpStatus.CONFLICT);
		}
				
		Usuario usuarioSalvo = this.usuarioRepository.save(usuarioNovo);
		
		UsuarioDTO usuarioSalvoDTO = UsuarioMapper.mapUsuarioToUsuarioDTO(usuarioSalvo);
		
		return new ResponseEntity<UsuarioDTO>(usuarioSalvoDTO, HttpStatus.CREATED);
	}
	
	public ResponseEntity<?> editarUsuario(CriaUsuarioDTO usuario) {
		Usuario usuarioDb = this.usuarioRepository.findByCpf(usuario.getCpf());
		
		if(usuarioDb == null) {
			return new ResponseEntity<CustomErrorType>(new CustomErrorType("Usuário inexistente"), HttpStatus.NOT_FOUND);
		}
		
		usuarioDb.setEmail(usuario.getEmail());
		usuarioDb.setNome(usuario.getNome());
		
		Usuario usuarioAtualizado = this.usuarioRepository.save(usuarioDb);
		UsuarioDTO usuarioAtualizadoDTO = UsuarioMapper.mapUsuarioToUsuarioDTO(usuarioAtualizado);
		
		return new ResponseEntity<UsuarioDTO>(usuarioAtualizadoDTO, HttpStatus.NO_CONTENT);
	}
	
	public ResponseEntity<?> editarUsuarioViaAdmin(EditaUsuarioViaAdminDTO editaUsuarioViaAdminDTO, Long usuarioId) {
		Usuario usuarioDb = this.usuarioRepository.findByCpf(editaUsuarioViaAdminDTO.getCpf());
		
		if(usuarioDb == null) {
			return new ResponseEntity<CustomErrorType>(new CustomErrorType("Usuário inexistente"), HttpStatus.NOT_FOUND);
		}
		
		if(!usuarioDb.getId().equals(usuarioId)) {
			return new ResponseEntity<CustomErrorType>(new CustomErrorType("IDs do path e do body conflitantes"), HttpStatus.CONFLICT);
		}
		
		UsuarioMapper.mapEditaUsuarioViaAdminDTOToUsuario(usuarioDb, editaUsuarioViaAdminDTO);

		Usuario usuarioAtualizado = this.usuarioRepository.save(usuarioDb);
		UsuarioDTO usuarioAtualizadoDTO = UsuarioMapper.mapUsuarioToUsuarioDTO(usuarioAtualizado);
		
		return new ResponseEntity<UsuarioDTO>(usuarioAtualizadoDTO, HttpStatus.NO_CONTENT);
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
