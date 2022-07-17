package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Tuning {
	private List<Gear> gears = new ArrayList<Gear>();
	private double finalDrive;
	private Tire tire;

	public List<Gear> getGears() {
		return gears;
	}

	public void setGears(List<Gear> gears) {
		this.gears = gears;
	}

	public double getFinalDrive() {
		return finalDrive;
	}

	public void setFinalDrive(double finalDrive) {
		this.finalDrive = finalDrive;
	}

	public Tire getTire() {
		return tire;
	}

	public void setTire(Tire tire) {
		this.tire = tire;
	}

	public void calculateSpeed(int redline, double finalDrive) {
		// double speed = redline + (1/gear.ratio)
		double speed;
		for (Gear gear : gears) {
			speed = redline * (1 / gear.getRatio() * (1 / finalDrive) * tire.getCircumference() * 0.001 * 60);

			// round to 2 decimal places
			speed = Math.round(speed * 100.0) / 100.0;
			gear.setSpeed(speed);
			// speed += gear.getRatio() * gear.getSpeed();
		}
		setGears(gears);
		// return speed;
	}

	public void calculateSpeeds(double finalDrive) {

//		int[] rpm = { 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000, 11000, 12000, 13000, 14000, 15000 };
		int[] rpm = {0, 1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000};

		for (Gear gear : gears) {
			List<Double> speeds = new ArrayList<Double>();
			HashMap<Integer, Double> rpmSpeeds = new HashMap<>();
			double speed;
			for (int i = 0; i < rpm.length; i++) {
				speed = rpm[i] * ((1 / gear.getRatio()) * (1 / finalDrive) * tire.getCircumference() * 0.001 * 60);

				// round to 2 decimal places
				speed = Math.round(speed * 100.0) / 100.0;
				speeds.add(speed);
				rpmSpeeds.put(rpm[i], speed);
			}
			gear.setSpeeds(speeds);
			gear.setRpmSpeeds(rpmSpeeds);
		}

		setGears(gears);

		// return
	}
}
