package com.taliateli.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.taliateli.model.Colaborador;
import com.taliateli.util.Util;

public class ColaboradorDao {

	public void salvar(Colaborador col) throws Exception {
		String sql = "INSERT INTO colaborador(nome,funcao,dtInclusao)" + " VALUES(?,?,?)";
		Connection conn = null;
		PreparedStatement pstm = null;
		conn = Conexao.getConexao();
		pstm = conn.prepareStatement(sql);
		pstm.setString(1, col.getNome());
		pstm.setString(2, col.getFuncao());
		pstm.setString(3, Util.getDataFormatada(col.getDtInclusao(), "yyyy/MM/dd HH:mm:ss"));
		pstm.execute();
		if (pstm != null) {
			pstm.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	public List<Colaborador> listarColaboradores() throws Exception {
		String sql = "SELECT * FROM colaborador WHERE dtExclusao is NULL";
		List<Colaborador> colaboradores = new ArrayList<Colaborador>();
		Connection conn = null;
		PreparedStatement pstm = null;
		// Classe que vai recuperar os dados do banco de dados
		ResultSet rset = null;
		conn = Conexao.getConexao();
		pstm = conn.prepareStatement(sql);
		rset = pstm.executeQuery();
		// Enquanto existir dados no banco de dados, faça
		while (rset.next()) {
			Colaborador colaborador = new Colaborador();
			colaborador.setId(rset.getInt("id"));
			colaborador.setNome(rset.getString("nome"));
			colaborador.setFuncao(rset.getString("funcao"));
			colaborador.setDtInclusao(rset.getDate("dtInclusao"));
			colaboradores.add(colaborador);
		}
		if (rset != null) {
			rset.close();
		}
		if (pstm != null) {
			pstm.close();
		}
		if (conn != null) {
			conn.close();
		}
		return colaboradores;
	}

	// Alterar este método para UPDATE dtExclusao e criar as colunas de data
	// inclusao e exclusao
	public void excluir(Colaborador col) throws Exception {
		String sql = "UPDATE colaborador set dtExclusao = ? WHERE id = ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		conn = Conexao.getConexao();
		pstm = conn.prepareStatement(sql);
		pstm.setString(1, Util.getDataFormatada(col.getDtExclusao(), "yyyy/MM/dd HH:mm:ss"));
		pstm.setInt(2, col.getId());
		pstm.execute();
		if (pstm != null) {
			pstm.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	public Colaborador consultar(Integer id) throws Exception {
		Colaborador col = new Colaborador();
		String sql = "SELECT * FROM colaborador WHERE id = ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		conn = Conexao.getConexao();
		pstm = conn.prepareStatement(sql);
		pstm.setInt(1, id);
		rset = pstm.executeQuery();
		while (rset.next()) {
			col.setId(rset.getInt("id"));
			col.setNome(rset.getString("nome"));
			col.setFuncao(rset.getString("funcao"));
			col.setDtInclusao(rset.getDate("dtInclusao"));
			col.setDtExclusao(rset.getDate("dtExclusao"));
		}
		if (pstm != null) {
			pstm.close();
		}
		if (conn != null) {
			conn.close();
		}
		return col;
	}
}
