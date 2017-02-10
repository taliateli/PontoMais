package com.taliateli.util;

import java.util.Map;

import com.mysql.jdbc.Connection;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

public class GeradorDeRelatorios {

	public static void gerar(Connection conexao, Map<String, Object> params, String caminhoJasper) throws JRException {

		try {
			JasperPrint print = JasperFillManager.fillReport(caminhoJasper, params, conexao);
			JasperPrintManager.printReport(print, false);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

}