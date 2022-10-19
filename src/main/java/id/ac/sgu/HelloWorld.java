package id.ac.sgu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloWorld extends Application {

	private static final Logger LOG = LoggerFactory.getLogger(HelloWorld.class);

	public static void main(String[] args) {

		LOG.trace("TRACE");
		LOG.debug("DEBUG");
		LOG.info("INFO");
		LOG.warn("WARN");
		LOG.error("ERROR");

		launch();

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		LOG.info("starting application");

		Parent root = FXMLLoader.load(getClass().getResource("App.fxml"));

		primaryStage.setTitle("HomeControlApp");
		primaryStage.setScene(new Scene(root));

		primaryStage.getScene().getStylesheets().add("AppStyle.css");
		primaryStage.setOnCloseRequest(e -> Platform.exit());

		primaryStage.show();

	}

	@Override
	public void stop() throws Exception {

		LOG.info("stopping application");

	}
}
