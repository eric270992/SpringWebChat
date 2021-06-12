package com.clona.chat;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/*
 * Broker o servidor que s'encarregarà de gestionar els missatges
 */
@Configuration
//Habilita el websocket en spring també implmentem l'interface
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	//Fem override de dos mètodes
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		//Agreguem un endpoint que serà la metixa ruta que farem servir per conectar desde angular
		registry.addEndpoint("/chat-websocket")
		//Acceptem les peticions i accés des de angular
		.setAllowedOrigins("http://localhost:4200")
		//Llibreria que farà servir
		.withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		/*
		 * Afegim un prefixe que es posarà a tots els events que succeixin
		 * tant als enviaments com rebuda etc.. /chat/mensaje doncs és el /chat/
		 */
		registry.enableSimpleBroker("/chat/");
		/*
		 * Desti de quant publiquem un missatge, per tant sempre que volguem una ruta de desti anirà
		 * amb el prefixe /app
		 */
		registry.setApplicationDestinationPrefixes("/app");
	}

	
}
