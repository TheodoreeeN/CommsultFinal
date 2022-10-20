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
import id.ac.sgu.sensors.Sensor;
import id.ac.sgu.actors.AirConditioner;
import id.ac.sgu.actors.Blinds;
import id.ac.sgu.actors.Actor;

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
	private Text airCon;
	
	@FXML
	private Text blinds;

	@FXML
	private Button runBtn;
	
	@FXML
	private Button stopBtn;
	
	Sensor thermometer = new Thermometer(25);
	Sensor anemometer = new Anemometer(30);
	Sensor clock = new Clock(1);
	Actor airConditioner = new AirConditioner("OFF");
	Actor blind = new Blinds("CLOSED");
	String tempVal, windVal, timeVal, acVal, blindsVal;
	
	Thread t1; 

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		runBtn.setText("Start");
		stopBtn.setText("Stop");
		stopBtn.setDisable(true);
	}

	@FXML
	void onStartButtonMouseClick(MouseEvent event) throws Exception {
		runBtn.setDisable(true);
		stopBtn.setDisable(false);
		t1 = new Thread(() -> {
	        while(true)
	        {
	            try
	            {
	            	run(thermometer, anemometer, clock, airConditioner, blind);
	            }
	            catch(Exception e)
	            {
	                Platform.runLater(() -> updateText(tempVal, windVal, timeVal, acVal, blindsVal));
	            }
	        }
	    });
		t1.start();

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
	
	@FXML
	void onStopButtonMouseClick(MouseEvent event) throws Exception {
		
		t1.stop();
		runBtn.setDisable(false);
		stopBtn.setDisable(true);

	}
	
	void run(Sensor thermo, Sensor anemo, Sensor clock, Actor airCon, Actor blind) throws Exception {
		
		
			int temp = thermo.getState();
			int speed = anemo.getState();
			int time = clock.getState();
			
			tempVal = Integer.toString(temp);
			windVal = Integer.toString(speed);
			timeVal = Integer.toString(time);
			acVal = airCon.getState();
			blindsVal = blind.getState();
			
			
			if (temp >= 30 || time >= 21 || time < 3) {
				acVal = "ON";
			} else {
				acVal = "OFF";
			}
			
			if (speed >= 40 || time >= 18 || time < 6) {
				blindsVal = "CLOSED";
			} else {
				blindsVal = "OPENED";
			}
			
			if(time<10) {
				timeVal = "0" + timeVal + ":00";
			}else { timeVal = timeVal + ":00";}
			
			updateText(tempVal, windVal, timeVal, acVal, blindsVal);
			
			int rand = random.nextInt(6) - 2; //Random value to add temperature
			int rand2 = random.nextInt(6) - 2; //Random value to add wind speed
			
			if ((temp + rand) < -5) {
				rand = 0;
				temp += 3;
			} else if ((temp + rand) > 40) {
				rand = 0;
				temp -= 3;
			}
			
			if ((speed + rand2) < 0) {
				rand2 = 0;
				speed += 3;
			} else if ((speed + rand2) > 50) {
				rand2 = 0;
				speed -= 3;
			}
			
			thermo.setState(temp + rand);
			anemo.setState(speed + rand2);
			if (time == 24) {
				time = 0;
			}
			clock.setState(time + 1);
			airCon.setState(acVal);
			blind.setState(blindsVal);
			
			LOG.info("Temperature: {}C, Wind speed: {} km/h, Time: {}, Air Conditioner : {}, Blinds: {}", temp, speed, time, acVal, blindsVal);
			Thread.sleep(2000);

	}
	
	void updateText(String tempVal, String windVal, String timeVal, String acVal, String blindsVal) {
		temp.setText(tempVal);
		wind.setText(windVal);
		time.setText(timeVal);
		airCon.setText(acVal);
		blinds.setText(blindsVal);
	}

}
