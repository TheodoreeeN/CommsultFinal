package id.ac.sgu.actors;

public abstract class Actor implements IActor {
	
	private String state;
	
	public Actor(String state) {
		this.state = state;
	}
	@Override
	public String getState() {
		return state;
	}

	@Override
	public void setState(String state) {
		this.state = state;
	}
}
