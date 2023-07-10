package mines;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class MyController {
	
	private void createButtons(int width, int height) {
	    fieldMap.getChildren().clear(); // Clear existing buttons

	    fieldMap.getColumnConstraints().clear();
	    fieldMap.getRowConstraints().clear();

	    for (int col = 0; col < width; col++) {
	        ColumnConstraints columnConstraints = new ColumnConstraints();
	        columnConstraints.setPercentWidth(100.0 / width);
	        fieldMap.getColumnConstraints().add(columnConstraints);
	    }

	    for (int row = 0; row < height; row++) {
	        RowConstraints rowConstraints = new RowConstraints();
	        rowConstraints.setPercentHeight(100.0 / height);
	        fieldMap.getRowConstraints().add(rowConstraints);

	        for (int col = 0; col < width; col++) {
	            Button button = new Button();
	            // Customize button appearance and behavior if needed

	            fieldMap.add(button, col, row); // Add button to the fieldMap GridPane
	        }
	    }
	}
	


    @FXML
    private TextArea heightText;

    @FXML
    private TextArea minesText;

    @FXML
    private Button resetButton;

    @FXML
    private TextArea widthText;
    
    @FXML
    private GridPane fieldMap;

    @FXML
    void resetPressed(ActionEvent event) {
    	int width = Integer.parseInt(widthText.getText());
        int height = Integer.parseInt(heightText.getText());
        createButtons(width, height);
        

    }

}
