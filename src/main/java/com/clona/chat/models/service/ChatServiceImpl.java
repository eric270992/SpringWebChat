package com.clona.chat.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clona.chat.models.Mensaje;
import com.clona.chat.models.dao.ChatRepository;

@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	private ChatRepository chatRepository;
	
	@Override
	public List<Mensaje> obtenerUltimosDiezMensajes() {
		return chatRepository.findFirst10ByOrderByFechaDesc();
	}

	@Override
	public Mensaje guardar(Mensaje mensaje) {
		return chatRepository.save(mensaje);
	}
	
}
