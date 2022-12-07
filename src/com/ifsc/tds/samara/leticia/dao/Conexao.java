package com.ifsc.tds.samara.leticia.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private static  final String LOGIN_BANCO = "root";
	private static  final String SENHA_BANCO = "";
	private static  final String URL_BANCO = "jdbc:mysql://localhost:3306/DB_Agenda?autoReconnect=true&useSSL=false";
	
	public Connection getConnection() {
		Connection conexao = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexao = DriverManager.getConnection(Conexao.URL_BANCO, Conexao.LOGIN_BANCO, Conexao.SENHA_BANCO);
		} catch (SQLException e) {
			System.out.println("Erro ao tentar conectar com o banco de dados. ERRO: " + e);
		} catch (ClassNotFoundException e) {
			System.out.println("Não foi possivel carregar a classe JDBC MySQL. ERRO " + e);
		} catch (Exception e) {
			System.out.println("Erro geral. ERRO: " + e);
		}
		return conexao;
	}
}
