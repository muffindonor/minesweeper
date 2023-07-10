package mines;

import java.io.IOException;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;



import javafx.scene.layout.HBox;

import javafx.scene.Scene;

import javafx.stage.Stage;

public class MinesFX extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
    public void start(Stage stage) {
        HBox root;

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Screen.fxml"));
            root = loader.load();
            stage.setTitle("The Amazing Mines Sweeper");

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}



