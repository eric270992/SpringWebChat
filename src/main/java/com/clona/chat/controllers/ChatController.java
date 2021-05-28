package com.clona.chat.controllers;

import java.util.Date;
import java.util.Random;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.clona.chat.models.Mensaje;

/*
 * Controlarà els missatges del Broker (WebSocketConfig)
 */
@Controller
public class ChatController {
	
	private String[] colores = {"red","blue","green","purple","orange"};
	
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
		if(mensaje.getTipo().equals("NUEVO_USUARIO")) {
			mensaje.setColor(colores[new Random().nextInt(colores.length)]);
			mensaje.setTexto("Nuevo usuario: "+mensaje.getUsername());
		}else {
			mensaje.setTexto(mensaje.getTexto());
		}

		//Retornem el missatge que serà rebut per tothom del que estigui subscrit
		//al clientStomp de angular.
		return mensaje;
	}
	
	@MessageMapping("/escribiendo")
	@SendTo("/chat/escribiendo")
	public String estaEscribiendo(String username) {
		return username.concat(" Esta escribiendo...");
	}
}
