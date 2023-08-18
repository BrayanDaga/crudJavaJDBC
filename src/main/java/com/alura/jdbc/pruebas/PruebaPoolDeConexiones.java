package com.alura.jdbc.pruebas;

import java.sql.SQLException;

import com.alura.jdbc.factory.ConnectionFactory;

public class PruebaPoolDeConexiones {
	public static void main(String[] args) throws SQLException {
		ConnectionFactory factory = new ConnectionFactory();
		System.out.println("Cerrando la conexión");
		for (int i = 0 ; i< 20 ; i++) {
			  factory.recuperaConexion();
			System.out.println("Abriendo la conexion numero "+ (i+1));
		}
	}
}
