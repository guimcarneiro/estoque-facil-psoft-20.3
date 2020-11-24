package com.ufcg.psoft.mercadofacil.mappers;

import com.ufcg.psoft.mercadofacil.DTO.CriaUsuarioDTO;
import com.ufcg.psoft.mercadofacil.DTO.EditaUsuarioViaAdminDTO;
import com.ufcg.psoft.mercadofacil.DTO.UsuarioDTO;
import com.ufcg.psoft.mercadofacil.models.Usuario;

public class UsuarioMapper {

	public static Usuario mapCriaUsuarioDTOToUsuario(CriaUsuarioDTO criaUsuarioDTO) {
		Usuario usuario = new Usuario();
		
		usuario.setCpf(criaUsuarioDTO.getCpf());
		usuario.setNome(criaUsuarioDTO.getNome());
		usuario.setEmail(criaUsuarioDTO.getEmail());
		
		return usuario;
	}
	
	public static UsuarioDTO mapUsuarioToUsuarioDTO(Usuario usuario) {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		
		usuarioDTO.setId(usuario.getId());
		usuarioDTO.setCpf(usuario.getCpf());
		usuarioDTO.setNome(usuario.getNome());
		usuarioDTO.setEmail(usuario.getEmail());
		usuarioDTO.setPerfil(usuario.getPerfil().getPerfil());
		
		return usuarioDTO;
	}
	
	
	/**
	 * Preserva cpf e id do Usuario
	 * 
	 * @param usuario
	 * @param euvaDTO
	 */
	public static void mapEditaUsuarioViaAdminDTOToUsuario(Usuario usuario, EditaUsuarioViaAdminDTO euvaDTO) {
		
		usuario.setEmail(euvaDTO.getEmail());
		usuario.setNome(euvaDTO.getNome());
		usuario.setPerfilEnum(euvaDTO.getPerfil());
		
	}
}
