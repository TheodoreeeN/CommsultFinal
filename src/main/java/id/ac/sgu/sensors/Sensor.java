package id.ac.sgu.sensors;

public abstract class Sensor implements ISensor {
	
	private int state;
	
	public Sensor(int state) {
		this.state = state;
	}
	@Override
	public int getState() {
		return state;
	}

	@Override
	public void setState(int state) {
		this.state = state;
	}
	
}
