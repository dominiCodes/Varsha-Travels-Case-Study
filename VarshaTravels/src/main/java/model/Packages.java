package model;

public class Packages{
	public String package_id;
	public String source_place;
	public String destination_place;
	public int no_of_days;
	double basic_fare;
	public double package_cost;
	public double calculatePackageCost;
	public Packages(String package_id, String source_place, String destination_place, int no_of_days,
			double basic_fare) {
		super();
		this.package_id = package_id;
		this.source_place = source_place;
		this.destination_place = destination_place;
		this.no_of_days = no_of_days;
		this.basic_fare = basic_fare;
	
	}
	public void calculatePackageCost() {
		if(this.no_of_days<=5) {this.package_cost= this.basic_fare*this.no_of_days;
		return;}
		if(this.no_of_days>5 && this.no_of_days<=8) {this.package_cost= this.basic_fare*this.no_of_days*0.97;
		return;}
		if(this.no_of_days>8 && this.no_of_days<=10) {this.package_cost= this.basic_fare*this.no_of_days*0.95;
		return;}
		if(this.no_of_days>5) {this.package_cost= this.basic_fare*this.no_of_days*0.93;
		return;}
		
		this.package_cost*=1.12;
	}

}
