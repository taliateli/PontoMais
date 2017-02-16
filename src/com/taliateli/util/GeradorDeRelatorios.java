package com.taliateli.util;

import java.util.Map;

import java.sql.Connection;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class GeradorDeRelatorios {

	public static void gerar(Connection conexao, Map<String, Object> params, String caminhoJasper) throws JRException {

		try {
			JasperPrint print = JasperFillManager.fillReport(caminhoJasper, params, conexao);
			JasperViewer.viewReport(print);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

}