package com.taliateli.main;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.taliateli.dao.Conexao;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Main extends Application {

	public static URL PONTO_FXML = Main.class.getResource("Ponto.fxml");
	private Label lblRelogio;
	private SimpleDateFormat formatador = new SimpleDateFormat("hh:mm:ss a");

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
			stage.initStyle(StageStyle.UTILITY);
			stage.setScene(scene);
			stage.show();

			// criamos a fonte usando o método de fábrica.
			// Font font = Font.font("Arial", FontWeight.EXTRA_BOLD, 60);
			// lblRelogio.setFont(font);
			//
			// lblRelogio.setEffect(new DropShadow(10, Color.RED));
			//
			// agora ligamos um loop infinito que roda a cada segundo e atualiza
			// nosso label chamando atualizaHoras.
			// KeyFrame frame = new KeyFrame(Duration.millis(1000), e ->
			// atualizaHoras());
			// Timeline timeline = new Timeline(frame);
			// timeline.setCycleCount(Timeline.INDEFINITE);
			// timeline.play();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void atualizaHoras() {
		Date agora = new Date();
		lblRelogio.setText(formatador.format(agora));
	}
}
