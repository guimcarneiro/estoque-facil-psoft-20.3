package com.ufcg.psoft.mercadofacil.utils;

import com.ufcg.psoft.mercadofacil.enums.PerfilUsuarioEnum;
import com.ufcg.psoft.mercadofacil.interfaces.InterfacePerfilUsuario;
import com.ufcg.psoft.mercadofacil.models.PerfilUsuarioEspecial;
import com.ufcg.psoft.mercadofacil.models.PerfilUsuarioNormal;
import com.ufcg.psoft.mercadofacil.models.PerfilUsuarioPremium;

public class PerfilUsuarioUtils {

	public static InterfacePerfilUsuario getPerfilUsuarioByEnum(PerfilUsuarioEnum perfilUsuarioEnum) {
		switch(perfilUsuarioEnum) {
			case ESPECIAL:
				return new PerfilUsuarioEspecial();
			case PREMIUM:
				return new PerfilUsuarioPremium();
			case NORMAL:
				return new PerfilUsuarioNormal();
			default:
				return null;
		}
	}
	
}
