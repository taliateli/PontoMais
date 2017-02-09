package com.taliateli.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.taliateli.model.Colaborador;
import com.taliateli.model.Ponto;
import com.taliateli.util.Util;

public class PontoDao {

	public void salvar(String tb, Colaborador col, Ponto p) throws Exception {
		String sql = "INSERT INTO " + tb + "(colaborador,dataHora)" + " VALUES(?,?)";
		Connection conn = null;
		PreparedStatement pstm = null;
		conn = Conexao.getConexao();
		pstm = conn.prepareStatement(sql);
		pstm.setInt(1, p.getColaborador().getId());
		pstm.setString(2, Util.getDataFormatada(p.getDataHora(), "yyyy/MM/dd HH:mm:ss"));
		pstm.execute();
		if (pstm != null) {
			pstm.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

}
