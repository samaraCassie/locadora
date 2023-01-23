package com.ifsc.tds.samara.leticia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ifsc.tds.samara.leticia.entity.Filme;

public class FilmeDAO implements DAO<Filme>{
	
	@Override
	public Filme get(Long id) {
		Filme filme = null;
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
				filme = new Filme();

				// De campo para atributo
				filme.setId(rset.getLong("id"));
				filme.setNome(rset.getString("nome"));
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
		return filme;
	}

	@Override
	public List<Filme> getAll() {
		List<Filme> filmes = new ArrayList<Filme>();
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
				Filme filme = new Filme();

				// De campo para atributo
				filme.setId(rset.getLong("id"));
				filme.setNome(rset.getString("nome"));

				filmes.add(filme);
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
		return filmes;
	}

	@Override
	public int save(Filme filme) {
		String sql = "insert into contato (nome)" + "values (?)";

		// Recupera a conexão com BD
		Connection conexao = null;

		// CRiar preaparação de consulta
		PreparedStatement stm = null;

		try {
			conexao = new Conexao().getConnection();

			stm = conexao.prepareStatement(sql);
			stm.setString(1, filme.getNome());

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
	public boolean update(Filme filme, String[] params) {
		String sql = "update contato set nome = ? where id = ?";

		// Recupera a conexão com BD
		Connection conexao = null;

		// CRiar preaparação de consulta
		PreparedStatement stm = null;

		try {
			conexao = new Conexao().getConnection();

			stm = conexao.prepareStatement(sql);
			stm.setString(1, filme.getNome());
			stm.setLong(2, filme.getId());

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
	public boolean delete(Filme filme) {
		String sql = "delete from contato where id = ?";
		
		//Recupera a conexão com BD
		Connection conexao = null;
		
		//CRiar preaparação de consulta
		PreparedStatement stm = null;
		
		try {
			conexao = new Conexao().getConnection();
			
			stm = conexao.prepareStatement(sql);
			stm.setLong(1, filme.getId());
			
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
