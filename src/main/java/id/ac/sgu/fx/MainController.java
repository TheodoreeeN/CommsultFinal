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
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class MainController implements Initializable {

	private static final Logger LOG = LoggerFactory.getLogger(MainController.class);

	private Random random = new Random();

	@FXML
	private Text temp;
	
	@FXML
	private Text wind;
	
	@FXML
	private Text time;

	@FXML
	private Button runBtn;
	
	Thermometer thermometer = new Thermometer(20);
	Anemometer anemometer = new Anemometer(10);
	Clock clock = new Clock(0);
	String text1, text2, text3;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {


		runBtn.setText("Run");
	
	}

	@FXML
	void onOkButtonMouseClick(MouseEvent event) throws Exception {
		
		new Thread(() -> {
	        while(true)
	        {
	            try
	            {
	            	run(thermometer, anemometer, clock);
	            }
	            catch(Exception e)
	            {
	                Platform.runLater(() -> updateText(text1, text2, text3));
	            }
	        }
	    }).start();

		//run(thermometer, anemometer, clock);

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
		
		
			int temp = thermo.getTemp();
			int speed = anemo.getSpeed();
			int time = clock.getTime();
			
			LOG.info("Temperature: {}C, Wind speed: {} km/h, Time: {}", temp, speed, time);
			
			text1 = Integer.toString(temp);
			text2 = Integer.toString(speed);
			text3 = Integer.toString(time);

			updateText(text1, text2, text3);
			
			
			thermo.setTemp(temp + 1);
			anemo.setSpeed(speed + 1);
			clock.setTime(time + 1);
			Thread.sleep(1000);

		
		
	}
	
	void updateText(String text1, String text2, String text3) {
		temp.setText(text1);
		wind.setText(text2);
		time.setText(text3);
	}

}
