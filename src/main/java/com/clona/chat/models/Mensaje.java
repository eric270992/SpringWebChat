package com.clona.chat.models;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//Indiquem que serà un document de MongoDB "taula de SQL si fos entity"
@Document(collection="mensajes")
public class Mensaje implements Serializable {

	private static final long serialVersionUID = -6495706577703723485L;
	//Id de cada document, amb MONGO són strings
	@Id
	private String id;
	private String texto;
	private Long fecha;
	private String username;
	private String tipo;
	private String color;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Long getFecha() {
		return fecha;
	}
	public void setFecha(Long fecha) {
		this.fecha = fecha;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@Override
	public String toString() {
		return "Mensaje [id=" + id + ", texto=" + texto + ", fecha=" + fecha + ", username=" + username + ", tipo="
				+ tipo + ", color=" + color + "]";
	}
	 
	
	
	
	

}
