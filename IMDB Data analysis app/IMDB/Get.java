package hw3;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import hw3.Res;

public class Get {
	
	private static Connection connec;
	private static DataBaseConnect database;
	private static Statement st, stmt2, stmt3;
	Get() {
		try{
		database=new DataBaseConnect();
		connec=database.getConnection();
		st = connec.createStatement();
		stmt2 = connec.createStatement();
		stmt3 = connec.createStatement();
		}
		catch(SQLException e){
			
		}
		finally{
			if(st!=null|| stmt2!=null||stmt3!=null){
				database.closeConnection();
			}
		}
		}
		
	public ArrayList<String> getGenre(){
		ArrayList<String> genres = new ArrayList<String>();
		try{
			System.out.println("Selecting genre from the table");
			String genre;
			ResultSet rs = st.executeQuery("SELECT DISTINCT GENRE_NAME FROM MOVIE_GENRE ORDER BY GENRE_NAME");
			while(rs.next()){
				genre = rs.getString("GENRE_NAME");
				genres.add(genre);	
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return genres;
	}
	public ArrayList<String> getCountries(ArrayList<String> genre, String attr){
	    ArrayList<String> countries = new ArrayList<String>();
	    if(genre.size()==0)
			return countries;
	    String option;
	    if(attr.equals("AND"))
	    	option="INTERSECT";
	    else
	    	option="UNION";
		try{
			System.out.println("Selecting Countries from the table");
			String country;
			int i=0;
			String query="SELECT DISTINCT COUNTRY FROM MOVIE_COUNTRY C, MOVIE_GENRE G WHERE C.MOVIEID=G.MOVIEID AND G.GENRE_NAME='"+genre.get(i)+"'";
			
			for(i=i+1;i<genre.size();i++){
				query+=" "+option+" SELECT DISTINCT COUNTRY FROM MOVIE_COUNTRY C, MOVIE_GENRE G WHERE C.MOVIEID=G.MOVIEID AND G.GENRE_NAME='"+genre.get(i)+"'";
;
			}
			System.out.println(query);
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				country = rs.getString("COUNTRY");
				countries.add(country);	
			}
			
			System.out.println(countries.size());
		}
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		return countries;
	}
	public String [] getActor(){
		String[] actors= new String[30];
		try{
			ResultSet rs = st.executeQuery("SELECT DISTINCT ACTOR_NAME FROM MOVIE_ACTORS ORDER BY ACTOR_NAME");
			int i=0;
			while(rs.next()&& i<30){
				actors[i] = rs.getString("ACTOR_NAME");
				i++;
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return actors;
	}
	
	public String [] getDirector(){
		String[] director= new String[30];
		try{
			ResultSet rs = st.executeQuery("SELECT DISTINCT DIRECTOR_NAME FROM MOVIE_DIRECTORS ORDER BY DIRECTOR_NAME");
			int i=0;
			while(rs.next()&& i<30){
				director[i] = rs.getString("DIRECTOR_NAME");
				i++;
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(director[1]);
		return director;
	}

	public ArrayList<Res> gRes(String query){
		 ArrayList<Res> result= new ArrayList<>();
		 try{
				ResultSet rs = st.executeQuery(query);
				while(rs.next()){
					Res tmp= new Res();
					tmp.title=rs.getString("TITLE");
					tmp.year=rs.getInt("YEAR");
					tmp.country=rs.getString("COUNTRY");
					tmp.reviews=rs.getInt("REVIEWS");
					tmp.rating=rs.getFloat("AVGRATING");
					try{
						System.out.println("Selecting genre");
						ResultSet rs2 = stmt2.executeQuery("SELECT GENRE_NAME FROM MOVIE_GENRE G, MOVIES M WHERE M.MOVIEID=G.MOVIEID AND M.TITLE='"+tmp.title+"'");
						while(rs2.next()){
							String name = rs2.getString("GENRE_NAME");
							tmp.genN.add(name);
						}
						
					}
					catch (SQLException e) {
						e.printStackTrace();
					}
					try{
						ResultSet rs3 = stmt3.executeQuery("SELECT TAG_VALUE FROM TAGS T, MOVIES M, MOVIE_TAG MT WHERE M.MOVIEID=MT.MOVIEID AND T.TAG_ID=MT.TAG_ID AND M.TITLE='"+tmp.title+"'");
						while(rs3.next()){
							String name = rs3.getString("TAG_VALUE");
							tmp.tag.add(name);
						}
						
					}
					catch (SQLException e) {
						e.printStackTrace();
					}
					result.add(tmp);
				}
				
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		 return result;
	}
}


