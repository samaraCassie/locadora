package com.ifsc.tds.samara.leticia.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.ifsc.tds.samara.leticia.entity.Cliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class editLocacaoController implements Initializable{
	@FXML
    private AnchorPane paneMain;

    @FXML
    private GridPane paneGrid;

    @FXML
    private Label lblNome;

    @FXML
    private TextField txtNome;

    @FXML
    private Label lblFone;

    @FXML
    private TextField txtFone;
    
    @FXML
    private Label lblEmail;

    @FXML
    private TextField txtEmail;

    @FXML
    private HBox boxButton;

    @FXML
    private Button btnOK;

    @FXML
    private Button btnCancela;

	private Stage janelaClienteEdit;

	private Cliente cliente;

	private boolean OnClickOk = false;

	@FXML
	void onClickBtnCancela(ActionEvent event) {
		this.getJanelaClienteEdit().close();
	}

	@FXML
	void onClickBtnOK(ActionEvent event) {
		if (validarCampos()) {
			this.cliente.setNome(this.txtNome.getText());
			this.cliente.setFone(this.txtFone.getText());
			this.cliente.setEmail(this.txtEmail.getText());

			this.OnClickOk = true;
			this.getJanelaClienteEdit().close();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public Stage getJanelaClienteEdit() {
		return janelaClienteEdit;
	}

	public void setJanelaClienteEdit(Stage janelaClienteEdit) {
		this.janelaClienteEdit = janelaClienteEdit;
	}

	public void populaTela(Cliente cliente) {
		this.cliente = cliente;

		this.txtNome.setText(cliente.getNome());
		this.txtFone.setText(cliente.getFone());
		this.txtEmail.setText(cliente.getEmail());
	}

	public boolean isOkClick() {
		return OnClickOk;
	}

	private boolean validarCampos() {
		String avisoERRO = new String();

		if (this.txtNome.getText() == null || this.txtNome.getText().trim().length() == 0) {
			avisoERRO += "Verificar o nome!\n";
		}

		if (this.txtFone.getText() == null || this.txtFone.getText().trim().length() == 0) {
			avisoERRO += "Verificar o telefone!\n";
		}
		
		if (this.txtEmail.getText() == null || this.txtEmail.getText().trim().length() == 0) {
			avisoERRO += "Verificar o email!\n";
		}

		if (avisoERRO.length() == 0) {
			return true;
		} else {
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.initOwner(this.janelaClienteEdit);
			alerta.setTitle("Dados invalidos!");
			alerta.setHeaderText("Tente:");
			alerta.setContentText(avisoERRO);
			alerta.showAndWait();

			return false;
		}
	}

	public void setJanelaLocacaoEdit(Stage janelaLocacaoEditar) {
		// TODO Auto-generated method stub
		
	}
}

