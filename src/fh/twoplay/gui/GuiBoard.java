package fh.twoplay.gui;

import java.awt.MenuBar;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.*;

/**
 * @author Natasza Szczypien
 *
 */
public class GuiBoard extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("Gui_Board.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add("/fh/twoplay/gui/stylesheet.css");
		
		// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();	
	}
}
