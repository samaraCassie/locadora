package com.ifsc.tds.samara.leticia.controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.ifsc.tds.samara.leticia.dao.FilmeDAO;
import com.ifsc.tds.samara.leticia.entity.Filme;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class listaFilmesController implements Initializable{
	
 	@FXML
    private Label txtDetalhes;

    @FXML
    private Label txtNome;

    @FXML
    private Label txtLogin;

    @FXML
    private Label txtEmail;

    @FXML
    private Label txtDatadeCadastro;

    @FXML
    private Label txtNomeUser;

    @FXML
    private Label txtLoginUser;

    @FXML
    private Label txtEmailUser;

    @FXML
    private Label txtDataUser;

    @FXML
    private Button bntEditar;

    @FXML
    private Button bntIncluir;

    @FXML
    private Button btnExcluir;
    
	private List<Filme> listaFilmes;
	private ObservableList<Filme> observableListaFilmes = FXCollections.observableArrayList();
	private FilmeDAO filmeDAO;

	private Object tblViewFilmes;

	private Object columnCodigo;

	private Object columnNome;

	private Object lblRecebeNome;

	private Object lblRecebeFone;

	public static final String Filme_EDITAR = " - Editar";
	public static final String Filme_INCLUIR = " - Incluir";

	@FXML
	void onClickBtnEditar(ActionEvent event) {
		Filme Filme = this.tblViewFilmes.getSelectionModel().getSelectedItem();

		if (Filme != null) {
			boolean btnConfirmarClic = this.onShowTelaFilmeEditar(Filme, listaFilmesController.Filme_EDITAR);

			if (btnConfirmarClic) {
				this.getFilmeDAO().update(Filme, null);
				this.carregarTableViewFilmes();
			}
		} else {
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setContentText("Escolha um Filme na tabela!");
			alerta.show();
		}
	}

	@FXML
	void onClickBtnExcluir(ActionEvent event) {
		Filme Filme = this.tblViewFilmes.getSelectionModel().getSelectedItem();

		if (Filme != null) {

			Alert alerta = new Alert(AlertType.CONFIRMATION);
			alerta.setTitle("Pergunta");
			alerta.setHeaderText("Confirma a exclusão do Filme?\n" + Filme.getNome());

			ButtonType botaoNao = ButtonType.NO;
			ButtonType botaoSim = ButtonType.YES;
			alerta.getButtonTypes().setAll(botaoSim, botaoNao);
			Optional<ButtonType> resultado = alerta.showAndWait();

			if (resultado.get() == botaoSim) {
				this.getFilmeDAO().delete(Filme);
				this.carregarTableViewFilmes();
			}
		} else {
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setContentText("Por favor, escolha uma Filme na tabela!");
			alerta.show();
		}
	}

	@FXML
	void onClickBtnIncluir(ActionEvent event) {
		Filme Filme = new Filme();

		boolean btnConfirmarClic = this.onShowTelaFilmeEditar(Filme, listaFilmesController.Filme_INCLUIR);

		if (btnConfirmarClic) {
			this.getFilmeDAO().save(Filme);
			this.carregarTableViewFilmes();
		}
	}

	public FilmeDAO getFilmeDAO() {
		return filmeDAO;
	}

	public void setFilmeDAO(FilmeDAO FilmeDAO) {
		this.filmeDAO = FilmeDAO;
	}

	public List<Filme> getListaFilmes() {
		return listaFilmes;
	}

	public void setListaFilmes(List<Filme> listaFilmes) {
		this.listaFilmes = listaFilmes;
	}

	public ObservableList<Filme> getObservableListaFilmes() {
		return observableListaFilmes;
	}

	public void setObservableListaFilmes(ObservableList<Filme> observableListaFilmes) {
		this.observableListaFilmes = observableListaFilmes;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.setFilmeDAO(new FilmeDAO());
		this.carregarTableViewFilmes();
		this.selecionarItemTableViewFilmes(null);

		this.tblViewFilmes.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> selecionarItemTableViewFilmes((Filme) newValue));
	}

	public void carregarTableViewFilmes() {
		this.columnCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
		this.columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

		this.setListaFilmes(this.getFilmeDAO().getAll());
		this.setObservableListaFilmes(FXCollections.observableArrayList(this.getListaFilmes()));
		this.tblViewFilmes.setItems(this.getObservableListaFilmes());
	}

	public void selecionarItemTableViewFilmes(Filme Filme) {
		if (Filme != null) {
			this.lblRecebeNome.setText(Filme.getNome());
			this.lblRecebeFone.setText(Filme.getFone());
			
			
		} else {
			this.lblRecebeNome.setText("");
			this.lblRecebeFone.setText("");
			
		}
	}

	public boolean onCloseQuery() {
		Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
		alerta.setTitle("Pergunta");
		alerta.setHeaderText("Deseja sair do sistema?");
		ButtonType buttonTypeNO = ButtonType.NO;
		ButtonType buttonTypeYES = ButtonType.YES;
		alerta.getButtonTypes().setAll(buttonTypeYES, buttonTypeNO);
		Optional<ButtonType> result = alerta.showAndWait();
		return result.get() == buttonTypeYES ? true : false;
	}

	public boolean onShowTelaFilmeEditar(Filme Filme, String operacao) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ifsc/tds/julian/kelcia/noemi/view/FilmeEdit.fxml"));
			Parent FilmeEditXML = loader.load();
			
			Stage janelaFilmeEditar = new Stage();
			janelaFilmeEditar.setTitle("Cadastro de Filme" + operacao);
			janelaFilmeEditar.initModality(Modality.APPLICATION_MODAL);
			janelaFilmeEditar.resizableProperty().setValue(Boolean.FALSE);

			Scene FilmeEditLayout = new Scene(FilmeEditXML);
			janelaFilmeEditar.setScene(FilmeEditLayout);

			editFilmesController editFilmeController = loader.getController();
			editFilmeController.setJanelaClienteEdit(janelaFilmeEditar);
			editFilmeController.populaTela(Filme);

			janelaFilmeEditar.showAndWait();

			return editFilmeController.isOkClick();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public List<Filme> retornaListagemFilme() {
		if (this.getFilmeDAO() == null) {
			this.setFilmeDAO(new FilmeDAO());
		}
		return this.getFilmeDAO().getAll();
	}
}

