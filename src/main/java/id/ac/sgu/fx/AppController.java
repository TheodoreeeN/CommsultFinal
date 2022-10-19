package id.ac.sgu.fx;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class AppController implements Initializable {

	private static final Logger LOG = LoggerFactory.getLogger(AppController.class);

	private Random random = new Random();

	@FXML
	private Text demoText;

	@FXML
	private Button demoBtn;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		demoText.setText("Application init");

		demoBtn.setText("Update");

	}

	@FXML
	void onOkButtonMouseClick(MouseEvent event) {

		String randomNumber = Integer.toString(random.nextInt(100));

		LOG.info("onOkButtonMouseClick with new random value {}", randomNumber);

		demoText.setText(randomNumber);

	}

}
