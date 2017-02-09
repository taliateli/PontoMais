package com.taliateli.control;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.taliateli.dao.ColaboradorDao;
import com.taliateli.dao.PontoDao;
import com.taliateli.model.Colaborador;
import com.taliateli.model.PontoEntrada;
import com.taliateli.model.PontoEntradaAlmoco;
import com.taliateli.util.MensagensDialog;
import com.taliateli.util.Util;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

public class PontoControl implements Initializable {

	@FXML
	private ComboBox<Colaborador> cbColaboradores;
	@FXML
	private ListView<String> lvRegistro;
	@FXML
	private TextField txDtInicial;
	@FXML
	private TextField txDtFinal;
	@FXML
	private TableView<ColaboradorModel> tbvColaboradores;
	@FXML
	private TableColumn<ColaboradorModel, String> colId;
	@FXML
	private TableColumn<ColaboradorModel, String> colNome;
	@FXML
	private TableColumn<ColaboradorModel, String> colFuncao;
	@FXML
	private TableColumn<ColaboradorModel, String> colDtInclusao;
	private ObservableList<ColaboradorModel> colaboradorObsList;
	private ColaboradorDao cd;
	private List<Colaborador> colaboradores;
	private List<String> logs;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			logs = new ArrayList<String>();
			cd = new ColaboradorDao();
			preencherTbView();
			carregarCboColaboradores();
			Util.dateField(txDtInicial);
			Util.dateField(txDtFinal);
		} catch (Exception e) {
			MensagensDialog.getAlertError(e.getMessage());
		}

	}

	private void preencherTbView() throws Exception {
		colaboradores = cd.listarColaboradores();
		colaboradorObsList = FXCollections.observableArrayList();
		for (Colaborador col : colaboradores) {
			ColaboradorModel colModel = new ColaboradorModel(col);
			colaboradorObsList.add(colModel);
		}
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colFuncao.setCellValueFactory(new PropertyValueFactory<>("funcao"));
		colDtInclusao.setCellValueFactory(new PropertyValueFactory<>("dtInclusao"));

		tbvColaboradores.refresh();

		tbvColaboradores.setItems(colaboradorObsList);

	}

	private void carregarCboColaboradores() throws Exception {
		ObservableList<Colaborador> oblColaboradores = FXCollections.observableArrayList();
		colaboradores = cd.listarColaboradores();
		for (Colaborador col : colaboradores) {
			oblColaboradores.add(col);
		}
		cbColaboradores.setItems(oblColaboradores);
	}

	private void validarPonto() throws Exception {
		if (cbColaboradores.getSelectionModel().getSelectedItem() == null) {
			throw new Exception("Selecione algum colaborador para registrar o ponto!");
		}
	}

	@FXML
	public void btPontoEntrada() {
		try {
			validarPonto();
			Colaborador col = new Colaborador();
			col = cbColaboradores.getSelectionModel().getSelectedItem();
			PontoDao pd = new PontoDao();
			String tbPonto = "entrada";
			PontoEntrada pEntrada = new PontoEntrada();
			pEntrada.setColaborador(col);
			pEntrada.setDataHora(new Date());
			pd.salvar(tbPonto, col, pEntrada);
			logs.add("Ponto registrado com sucesso. Colaborador: " + col.getNome() + " - "
					+ Util.getDataFormatada(pEntrada.getDataHora(), "dd/MM/yyyy HH:mm:ss"));
			lvRegistro.setItems(FXCollections.observableArrayList(logs));
			MensagensDialog.getAlertInformation("Informa��o do Sistema", "Ponto registrado com sucesso!");
		} catch (Exception e) {
			MensagensDialog.getAlertError(e.getMessage());
		}

	}

	@FXML
	public void btPontoEntradaAlmoco() {
		try {
			validarPonto();
			Colaborador col = new Colaborador();
			col = cbColaboradores.getSelectionModel().getSelectedItem();
			PontoDao pd = new PontoDao();
			String tbPonto = "entrada_almoco";
			PontoEntradaAlmoco pEntradaAlmoco = new PontoEntradaAlmoco();
			pEntradaAlmoco.setColaborador(col);
			pEntradaAlmoco.setDataHora(new Date());
			pd.salvar(tbPonto, col, pEntradaAlmoco);
			logs.add("Ponto registrado com sucesso. Colaborador: " + col.getNome() + " - "
					+ Util.getDataFormatada(pEntradaAlmoco.getDataHora(), "dd/MM/yyyy HH:mm:ss"));
			lvRegistro.setItems(FXCollections.observableArrayList(logs));
			MensagensDialog.getAlertInformation("Informa��o do Sistema", "Ponto registrado com sucesso!");
		} catch (Exception e) {
			MensagensDialog.getAlertError(e.getMessage());
		}

	}

	@FXML
	public void btPontoSaidaAlmoco() {
		try {
			validarPonto();
			Colaborador col = new Colaborador();
			col = cbColaboradores.getSelectionModel().getSelectedItem();
			PontoDao pd = new PontoDao();
			String tbPonto = "saida_almoco";
			PontoEntradaAlmoco pSaidaAlmoco = new PontoEntradaAlmoco();
			pSaidaAlmoco.setColaborador(col);
			pSaidaAlmoco.setDataHora(new Date());
			pd.salvar(tbPonto, col, pSaidaAlmoco);
			logs.add("Ponto registrado com sucesso. Colaborador: " + col.getNome() + " - "
					+ Util.getDataFormatada(pSaidaAlmoco.getDataHora(), "dd/MM/yyyy HH:mm:ss"));
			lvRegistro.setItems(FXCollections.observableArrayList(logs));
			MensagensDialog.getAlertInformation("Informa��o do Sistema", "Ponto registrado com sucesso!");
		} catch (Exception e) {
			MensagensDialog.getAlertError(e.getMessage());
		}

	}

	@FXML
	public void btPontoSaida() {
		try {
			validarPonto();
			Colaborador col = new Colaborador();
			col = cbColaboradores.getSelectionModel().getSelectedItem();
			PontoDao pd = new PontoDao();
			String tbPonto = "saida";
			PontoEntradaAlmoco pSaida = new PontoEntradaAlmoco();
			pSaida.setColaborador(col);
			pSaida.setDataHora(new Date());
			pd.salvar(tbPonto, col, pSaida);
			logs.add("Ponto registrado com sucesso. Colaborador: " + col.getNome() + " - "
					+ Util.getDataFormatada(pSaida.getDataHora(), "dd/MM/yyyy HH:mm:ss"));
			lvRegistro.setItems(FXCollections.observableArrayList(logs));
			MensagensDialog.getAlertInformation("Informa��o do Sistema", "Ponto registrado com sucesso!");
		} catch (Exception e) {
			MensagensDialog.getAlertError(e.getMessage());
		}

	}

	@FXML
	public void btCadastrarColaborador() {
		try {
			Dialog<Pair<String, String>> dialog = new Dialog<>();
			dialog.setTitle("Cadastro de Colaborador");
			dialog.setHeaderText("Preencha os campos abaixo");

			ButtonType btSalvar = new ButtonType("Salvar", ButtonData.OK_DONE);
			ButtonType btCancelar = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
			dialog.getDialogPane().getButtonTypes().addAll(btSalvar, btCancelar);

			GridPane grid = new GridPane();
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(20, 150, 10, 10));
			TextField txNome = new TextField();
			TextField txFuncao = new TextField();

			grid.add(new Label("Nome: "), 0, 0);
			grid.add(txNome, 1, 0);
			grid.add(new Label("Fun��o: "), 0, 1);
			grid.add(txFuncao, 1, 1);

			dialog.getDialogPane().setContent(grid);
			Platform.runLater(() -> txNome.requestFocus());

			Optional<Pair<String, String>> result = dialog.showAndWait();
			if (result.isPresent()) {
				ColaboradorDao cd = new ColaboradorDao();
				Colaborador col = new Colaborador();
				col.setNome(txNome.getText().toUpperCase());
				col.setFuncao(txFuncao.getText().toUpperCase());
				col.setDtInclusao(new Date());
				cd.salvar(col);
				MensagensDialog.getAlertInformation("Informa��o do Sistema", "Colaborador cadastrado com sucesso!");
				preencherTbView();
				carregarCboColaboradores();
			}
		} catch (Exception e) {
			MensagensDialog.getAlertError(e.getMessage());
		}
	}

	@FXML
	public void btExcluirColaborador() {
		try {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirma��o de Exclus�o");
			alert.setHeaderText("Confirma a exclus�o do colaborador");

			ButtonType btSim = new ButtonType("Sim");
			ButtonType btNao = new ButtonType("N�o");
			alert.getButtonTypes().setAll(btSim, btNao);
			Optional<ButtonType> result = alert.showAndWait();
			ColaboradorDao cd = new ColaboradorDao();
			if (result.get() == btSim) {
				for (Colaborador col : this.colaboradores) {
					ColaboradorModel colModel = new ColaboradorModel(col);
					colModel = tbvColaboradores.getSelectionModel().getSelectedItem();
					if (col.getId().toString().equals(colModel.getId())) {
						col = cd.consultar(col.getId());
						col.setDtExclusao(new Date());
						cd.excluir(col);
					}
				}
				preencherTbView();
				carregarCboColaboradores();
			}
			if (result.get() == btNao) {
				alert.close();
			}
		} catch (Exception e) {
			MensagensDialog.getAlertError(e.getMessage());
		}
	}

	public class ColaboradorModel {
		private String id;
		private String nome;
		private String funcao;
		private String dtInclusao;

		public ColaboradorModel(Colaborador col) {
			this.id = col.getId().toString();
			this.nome = col.getNome();
			this.funcao = col.getFuncao();
			this.dtInclusao = Util.getDataFormatada(col.getDtInclusao(), "dd/MM/yyyy");
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getFuncao() {
			return funcao;
		}

		public void setFuncao(String funcao) {
			this.funcao = funcao;
		}

		public String getDtInclusao() {
			return dtInclusao;
		}

		public void setDtInclusao(String dtInclusao) {
			this.dtInclusao = dtInclusao;
		}

	}

}
