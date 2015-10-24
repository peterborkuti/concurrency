package common;


public class NamedSleeper extends Sleeper {
	protected String name;

	public NamedSleeper(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		Util.writeAndSleep(name, messages, 1000, 2000);
	}	
}