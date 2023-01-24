package com.ifsc.tds.samara.leticia.controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.ifsc.tds.samara.leticia.dao.LocacaoDAO;
import com.ifsc.tds.samara.leticia.entity.Locacao;

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

public class listaLocacaoController implements Initializable{
	
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

    @FXML
    void OnClickExcluir(ActionEvent event) {

    }

    @FXML
    void OnClickIncluir(ActionEvent event) {

    }

    @FXML
    void bntEditar(ActionEvent event) {
    }
    
	private List<Locacao> listaLocacoes;
	private ObservableList<Locacao> observableListaLocacaos = FXCollections.observableArrayList();
	private LocacaoDAO locacaoDAO;

	private Object tblViewLocacaos;

	private Object lblRecebeNomeFilme;

	private Object lblRecebeDataLocacao;

	private Object columnNomeFilme;

	private Object columnCodigo;

	public static final String CONTATO_EDITAR = " - Editar";
	public static final String CONTATO_INCLUIR = " - Incluir";

	@FXML
	void onClickBtnEditar(ActionEvent event) {
		Locacao locacao = this.tblViewLocacaos.getSelectionModel().getSelectedItem();

		if (locacao != null) {
			boolean btnConfirmarClic = this.onShowTelaLocacaoEditar(locacao, listaLocacaoController.CONTATO_EDITAR);

			if (btnConfirmarClic) {
				this.getLocacaoDAO().update(locacao, null);
				this.carregarTableViewLocacaos();
			}
		} else {
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setContentText("Escolha um locacao na tabela!");
			alerta.show();
		}
	}

	@FXML
	void onClickBtnExcluir(ActionEvent event) {
		Locacao locacao = this.tblViewLocacaos.getSelectionModel().getSelectedItem();

		if (locacao != null) {

			Alert alerta = new Alert(AlertType.CONFIRMATION);
			alerta.setTitle("Pergunta");
			alerta.setHeaderText("Confirma a exclusão do locacao?\n" + locacao.getNomeFilme());

			ButtonType botaoNao = ButtonType.NO;
			ButtonType botaoSim = ButtonType.YES;
			alerta.getButtonTypes().setAll(botaoSim, botaoNao);
			Optional<ButtonType> resultado = alerta.showAndWait();

			if (resultado.get() == botaoSim) {
				this.getLocacaoDAO().delete(locacao);
				this.carregarTableViewLocacaos();
			}
		} else {
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setContentText("Por favor, escolha uma locacao na tabela!");
			alerta.show();
		}
	}

	@FXML
	void onClickBtnIncluir(ActionEvent event) {
		Locacao locacao = new Locacao();

		boolean btnConfirmarClic = this.onShowTelaLocacaoEditar(locacao, listaLocacaoController.CONTATO_INCLUIR);

		if (btnConfirmarClic) {
			this.getLocacaoDAO().save(locacao);
			this.carregarTableViewLocacaos();
		}
	}

	public LocacaoDAO getLocacaoDAO() {
		return locacaoDAO;
	}

	public void setLocacaoDAO(LocacaoDAO locacaoDAO) {
		this.locacaoDAO = locacaoDAO;
	}

	public List<Locacao> getListaLocacaos() {
		return listaLocacoes;
	}

	public void setListaLocacaos(List<Locacao> listaLocacoes) {
		this.listaLocacoes = listaLocacoes;
	}

	public ObservableList<Locacao> getObservableListaLocacaos() {
		return observableListaLocacaos;
	}

	public void setObservableListaLocacaos(ObservableList<Locacao> observableListaLocacaos) {
		this.observableListaLocacaos = observableListaLocacaos;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.setLocacaoDAO(new LocacaoDAO());
		this.carregarTableViewLocacaos();
		this.selecionarItemTableViewLocacaos(null);

		this.tblViewLocacaos.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> selecionarItemTableViewLocacaos((Locacao) newValue));
	}

	public void carregarTableViewLocacaos() {
		this.columnCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
		this.columnNomeFilme.setCellValueFactory(new PropertyValueFactory<>("nome"));

		this.setListaLocacaos(this.getLocacaoDAO().getAll());
		this.setObservableListaLocacaos(FXCollections.observableArrayList(this.getListaLocacaos()));
		this.tblViewLocacaos.setItems(this.getObservableListaLocacaos());
	}

	public void selecionarItemTableViewLocacaos(Locacao locacao) {
		if (locacao != null) {
			this.lblRecebeNomeFilme.setText(locacao.getNomeFilme());
			this.lblRecebeDataLocacao.setText(locacao.getDataLocacao());
			
			
		} else {
			this.lblRecebeNomeFilme.setText("");
			this.lblRecebeDataLocacao.setText("");
			
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

	public boolean onShowTelaLocacaoEditar(Locacao locacao, String operacao) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ifsc/tds/julian/kelcia/noemi/view/LocacaoEdit.fxml"));
			Parent locacaoEditXML = loader.load();
			
			Stage janelaLocacaoEditar = new Stage();
			janelaLocacaoEditar.setTitle("Cadastro de locacao" + operacao);
			janelaLocacaoEditar.initModality(Modality.APPLICATION_MODAL);
			janelaLocacaoEditar.resizableProperty().setValue(Boolean.FALSE);

			Scene locacaoEditLayout = new Scene(locacaoEditXML);
			janelaLocacaoEditar.setScene(locacaoEditLayout);

			editLocacaoController locacaoEditController = loader.getController();
			locacaoEditController.setJanelaLocacaoEdit(janelaLocacaoEditar);
			locacaoEditController.populaTela(locacao);

			janelaLocacaoEditar.showAndWait();

			return locacaoEditController.isOkClick();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public List<Locacao> retornaListagemLocacao() {
		if (this.getLocacaoDAO() == null) {
			this.setLocacaoDAO(new LocacaoDAO());
		}
		return this.getLocacaoDAO().getAll();
	}
}
