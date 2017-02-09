package com.taliateli.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class MensagensDialog {

	public static Alert getAlertError(String titulo, String msg) {
		Alert dlgError = new Alert(Alert.AlertType.ERROR);
		dlgError.setTitle(titulo);
		dlgError.setContentText(msg);
		dlgError.showAndWait();
		return dlgError;
	}

	public static Alert getAlertError(String erro) {
		Alert dlgError = new Alert(Alert.AlertType.ERROR);
		dlgError.setContentText(erro);
		dlgError.showAndWait();
		return dlgError;
	}

	public static Alert getAlertInformation(String titulo, String msg) {
		Alert dlgInf = new Alert(Alert.AlertType.INFORMATION);
		dlgInf.setTitle(titulo);
		dlgInf.setContentText(msg);
		dlgInf.showAndWait();
		return dlgInf;
	}

	public static Alert getAlertWarning(String titulo, String msg) {
		Alert dlgWarning = new Alert(Alert.AlertType.WARNING);
		dlgWarning.setTitle(titulo);
		dlgWarning.setContentText(msg);
		dlgWarning.showAndWait();
		return dlgWarning;
	}

	public static Alert getAlertConfirmation(String titulo, String msg) {
		Alert dlgConf = new Alert(Alert.AlertType.CONFIRMATION);
		Button btnSim = new Button("Sim");
		Button btnNao = new Button("Não");
		dlgConf.setTitle(titulo);
		dlgConf.setContentText(msg);
		dlgConf.showAndWait();
		return dlgConf;
	}

}
