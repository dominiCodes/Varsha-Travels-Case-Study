package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Packages;
import utility.JdbcUtility;

class InvalidIdException extends Exception {
	InvalidIdException() {
		super("Invalid Id passed");
	}
}

public class TravelAgencyImplemetation {

	public List<Packages> generatePackageCost() throws InvalidIdException, SQLException {
		List<Packages> list = new ArrayList();
		int i = 1;
		Scanner sc = null;
		try {
			Connection con=JdbcUtility.makeConnection();
			
			
				PreparedStatement ps=con.prepareStatement("insert into package_details2 values(?,?,?,?,?)");
				
				PreparedStatement prs=con.prepareStatement("select * from package_details2 where package_id=?");
				
			File file = new File("C://Users/ANOORMOH/Desktop");
			sc = new Scanner(file);
			while (sc.hasNextLine()) {

				String st = sc.nextLine();
				st = st.trim();
				String arr[] = st.split(",");

				String id = arr[0].trim();
				if (validate(id)) {
					String source_place = arr[1].trim();
					String destination_place = arr[2].trim();
					int days = Integer.parseInt(arr[3].trim());
					double basic_fare = Double.parseDouble(arr[4].trim());
					Packages P1 = new Packages(id, source_place, destination_place,days,
							basic_fare);
					P1.calculatePackageCost();
					list.add(P1);
					ps.setString(1, id);
					ps.setString(2,source_place);
					ps.setString(3,destination_place);
					ps.setInt(4,days);
					ps.setDouble(5,P1.package_cost);
					prs.setString(1, id);
					ResultSet rs=prs.executeQuery();
					if(!rs.next())ps.executeUpdate();
					i++;
				} else {
					throw new InvalidIdException();
				}
			}

		} catch (FileNotFoundException ex) {
			System.out.println("Problem in line number - " + i);
			System.out.println(ex.getMessage());
		} 

		return list;
	}

	public boolean validate(String packageId) {
		String regex = "[0-9]{3}[/]{1}[A-Z]{3}";
		return packageId.matches(regex);
	}

	public List<Packages> findPackagesWithMinimumNumberOfDays() {

		List<Packages> list = new ArrayList();
		try {
			Connection connection = JdbcUtility.makeConnection();

			PreparedStatement ps = connection.prepareStatement("select * from package_details2 where no_of_days=(select MIN(no_of_days) from package_details2)");
            
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String id = rs.getString("package_id");
				String src = rs.getString("source_place");
				String dstn = rs.getString("destination_place");
				int days = rs.getInt("no_of_days");
				double cost = rs.getDouble("package_cost");

				Packages v1 = new Packages(id, src, dstn, days, 0);
				v1.package_cost = cost;
				list.add(v1);

			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage()+ "in findPackagesWithMinimumNumberOfDays");
		}
		return list;

	}
	 
	 
}
