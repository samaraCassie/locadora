package com.ifsc.tds.samara.leticia.controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class mainController implements Initializable {
	
	 	@FXML
	    private MenuBar menuBar;

	    @FXML
	    private Menu menuCadastro;

	    @FXML
	    private MenuItem itemClientes;

	    @FXML
	    private MenuItem itemFilmes;

	    @FXML
	    private MenuItem itemEmprestimos;

	    @FXML
	    private MenuItem itemSair;


	    @FXML
	    void OnClickCadastro(ActionEvent event) {

	    }
	    
	    @FXML
	    void OnClickSair(ActionEvent event) {

	    }

		@FXML
		void OnClickClientes(ActionEvent event) {
			try {
				// carregando o loader
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ifsc/tds/Samara/Leticia/view/CaixaLista.fxml"));
				Parent listaClienteXML = loader.load();

				// carregando o controller e a scene
				listaClientesController listaCliente = loader.getController();
				Scene listaClienteLayout = new Scene(listaClienteXML);

				this.getStage().setScene(listaClienteLayout);
				this.getStage().setTitle("Cadastro de caixa");

				// atribuindo evento para fechar janela
				this.getStage().setOnCloseRequest(e -> {
					if (caixaListaController.onCloseQuery()) {
						this.getStage().close();
					} else {
						e.consume();
					}
				});

				this.stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@FXML
		void  OnClickFilmes(ActionEvent event) {
			try {
				// carregando o loader
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/peregrinoti/view/TipoColecaoLista.fxml"));
				Parent listaFilmesXML = loader.load();

				// carregando o controller e a scene
				listaFilmesController listaFilmes = loader.getController();
				Scene listaFilmesLayout = new Scene(listaFilmesXML);

				this.getStage().setScene(listaFilmesLayout);
				this.getStage().setTitle("Cadastro de tipo de cole��o");

				// atribuindo evento para fechar janela
				this.getStage().setOnCloseRequest(e -> {
					if (tipoColecaoListaController.onCloseQuery()) {
						this.getStage().close();
					} else {
						e.consume();
					}
				});

				this.stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@FXML
		void OnClickEmprestimos(ActionEvent event) {
			try {
				// carregando o loader
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/peregrinoti/view/RevistaLista.fxml"));
				Parent revistaListaXML = loader.load();

				// carregando o controller e a scene
				RevistaListaController revistaListaController = loader.getController();
				Scene revistaListaLayout = new Scene(revistaListaXML);

				this.getStage().setScene(revistaListaLayout);
				this.getStage().setTitle("Cadastro de revista");

				// atribuindo evento para fechar janela
				this.getStage().setOnCloseRequest(e -> {
					if (revistaListaController.onCloseQuery()) {
						this.getStage().close();
					} else {
						e.consume();
					}
				});

				this.stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@FXML
		void onClickMnoSair(ActionEvent event) {
			if (this.onCloseQuery()) {
				System.exit(0);
			} else {
				event.consume();
			}
		}

		@FXML
		void onClickMnoSobre(ActionEvent event) {
			Alert alerta = new Alert(Alert.AlertType.INFORMATION);
			alerta.setTitle("Sobre");
			alerta.setHeaderText("Sistema desenvolvido por: Peregrino TI - 2020.\nDesenvolvido com JavaFX vers�o 1.0.");
			alerta.showAndWait();
		}

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			this.configuraBarraStatus();
			this.configuraStage();
		}

		public boolean onCloseQuery() {
			Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
			alerta.setTitle("Pergunta");
			alerta.setHeaderText("Deseja sair do sistema?");
			ButtonType botaoNao = ButtonType.NO;
			ButtonType botaoSim = ButtonType.YES;
			alerta.getButtonTypes().setAll(botaoSim, botaoNao);
			Optional<ButtonType> resultado = alerta.showAndWait();
			return resultado.get() == botaoSim ? true : false;
		}

		public void configuraBarraStatus() {
			DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			this.lblData.setText("Data: " + LocalDateTime.now().format(dataFormatada));

			Timeline relogio = new Timeline(new KeyFrame(Duration.ZERO, e -> {
				DateTimeFormatter horaFormatada = DateTimeFormatter.ofPattern("HH:mm:ss");
				this.lblHora.setText("Hora: " + LocalDateTime.now().format(horaFormatada));
			}), new KeyFrame(Duration.seconds(1)));
			relogio.setCycleCount(Animation.INDEFINITE);
			relogio.play();
		}

		// configura tela
		public void configuraStage() {
			this.setStage(new Stage());
			this.getStage().initModality(Modality.APPLICATION_MODAL);
			this.getStage().resizableProperty().setValue(Boolean.FALSE);
		}

	}

	}

