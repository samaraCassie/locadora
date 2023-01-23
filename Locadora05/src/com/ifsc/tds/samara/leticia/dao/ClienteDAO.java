package com.ifsc.tds.samara.leticia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ifsc.tds.samara.leticia.entity.Cliente;

public class ClienteDAO implements DAO<Cliente>{

	@Override
	public Cliente get(Long id) {
		Cliente cliente = null;
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
				cliente = new Cliente();

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
	public List<Cliente> getAll() {
		List<Cliente> clientes = new ArrayList<Cliente>();
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
				Cliente cliente = new Cliente();

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
	public int save(Cliente cliente) {
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
	public boolean update(Cliente cliente, String[] params) {
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
	public boolean delete(Cliente cliente) {
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
