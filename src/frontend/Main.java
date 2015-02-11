package frontend;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.*;

public class Main {

	public static void main(String[] args) throws SQLException {
		Query q = new Query();
		q.makeTable("test4");
		q.setArtikel("test4", "blark");
		q.setArtikel("test4", "blark2");
		
		String[] rs = q.getArtikelen("artikelen", "naam");
		for (String m : rs){
			System.out.println(m);
		}
	}
}
