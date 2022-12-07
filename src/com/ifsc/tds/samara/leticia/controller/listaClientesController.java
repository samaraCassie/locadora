package com.ifsc.tds.samara.leticia.controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.ifsc.tds.samara.leticia.dao.ClienteDAO;
import com.ifsc.tds.samara.leticia.entity.Cliente;

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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class listaClientesController implements Initializable{
	
	 @FXML
	    private AnchorPane paneMain;

	    @FXML
	    private SplitPane paneDivisao;

	    @FXML
	    private AnchorPane paneEsquerda;

	    @FXML
	    private TableView<Cliente> tbvClientes;

	    @FXML
	    private TableColumn<?, ?> columnCodigo;

	    @FXML
	    private TableColumn<?, ?> columnNome;

	    @FXML
	    private AnchorPane paneDireita;

	    @FXML
	    private Label lblDetalhes;

	    @FXML
	    private GridPane paneGridDetalhes;

	    @FXML
	    private Label lblNome;

	    @FXML
	    private Label lblFone;

	    @FXML
	    private Label lblRecebeNome;

	    @FXML
	    private Label lblRecebeFone;
	  
	    @FXML
	    private ButtonBar btnBarBotoes;

	    @FXML
	    private Button btnInclur;

	    @FXML
	    private Tooltip tlpIncluir;

	    @FXML
	    private Button btnEditar;

	    @FXML
	    private Tooltip tlpEditar;

	    @FXML
	    private Tooltip tlpExcluir;
	    
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
	    
	    private List<Cliente>listaClientes;
		private ObservableList<Cliente> observableListaClientes = FXCollections.observableArrayList();
		private ClienteDAO clienteDAO;

		public static final String CLIENTES_EDITAR = " - Editar";
		public static final String CLIENTES_INCLUIR = " - Incluir";

		@FXML
		void OnClickEditar(ActionEvent event) {
			Cliente cliente = this.tbvClientes.getSelectionModel().getSelectedItem();

			if (cliente != null) {
				boolean btnConfirmarClic = this.onShowTelaClientesEditar(cliente, listaClientesController.CLIENTES_EDITAR);

				if (btnConfirmarClic) {
					this.getClientesDAO().update(cliente, null);
					this.carregarTableViewClientes();
				}
			} else {
				Alert alerta = new Alert(Alert.AlertType.ERROR);
				alerta.setContentText("Escolha um Clientes na tabela!");
				alerta.show();
			}
		}

		@FXML
		void OnClickExcluir(ActionEvent event) {
			Cliente cliente = this.tbvClientes.getSelectionModel().getSelectedItem();

			if (cliente != null) {

				Alert alerta = new Alert(AlertType.CONFIRMATION);
				alerta.setTitle("Pergunta");
				alerta.setHeaderText("Confirma a exclusão do Clientes?\n" + cliente.getNome());

				ButtonType botaoNao = ButtonType.NO;
				ButtonType botaoSim = ButtonType.YES;
				alerta.getButtonTypes().setAll(botaoSim, botaoNao);
				Optional<ButtonType> resultado = alerta.showAndWait();

				if (resultado.get() == botaoSim) {
					this.getClientesDAO().delete(cliente);
					this.carregarTableViewClientes();
				}
			} else {
				Alert alerta = new Alert(Alert.AlertType.ERROR);
				alerta.setContentText("Por favor, escolha uma Clientes na tabela!");
				alerta.show();
			}
		}

		@FXML
		void OnClickIncluir(ActionEvent event) {
			Cliente Clientes = new Cliente();

			boolean btnConfirmarClic = this.onShowTelaClientesEditar(Clientes, listaClientesController.CLIENTES_INCLUIR);

			if (btnConfirmarClic) {
				this.getClientesDAO().save(Clientes);
				this.carregarTableViewClientes();
			}
		}

		public ClienteDAO getClientesDAO() {
			return clienteDAO;
		}

		public void setClientesDAO(ClienteDAO ClientesDAO) {
			this.clienteDAO = ClientesDAO;
		}

		public List<Cliente> getListaClientes() {
			return listaClientes;
		}

		public void setListaClientes(List<Cliente> listaClientes) {
			this.listaClientes = listaClientes;
		}

		public ObservableList<Cliente> getObservableListaClientes() {
			return observableListaClientes;
		}

		public void setObservableListaClientes(ObservableList<Cliente> observableListaClientes) {
			this.observableListaClientes = observableListaClientes;
		}

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			this.setClientesDAO(new ClienteDAO());
			this.carregarTableViewClientes();
			this.selecionarItemTableViewClientes(null);

			this.tbvClientes.getSelectionModel().selectedItemProperty()
					.addListener((observable, oldValue, newValue) -> selecionarItemTableViewClientes((Cliente) newValue));
		}

		public void carregarTableViewClientes() {
			this.columnCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
			this.columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

			this.setListaClientes(this.getClientesDAO().getAll());
			this.setObservableListaClientes(FXCollections.observableArrayList(this.getListaClientes()));
			this.tbvClientes.setItems(this.getObservableListaClientes());
		}

		public void selecionarItemTableViewClientes(Cliente Clientes) {
			if (Clientes != null) {
				this.lblRecebeNome.setText(Clientes.getNome());
				this.lblRecebeFone.setText(Clientes.getFone());
				
				
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

		public boolean onShowTelaClientesEditar(Cliente Clientes, String operacao) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ifsc/tds/julian/kelcia/noemi/view/ClientesEdit.fxml"));
				Parent ClientesEditXML = loader.load();
				
				Stage janelaClientesEditar = new Stage();
				janelaClientesEditar.setTitle("Cadastro de Clientes" + operacao);
				janelaClientesEditar.initModality(Modality.APPLICATION_MODAL);
				janelaClientesEditar.resizableProperty().setValue(Boolean.FALSE);

				Scene ClientesEditLayout = new Scene(ClientesEditXML);
				janelaClientesEditar.setScene(ClientesEditLayout);

				editClientesController editClientes = loader.getController();
				editClientes.setJanelaclienteEdit(janelaClientesEditar);
				editClientes.populaTela(Clientes);

				janelaClientesEditar.showAndWait();

				return editClientes.isOkClick();

			} catch (Exception e) {
				e.printStackTrace();
			}

			return false;
		}

		public List<Cliente> retornaListagemClientes() {
			if (this.getClientesDAO() == null) {
				this.setClientesDAO(new ClienteDAO());
			}
			return this.getClientesDAO().getAll();
		}
}


