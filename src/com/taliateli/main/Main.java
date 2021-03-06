package com.taliateli.main;

import java.io.IOException;
import java.net.URL;

import com.taliateli.dao.Conexao;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static URL PONTO_FXML = Main.class.getResource("Ponto.fxml");

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) throws Exception {
		try {
			Conexao.getConexao();
			FXMLLoader fxmlLoader = new FXMLLoader(PONTO_FXML);
			Parent root;
			root = fxmlLoader.load();
			Scene scene = new Scene(root);
			stage.setTitle("Controle de Ponto");
			// stage.initModality(Modality.WINDOW_MODAL);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
