/*package id.ac.sgu.fx;

import java.lang.Thread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import id.ac.sgu.sensors.Anemometer;
import id.ac.sgu.sensors.Clock;
import id.ac.sgu.sensors.Thermometer;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class RunThread implements Runnable {
	private boolean exit;
	Thread t;
	Thermometer thermo;
	Anemometer anemo;
	Clock clock;
	String text1, text2, text3;
	Logger LOG;
	
	@FXML
	private Text temp;
	
	@FXML
	private Text wind;
	
	@FXML
	private Text time;
	
	RunThread(Thermometer thermo, Anemometer anemo, Clock clock, Logger LOG) {
		this.thermo = thermo;
		this.anemo = anemo;
		this.clock = clock;
		this.LOG = LOG;
		exit = false;
		t = new Thread(this, "T1");
		t.start();
	}
	
	public void run() {
		int temp = thermo.getTemp();
		int speed = anemo.getSpeed();
		int time = clock.getTime();
		
		
		
		LOG.info("Temperature: {}C, Wind speed: {} km/h, Time: {}", temp, speed, time);
		
		text1 = Integer.toString(temp);
		text2 = Integer.toString(speed);
		text3 = Integer.toString(time);

		
		
		thermo.setTemp(temp + 1);
		anemo.setSpeed(speed + 1);
		clock.setTime(time + 1);
    }
	
	public void stop() {
		exit = true;
	}
	
	
}*/
