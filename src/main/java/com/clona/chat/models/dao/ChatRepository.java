package com.clona.chat.models.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.clona.chat.models.Mensaje;

public interface ChatRepository extends MongoRepository<Mensaje, String> {
	/*
	 * Si fem servir la nomeclatura de JPA recordem que utilitza els mètodes propis
	 * per retornar directament els 10ultims ordenats per data descendent
	 * Podem verue més info a https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#mongo.query
	 * apartat 9.2 i 9.4.5
	 */
	public List<Mensaje> findFirst10ByOrderByFechaDesc();
}
