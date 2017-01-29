/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segp3;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

/**
 *
 * @author Segp-Group 3
 */
public class FXMLDocumentController implements Initializable {



    @FXML
    private BorderPane rootBorderPane;

	@FXML
	private BorderPane borderpane;

	@FXML
	private JFXButton back;

	@FXML
	private JFXButton forward;

	@FXML
	private JFXTextField searchField;

	@FXML
	private JFXButton search;

	@FXML
	private JFXTabPane tabpane;

	@FXML
	private Tab addNewTab;

	@FXML
	private JFXHamburger hamburger;
	
	@FXML
    private JFXButton refresh;
	
	@FXML
    private GridPane navigationBar;

	VBox drawerPane = new VBox();

	WebView browser = new WebView();
	WebEngine webEngine = browser.getEngine();

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		//css attach with Tabpane
		tabpane.getStyleClass().addAll("tab-pane");
		

		// ---------------------webView---------------------------webEngine----------------------------------------------

		// Default url will be google
		webEngine.load("http://www.google.com");
		searchField.setText(webEngine.getLocation());
		borderpane.setCenter(browser);

		// Listener for search button
		search.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
		webEngine.load(searchField.getText());
		
		webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
			@Override
			public void changed(ObservableValue ov, State oldState, State newState) {
				if (newState == Worker.State.SUCCEEDED) {
					searchField.setText(webEngine.getLocation());
					}
				}
			});

		});
		// --------------------------------------UrlField--------------------------Url--GetLocation-------------------

		// listner for search textfield of search button
		searchField.setOnKeyPressed(event -> 
		{
			if (event.getCode() == KeyCode.ENTER) 
			{
				webEngine.load(searchField.getText());
			//calling history function
								
			}
		});
		
		/*
		 * We got this idea from this link Doc
		 * :https://docs.oracle.com/javase/8/javafx/api/index.html?
		 * javafx/scene/web/WebEngine.html We got the conceptual thought
		 * from Stack: this
		 * 
		 * http://stackoverflow.com/questions/32486758/detect-url-
		 * changes-in-javafx-webview The purpose of this is to show the
		 * chaning url
		 */
		
		webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
			@Override
			public void changed(ObservableValue ov, State oldState, State newState) {

				if (newState == Worker.State.SUCCEEDED) {
					//System.out.println(webEngine.getLocation());
					searchField.setText(webEngine.getLocation());
				}

			}
		});

		
		
		/*
		 * bookmarks.setOnAction(new EventHandler<ActionEvent>() {
		 * 
		 * @Override public void handle(ActionEvent event) { // TODO
		 * Auto-generated method stub String url = engine.getLocation();
		 * System.out.println(url); // engine.get // write.println(url); }
		 * 
		 * }); History.setOnAction(new EventHandler<ActionEvent>(){
		 * 
		 * @Override public void handle(ActionEvent event) { // TODO
		 * Auto-generated method stub WebHistory history = engine.getHistory();
		 * ObservableList<Entry> list = history.getEntries(); for(int i=0 ; i<
		 * list.size();i++){ System.out.println(list.get(i)); } } });
		 * 
		 */
		
		
		// --------------------------------------------------------Backward-------------------------------------------
		
		back.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			try {

				WebHistory history = webEngine.getHistory();
				history.go(-1);
			} catch (IndexOutOfBoundsException e1) {
				System.out.println(e1);
			}
		});
		
		// --------------------------------------------------------Forward--------------------------------------------

		forward.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			try {
				WebHistory history = webEngine.getHistory();
				history.go(1);
			} catch (IndexOutOfBoundsException e1) {
				System.out.println(e1);
			}
		});
		
		//--------------------------------------------Refresh------------------------------------------------
		
		refresh.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
			webEngine.load(webEngine.getLocation());
			
		});

		// -------------------------------------------Hamburger----Drawer----Menu-----------------------------------

		//Hamburger ham = new Hamburger();
		//ham.getHamburger(hamburger, borderpane,tabpane);
		
		// --------------------------------------------------------TabPane---------------------------------------------

		//TabPane tabpan_view = new TabPane();
		//tabpan_view.getTabPane(tabpane, addNewTab, navigationBar);
		/**
		 * There is well know error in Tabpane while you will be working with scenebuilder then comment the 
		 * below tabpane! We gone through we severly face this #bug of tabpane many time so becareful! 
		 */
		
		//----------------------just put tabpane in vbox as to add new tab button on click new tab pop up
	
		
			
		// end method
	}
	// end class
}