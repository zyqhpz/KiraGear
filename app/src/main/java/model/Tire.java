package model;

public class Tire {
	private double width;
	private double aspectRatio;
	private double diameter;
	private double circumference;

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getAspectRatio() {
		return aspectRatio;
	}

	public void setAspectRatio(double aspectRatio) {
		this.aspectRatio = aspectRatio;
	}

	public double getDiameter() {
		return diameter;
	}

	public void setDiameter(double diameter) {
		this.diameter = diameter;
	}

	public double getCircumference() {

		double diameterMiliMetre = diameter * 25.4;

		double sidewall = width * (aspectRatio / 100);

		double overallDiameter = diameterMiliMetre + (sidewall * 2);

		circumference = Math.PI * overallDiameter;

		// circumference = Math.PI * ((diameter * 25.4) + (width * (aspectRatio/100))) *
		// 0.001;

		circumference = circumference * 0.001;

		// round off to 2 decimal places
		circumference = Math.round(circumference * 100.0) / 100.0;

		return circumference;
	}

	public void setCircumference(double circumference) {
		this.circumference = circumference;
	}

}
