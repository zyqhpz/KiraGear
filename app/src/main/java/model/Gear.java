package model;

import java.util.HashMap;
import java.util.List;

public class Gear {
	private double ratio;
	private double speed;
	private List<Double> speeds;
	private HashMap<Integer, Double> rpmSpeeds;

	public double getRatio() {
		return ratio;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public List<Double> getSpeeds() {
		return speeds;
	}

	public void setSpeeds(List<Double> speeds) {
		this.speeds = speeds;
	}

	public void setRpmSpeeds(HashMap<Integer, Double> rpmSpeeds) {
		this.rpmSpeeds = rpmSpeeds;
	}

	public HashMap<Integer, Double> getrRpmSpeeds() {
		return rpmSpeeds;
	}
}
