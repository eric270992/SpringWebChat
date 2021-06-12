package com.clona.chat.controllers;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.clona.chat.models.Mensaje;
import com.clona.chat.models.service.ChatService;

/*
 * Controlarà els missatges del Broker (WebSocketConfig)
 */
@Controller
public class ChatController {
	
	private String[] colores = {"red","blue","green","purple","orange"};
	
	//Injectem el ChatService que implementa les funcions d'accés a dades
	@Autowired
	public ChatService chatService;
	
	@Autowired
	private SimpMessagingTemplate webSocket;
	
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
			//Només guardarem els misatges que escriu l'usuari
			//mensaje.setTexto(mensaje.getTexto());
			//Guardem el missatge a MONGO
			chatService.guardar(mensaje).toString();
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
	

	/*
	 * Tots els usuaris que estiguin subscrits al mètode /chat/histoiral rebran les notificacions i per tant
	 * tots els historials dels chats que es conetin, però això ho podema arreglar si fem ús de SimpMessagingTemplate de
	 * webSocket
	 * Per tant aquí no fem servir @SendTo("/chat/historial") siní el webSocket
	 * 
	 * Recordem que l'anotació MessageMapping indica que és un edpoint on rebrem un missatge
	 */
	@MessageMapping("/historial")
	
	public void historial(String clienteId){
		//Fa la mateixa funció que el sendTo, envia un missatge 
		/*
		 * El primer parametre és la ruta a la que ens subscribim i el segon el que enviem al  broker
		 */
		webSocket.convertAndSend("/chat/historial/"+clienteId,chatService.obtenerUltimosDiezMensajes());
	}
}
