package com.clona.chat.controllers;

import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.clona.chat.models.Mensaje;

/*
 * Controlarà els missatges del Broker (WebSocketConfig)
 */
@Controller
public class ChatController {
	
	/*
	 * No cal indicar el prefixe que tenim configurat WebSocketConfig
	 * configureMessageBroker -> ApplicationDestinationPrefixes
	 * També indiquem que el return ho enviarà cap al broker, aquest cop 
	 * si que tot el que té a veure amb enviaments al Broker necessitem posar el prefixe
	 */
	@MessageMapping("/mensaje")
	@SendTo("/chat/mensaje")
	public Mensaje recibeMensaje(Mensaje mensaje) {
		//Podem fer quelcom amb el missatge que rebem
		mensaje.setFecha(new Date().getTime());
		mensaje.setTexto("Recibido por el broker: "+mensaje.getTexto());
		
		return mensaje;
	}
}
