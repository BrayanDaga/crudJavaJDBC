package com.alura.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.alura.jdbc.modelos.Producto;

public class ProductoDAO {

	private Connection con;

	public ProductoDAO(Connection con) {
		this.con = con;
	}

	public void guardar(Producto producto) {
		try {
			PreparedStatement statement;
			statement = con.prepareStatement(
					"INSERT INTO PRODUCTO " + "(nombre, descripcion, cantidad, categoria_id)" + " VALUES (?, ?, ?, ? )",
					Statement.RETURN_GENERATED_KEYS);

			try (statement) {
				statement.setString(1, producto.getNombre());
				statement.setString(2, producto.getDescripcion());
				statement.setInt(3, producto.getCantidad());
				statement.setInt(4, producto.getCategoriaId());
				statement.execute();

				final ResultSet resultSet = statement.getGeneratedKeys();

				try (resultSet) {
					while (resultSet.next()) {
						producto.setId(resultSet.getInt(1));

						System.out.println(String.format("Fue insertado el producto: %s", producto));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Producto> listar() {
		List<Producto> resultado = new ArrayList<>();

		try {
			var mySql = "SELECT P.ID , P.NOMBRE, P.DESCRIPCION, P.CANTIDAD, C.NOMBRE  FROM PRODUCTO P LEFT  JOIN CATEGORIA C ON P.CATEGORIA_ID = C.ID";
			var querySelect = "SELECT ID, NOMBRE, DESCRIPCION, CANTIDAD , CATEGORIA_ID FROM PRODUCTO";
			System.out.println(mySql);
			final PreparedStatement statement = con.prepareStatement(mySql);

			try (statement) {
				statement.execute();

				final ResultSet resultSet = statement.getResultSet();

				try (resultSet) {
					while (resultSet.next()) {
						resultado.add(new Producto(
								resultSet.getInt("P.ID"),
								resultSet.getString("P.NOMBRE"),
								resultSet.getString("P.DESCRIPCION"),
								resultSet.getInt("P.CANTIDAD"),
								resultSet.getString("C.NOMBRE")));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return resultado;
	}

	public int eliminar(Integer id) {
		try {
			final PreparedStatement statement = con.prepareStatement("DELETE FROM PRODUCTO WHERE ID = ?");

			try (statement) {
				statement.setInt(1, id);
				statement.execute();

				int updateCount = statement.getUpdateCount();

				return updateCount;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int modificar(String nombre, String descripcion, Integer cantidad, Integer id) {
		try {
			final PreparedStatement statement = con.prepareStatement(
					"UPDATE PRODUCTO SET " + " NOMBRE = ?, " + " DESCRIPCION = ?," + " CANTIDAD = ?" + " WHERE ID = ?");

			try (statement) {
				statement.setString(1, nombre);
				statement.setString(2, descripcion);
				statement.setInt(3, cantidad);
				statement.setInt(4, id);
				statement.execute();

				int updateCount = statement.getUpdateCount();

				return updateCount;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Producto> listar(Integer categoria_id) {
		List<Producto> resultado = new ArrayList<>();

		try {
			var querySelect = "SELECT ID, NOMBRE, DESCRIPCION, CANTIDAD FROM PRODUCTO WHERE CATEGORIA_ID = ?";
			System.out.println(querySelect);
			final PreparedStatement statement = con.prepareStatement(querySelect);

			try (statement) {
				statement.setInt(1, categoria_id);
				statement.execute();

				final ResultSet resultSet = statement.getResultSet();

				try (resultSet) {
					while (resultSet.next()) {
						resultado.add(new Producto(resultSet.getInt("ID"), resultSet.getString("NOMBRE"),
								resultSet.getString("DESCRIPCION"), resultSet.getInt("CANTIDAD")));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return resultado;
	}

}