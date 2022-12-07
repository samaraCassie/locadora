package com.ifsc.tds.samara.leticia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ifsc.tds.samara.leticia.entity.Locacao;


public class LocacaoDAO implements DAO<Locacao> {
	@Override
	public Locacao get(Long id) {
		Locacao locacao = null;
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
				locacao = new Locacao();

				// De campo para atributo
				locacao.setId(rset.getLong("id"));
				locacao.setNomeFilme(rset.getString("nome"));
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
		return locacao;
	}

	@Override
	public List<Locacao> getAll() {
		List<Locacao> locacoes = new ArrayList<Locacao>();
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
				Locacao locacao = new Locacao();

				// De campo para atributo
				locacao.setId(rset.getLong("id"));
				locacao.setNomeFilme(rset.getString("nome"));

				locacoes.add(locacao);
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
		return locacoes;
	}

	@Override
	public int save(Locacao locacao) {
		String sql = "insert into contato (nome)" + "values (?)";

		// Recupera a conexão com BD
		Connection conexao = null;

		// CRiar preaparação de consulta
		PreparedStatement stm = null;

		try {
			conexao = new Conexao().getConnection();

			stm = conexao.prepareStatement(sql);
			stm.setString(1, locacao.getObservacao());
			stm.setString(2, locacao.getNomeFilme());
			stm.setDate(3, locacao.getDataLocacao());

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
	public boolean update(Locacao locacao, String[] params) {
		String sql = "update contato set nome = ? where id = ?";

		// Recupera a conexão com BD
		Connection conexao = null;

		// CRiar preaparação de consulta
		PreparedStatement stm = null;

		try {
			conexao = new Conexao().getConnection();

			stm = conexao.prepareStatement(sql);
			stm.setString(1, locacao.getObservacao());
			stm.setString(2, locacao.getNomeFilme());
			stm.setDate(3, locacao.getDataLocacao());
			stm.setLong(4, locacao.getId());

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
	public boolean delete(Locacao locacao) {
		String sql = "delete from contato where id = ?";
		
		//Recupera a conexão com BD
		Connection conexao = null;
		
		//CRiar preaparação de consulta
		PreparedStatement stm = null;
		
		try {
			conexao = new Conexao().getConnection();
			
			stm = conexao.prepareStatement(sql);
			stm.setLong(1, locacao.getId());
			
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