package com.ifsc.tds.Samara.Leticia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ifsc.tds.Samara.Leticia.entity.Clientes;

public class ClienteDAO implements DAO<Clientes>{

	@Override
	public Clientes get(Long id) {
		Clientes cliente = null;
		String sql = "select * from contato where id = ?";

		// Recupera a conexão com BD
		Connection conexao = null;

		// CRiar preaparação de consulta
		PreparedStatement stm = null;

		// Guarda retorno da operação
		ResultSet rset = null;

		try {
			conexao = new Conexao().getConnection();

			stm = conexao.prepareStatement(sql);
			stm.setInt(1, id.intValue());
			rset = stm.executeQuery();

			while (rset.next()) {
				cliente = new Clientes();

				// De campo para atributo
				cliente.setId(rset.getLong("id"));
				cliente.setNome(rset.getString("nome"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stm != null)
					stm.close();
				if (conexao != null)
					conexao.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return cliente;
	}

	@Override
	public List<Clientes> getAll() {
		List<Clientes> clientes = new ArrayList<Clientes>();
		String sql = "select * from contato";

		// Recupera a conexão com BD
		Connection conexao = null;

		// CRiar preaparação de consulta
		PreparedStatement stm = null;

		// Guarda retorno da operação
		ResultSet rset = null;

		try {
			conexao = new Conexao().getConnection();

			stm = conexao.prepareStatement(sql);
			rset = stm.executeQuery();

			while (rset.next()) {
				Clientes cliente = new Clientes();

				// De campo para atributo
				cliente.setId(rset.getLong("id"));
				cliente.setNome(rset.getString("nome"));

				clientes.add(cliente);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stm != null)
					stm.close();
				if (conexao != null)
					conexao.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return clientes;
	}

	@Override
	public int save(Clientes cliente) {
		String sql = "insert into contato (nome)" + "values (?)";

		// Recupera a conexão com BD
		Connection conexao = null;

		// CRiar preaparação de consulta
		PreparedStatement stm = null;

		try {
			conexao = new Conexao().getConnection();

			stm = conexao.prepareStatement(sql);
			stm.setString(1, cliente.getNome());

			stm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stm != null)
					stm.close();
				if (conexao != null)
					conexao.close();
				return 1;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public boolean update(Clientes cliente, String[] params) {
		String sql = "update contato set nome = ? where id = ?";

		// Recupera a conexão com BD
		Connection conexao = null;

		// CRiar preaparação de consulta
		PreparedStatement stm = null;

		try {
			conexao = new Conexao().getConnection();

			stm = conexao.prepareStatement(sql);
			stm.setString(1, cliente.getNome());
			stm.setLong(2, cliente.getId());

			stm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stm != null)
					stm.close();
				if (conexao != null)
					conexao.close();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean delete(Clientes cliente) {
		String sql = "delete from contato where id = ?";
		
		//Recupera a conexão com BD
		Connection conexao = null;
		
		//CRiar preaparação de consulta
		PreparedStatement stm = null;
		
		try {
			conexao = new Conexao().getConnection();
			
			stm = conexao.prepareStatement(sql);
			stm.setLong(1, cliente.getId());
			
			stm.execute();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(stm != null)stm.close();
				if(conexao != null)conexao.close();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
