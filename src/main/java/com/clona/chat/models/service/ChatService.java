package com.clona.chat.models.service;

import java.util.List;

import com.clona.chat.models.Mensaje;

public interface ChatService {
	public List<Mensaje> obtenerUltimosDiezMensajes();
	public Mensaje guardar(Mensaje mensaje);
}
