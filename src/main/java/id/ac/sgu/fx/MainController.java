package id.ac.sgu.fx;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.lang.Thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import id.ac.sgu.sensors.Thermometer;
import id.ac.sgu.sensors.Anemometer;
import id.ac.sgu.sensors.Clock;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class MainController implements Initializable {

	private static final Logger LOG = LoggerFactory.getLogger(MainController.class);

	private Random random = new Random();

	@FXML
	private Text demoText;
	
	@FXML
	private Text demoText2;
	
	@FXML
	private Text demoText3;

	@FXML
	private Button demoBtn;
	
	Thermometer thermometer = new Thermometer(20);
	Anemometer anemometer = new Anemometer(10);
	Clock clock = new Clock(0);

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		demoText.setText("Application init");

		demoBtn.setText("Update");
	
	}

	@FXML
	void onOkButtonMouseClick(MouseEvent event) throws Exception {
		
		

		run(thermometer, anemometer, clock);

		/*
		String randomNumber = Integer.toString(random.nextInt(100));
		String randomNumber2 = Integer.toString(random.nextInt(100));
		String randomNumber3 = Integer.toString(random.nextInt(100));

		LOG.info("onOkButtonMouseClick with new random value {} {} {}", randomNumber, randomNumber2, randomNumber3);

		demoText.setText(randomNumber);
		demoText2.setText(randomNumber2);
		demoText3.setText(randomNumber3);
		*/

	}
	
	void run(Thermometer thermo, Anemometer anemo, Clock clock) throws Exception {
		
		while(true) {
			int temp = thermo.getTemp();
			int speed = anemo.getSpeed();
			int time = clock.getTime();
			
			LOG.info("Temperature: {}C, Wind speed: {} km/h, Time: {}", temp, speed, time);
			
			String text1 = Integer.toString(temp);
			String text2 = Integer.toString(speed);
			String text3 = Integer.toString(time);

			updateText(text1, text2, text3);
			
			
			thermo.setTemp(temp + 1);
			anemo.setSpeed(speed + 1);
			clock.setTime(time + 1);
			Thread.sleep(1000);
		}
		
	}
	
	void updateText(String text1, String text2, String text3) {
		demoText.setText(text1);
		demoText2.setText(text2);
		demoText3.setText(text3);
	}

}
