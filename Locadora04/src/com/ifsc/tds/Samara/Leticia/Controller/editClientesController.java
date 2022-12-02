package com.ifsc.tds.Samara.Leticia.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.ifsc.tds.samara.leticia.entity.Contato;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class editClientesController {
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

	private Stage janelaContatoEdit;

	private Contato contato;

	private boolean OnClickOk = false;

	@FXML
	void onClickBtnCancela(ActionEvent event) {
		this.getJanelaContatoEdit().close();
	}

	@FXML
	void onClickBtnOK(ActionEvent event) {
		if (validarCampos()) {
			this.contato.setNome(this.txtNome.getText());
			this.contato.setFone(this.txtFone.getText());
			this.contato.setEmail(this.txtEmail.getText());

			this.OnClickOk = true;
			this.getJanelaContatoEdit().close();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public Stage getJanelaContatoEdit() {
		return janelaContatoEdit;
	}

	public void setJanelaContatoEdit(Stage janelaContatoEdit) {
		this.janelaContatoEdit = janelaContatoEdit;
	}

	public void populaTela(Contato contato) {
		this.contato = contato;

		this.txtNome.setText(contato.getNome());
		this.txtFone.setText(contato.getFone());
		this.txtEmail.setText(contato.getEmail());
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
			alerta.initOwner(this.janelaContatoEdit);
			alerta.setTitle("Dados invalidos!");
			alerta.setHeaderText("Tente:");
			alerta.setContentText(avisoERRO);
			alerta.showAndWait();

			return false;
		}
	}
}
}
