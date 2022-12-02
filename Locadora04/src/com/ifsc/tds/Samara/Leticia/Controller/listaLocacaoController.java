package com.ifsc.tds.Samara.Leticia.controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.ifsc.tds.samara.leticia.controller.ContatoEditController;
import com.ifsc.tds.samara.leticia.controller.ContatoListaController;
import com.ifsc.tds.samara.leticia.dao.ContatoDAO;
import com.ifsc.tds.samara.leticia.entity.Contato;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

public class listaLocacaoController {
	
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
    
	private List<Contato> listaContatos;
	private ObservableList<Contato> observableListaContatos = FXCollections.observableArrayList();
	private ContatoDAO contatoDAO;

	public static final String CONTATO_EDITAR = " - Editar";
	public static final String CONTATO_INCLUIR = " - Incluir";

	@FXML
	void onClickBtnEditar(ActionEvent event) {
		Contato contato = this.tblViewContatos.getSelectionModel().getSelectedItem();

		if (contato != null) {
			boolean btnConfirmarClic = this.onShowTelaContatoEditar(contato, ContatoListaController.CONTATO_EDITAR);

			if (btnConfirmarClic) {
				this.getContatoDAO().update(contato, null);
				this.carregarTableViewContatos();
			}
		} else {
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setContentText("Escolha um contato na tabela!");
			alerta.show();
		}
	}

	@FXML
	void onClickBtnExcluir(ActionEvent event) {
		Contato contato = this.tblViewContatos.getSelectionModel().getSelectedItem();

		if (contato != null) {

			Alert alerta = new Alert(AlertType.CONFIRMATION);
			alerta.setTitle("Pergunta");
			alerta.setHeaderText("Confirma a exclusão do contato?\n" + contato.getNome());

			ButtonType botaoNao = ButtonType.NO;
			ButtonType botaoSim = ButtonType.YES;
			alerta.getButtonTypes().setAll(botaoSim, botaoNao);
			Optional<ButtonType> resultado = alerta.showAndWait();

			if (resultado.get() == botaoSim) {
				this.getContatoDAO().delete(contato);
				this.carregarTableViewContatos();
			}
		} else {
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setContentText("Por favor, escolha uma contato na tabela!");
			alerta.show();
		}
	}

	@FXML
	void onClickBtnIncluir(ActionEvent event) {
		Contato contato = new Contato();

		boolean btnConfirmarClic = this.onShowTelaContatoEditar(contato, ContatoListaController.CONTATO_INCLUIR);

		if (btnConfirmarClic) {
			this.getContatoDAO().save(contato);
			this.carregarTableViewContatos();
		}
	}

	public ContatoDAO getContatoDAO() {
		return contatoDAO;
	}

	public void setContatoDAO(ContatoDAO contatoDAO) {
		this.contatoDAO = contatoDAO;
	}

	public List<Contato> getListaContatos() {
		return listaContatos;
	}

	public void setListaContatos(List<Contato> listaContatos) {
		this.listaContatos = listaContatos;
	}

	public ObservableList<Contato> getObservableListaContatos() {
		return observableListaContatos;
	}

	public void setObservableListaContatos(ObservableList<Contato> observableListaContatos) {
		this.observableListaContatos = observableListaContatos;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.setContatoDAO(new ContatoDAO());
		this.carregarTableViewContatos();
		this.selecionarItemTableViewContatos(null);

		this.tblViewContatos.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> selecionarItemTableViewContatos((Contato) newValue));
	}

	public void carregarTableViewContatos() {
		this.columnCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
		this.columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

		this.setListaContatos(this.getContatoDAO().getAll());
		this.setObservableListaContatos(FXCollections.observableArrayList(this.getListaContatos()));
		this.tblViewContatos.setItems(this.getObservableListaContatos());
	}

	public void selecionarItemTableViewContatos(Contato contato) {
		if (contato != null) {
			this.lblRecebeNome.setText(contato.getNome());
			this.lblRecebeFone.setText(contato.getFone());
			
			
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

	public boolean onShowTelaContatoEditar(Contato contato, String operacao) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ifsc/tds/julian/kelcia/noemi/view/ContatoEdit.fxml"));
			Parent contatoEditXML = loader.load();
			
			Stage janelaContatoEditar = new Stage();
			janelaContatoEditar.setTitle("Cadastro de contato" + operacao);
			janelaContatoEditar.initModality(Modality.APPLICATION_MODAL);
			janelaContatoEditar.resizableProperty().setValue(Boolean.FALSE);

			Scene contatoEditLayout = new Scene(contatoEditXML);
			janelaContatoEditar.setScene(contatoEditLayout);

			ContatoEditController contatoEditController = loader.getController();
			contatoEditController.setJanelaContatoEdit(janelaContatoEditar);
			contatoEditController.populaTela(contato);

			janelaContatoEditar.showAndWait();

			return contatoEditController.isOkClick();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public List<Contato> retornaListagemContato() {
		if (this.getContatoDAO() == null) {
			this.setContatoDAO(new ContatoDAO());
		}
		return this.getContatoDAO().getAll();
	}
}

}
