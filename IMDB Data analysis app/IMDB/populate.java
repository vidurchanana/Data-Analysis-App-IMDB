package hw3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

class Movies {
	int id;
	String title;
	int year;
	float ACRating;
	float TCRating;
	float ARating;
	int ACNumReviews;
	int TCNumReviews;
	int ANumReviews;
	Movies(int id, String title, int year, float ACRating, float TCRating, float ARating, int ACNumReviews, int TCNumReviews, int ANumReviews){
		this.id=id;
		this.title=title;
		this.year=year;
		this.ACRating=ACRating;
		this.TCRating=TCRating;
		this.ARating=ARating;
		this.ACNumReviews=ACNumReviews;
		this.TCNumReviews=TCNumReviews;
		this.ANumReviews=ANumReviews;
		}
	}

class Movie_actors {
	int movieid;
	String actorid;
	String actorName;
	int ranking; //might change the data type
	Movie_actors (int movie_Id, String actorId, String actorName, int ranking){
		this.movieid=movie_Id;
		this.actorid=actorId;
		this.actorName=actorName;
		this.ranking=ranking;
		}		
	}
class Movie_country {
	int movieId;
	String country;
	
	Movie_country(int movieId, String country) {
		this.movieId=movieId;
		this.country=country;
		}
	}
class Movie_Directors {
	int movieId;
	String directorId;
	String directorName;
	Movie_Directors (int movie_Id, String directorId, String directorName){
		this.movieId=movie_Id;
		this.directorId=directorId;
		this.directorName=directorName;
		}
	}
class Movie_genre {
	int movieId;
	String genre;
	Movie_genre(int movieId, String genre)	{
		this.movieId=movieId;
		this.genre=genre;
		}
	}
class Tag {
	int tagId;
	String tagText;
	Tag(int tagId, String tagText){
		this.tagId=tagId;
		this.tagText=tagText;
		}
	}
class Movie_tag {
	int movieId;
	int tagId;
	int tagWeight;
	Movie_tag(int movieId, int tagId, int tagWeight){
			this.movieId=movieId;
			this.tagId=tagId;
			this.tagWeight=tagWeight;
		}
	}
class User_ratedMovies {
	int movieId;
	int rating;
	int userId;
	String timestamp; 
	User_ratedMovies(int userId, int movieId, int rating, String timestamp){
			this.movieId=movieId;
			this.rating=rating;
			this.userId=userId;
			this.timestamp=timestamp;
		}
	}
class User_taggedmovies {
	int movieId;
	int tagId;
	int userId;
	String timestamp;
	User_taggedmovies(int userId, int movieId, int tagId, String timestamp){
		this.movieId=movieId;
		this.tagId=tagId;
		this.userId=userId;
		this.timestamp=timestamp;
		}
	}

	public class populate {
	
	public static void main(String[] args){	
		DataBaseConnect db=new DataBaseConnect();
		Connection connection=db.getConnection();
		String currentLine;
		BufferedReader br = null;
		ArrayList<Movies> movies = new ArrayList<>();
	//movies	
		try {
			 br = new BufferedReader(new FileReader("src/hw3/"+args[0]));
			} 
			catch (FileNotFoundException e) {
			e.printStackTrace();
			}
		try{
			while ((currentLine = br.readLine()) != null) 
			{
				try{
					String ar[] = currentLine.split("\\t");
						if(ar.length>19){
						int id =Integer.parseInt(ar[0]);
						String title =ar[1];
						int movieyear =Integer.parseInt(ar[5]);
						float AllCriticRating =Float.parseFloat(ar[7]);
						int NoAllCriticRating =Integer.parseInt(ar[8]);
						float TotalCriticRating =Float.parseFloat(ar[12]);	
						int NoTotalCriticRating =Integer.parseInt(ar[13]);
						float AudienceRating =Float.parseFloat(ar[17]);
						int NoAudienceRating =Integer.parseInt(ar[18]);
						Movies temp= new Movies (id, title, movieyear, AllCriticRating, TotalCriticRating, AudienceRating, NoAllCriticRating, NoTotalCriticRating, NoAudienceRating);
						movies.add(temp);
					}
				}
				catch(NumberFormatException E)
				{
					continue;
				}
			}}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
				try {
					Statement stmt = connection.createStatement();
					String sql = "DELETE FROM MOVIES";
					stmt.executeUpdate(sql);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				try {
					String sql =	"INSERT INTO MOVIES\n" +
									"(MOVIEID, TITLE, YEAR, AC_RATING, TC_RATING,A_RATING, AC_NUM_REVIEWS,TC_NUM_REVIEWS,A_NUM_REVIEWS)\n" +
									"VALUES (?,?,?,?,?,?,?,?,?)";
					PreparedStatement pstmt = connection.prepareStatement(sql);
					for (Movies m : movies) {
						pstmt.setInt(1, m.id);
						pstmt.setString(2, m.title);
						pstmt.setInt(3, m.year);
						pstmt.setFloat(4, m.ACRating);
						pstmt.setFloat(5, m.TCRating);
						pstmt.setFloat(6, m.ARating);
						pstmt.setInt(7, m.ACNumReviews);
						pstmt.setInt(8, m.TCNumReviews);
						pstmt.setInt(9, m.ANumReviews);
						pstmt.executeQuery();
					}
					System.out.println(" movies data inserted");




				} catch (SQLException e) {
					e.printStackTrace();
				}
	//movie_actors
	ArrayList<Movie_actors> movieActors = new ArrayList<>();
	try {
		 br = new BufferedReader(new FileReader("src/hw3/"+args[1]));
		} 
		catch (FileNotFoundException e) {
		e.printStackTrace();
		}
	try{
		while ((currentLine = br.readLine()) != null) 
		{
			try{
				String ar[] = currentLine.split("\\t");
				if(ar.length>3){
				int movieID=Integer.parseInt (ar[0]);
				String actorID=ar[1];
				String actorName=ar[2];
				int ranking=Integer.parseInt (ar[3]);
				Movie_actors temp= new Movie_actors(movieID,actorID,actorName,ranking);
				movieActors.add(temp);
				}
			}
			catch(NumberFormatException E)
			{
				continue;




			}
		}}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			try {
				Statement stmt = connection.createStatement();
				String sql = "DELETE FROM MOVIE_ACTORS";
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				System.out.println("Enter Movie actors");
				String sql =	"INSERT INTO MOVIE_ACTORS\n" +
								"(MOVIEID, ACTOR_ID, ACTOR_NAME, RANKING)\n" +
								"VALUES (?,?,?,?)";
				PreparedStatement pstmt = connection.prepareStatement(sql);
				for (Movie_actors m : movieActors) {
					pstmt.setInt(1, m.movieid);
					pstmt.setString(2, m.actorid);
					pstmt.setString(3, m.actorName);
					pstmt.setInt(4, m.ranking);
					pstmt.executeQuery();
				}
				System.out.println("actors data inserted!");




			} catch (SQLException e) {
				e.printStackTrace();
			}
			// movie_countries
			ArrayList<Movie_country> movieCountry = new ArrayList<>();




			try {
				 br = new BufferedReader(new FileReader("src/hw3/"+args[2]));
				} 
				catch (FileNotFoundException e) {
				e.printStackTrace();
				}
			try{
			while ((currentLine = br.readLine()) != null) 
			{
				try{
					String ar[] = currentLine.split("\\t");
					if(ar.length>1){
					int movieID=Integer.parseInt (ar[0]);
					String country=ar[1];
					Movie_country temp= new Movie_country(movieID,country);
					movieCountry.add(temp);
					}
				}
				
				catch(NumberFormatException E)
				{
					continue;




				}
			}}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			try {
				Statement stmt = connection.createStatement();
				String sql = "DELETE FROM MOVIE_COUNTRY";
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				String sql =	"INSERT INTO MOVIE_COUNTRY\n" +
								"(MOVIEID, COUNTRY)\n" +
								"VALUES (?, ?)";
				PreparedStatement pstmt = connection.prepareStatement(sql);
				for (Movie_country m : movieCountry) {
					pstmt.setInt(1, m.movieId);
					pstmt.setString(2, m.country);
					pstmt.executeQuery();
				}
				System.out.println("countries data inserted");




			} catch (SQLException e) {
				e.printStackTrace();
			}
						
	//movie_directors
	ArrayList<Movie_Directors> movieDirectors = new ArrayList<>();
	try {
		 br = new BufferedReader(new FileReader("src/hw3/"+args[3]));
		} 
		catch (FileNotFoundException e) {
		e.printStackTrace();
		}
	try{
		while ((currentLine = br.readLine()) != null) 
		{
			try{
				String ar[] = currentLine.split("\\t");
				if(ar.length>2){
				int movieId=Integer.parseInt (ar[0]);
				String directorId=ar[1];
				String directorName=ar[2];
				Movie_Directors temp= new Movie_Directors(movieId,directorId,directorName);
				movieDirectors.add(temp);
				}
			}
			catch(NumberFormatException E)
			{
				continue;




			}
		}}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			try {
				Statement stmt = connection.createStatement();
				String sql = "DELETE FROM MOVIE_DIRECTORS";
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				System.out.println("movie directors - failed clearing!");
				e.printStackTrace();
			}
			
			try {
				String sql =	"INSERT INTO MOVIE_DIRECTORS\n" +
								"(MOVIEID, DIRECTOR_ID, DIRECTOR_NAME)\n" +
								"VALUES (?,?,?)";
				PreparedStatement pstmt = connection.prepareStatement(sql);
				for (Movie_Directors m : movieDirectors) {
					pstmt.setInt(1, m.movieId);
					pstmt.setString(2, m.directorId);
					pstmt.setString(3, m.directorName);
					pstmt.executeQuery();
				}
				System.out.println("directors data inserted");




			} catch (SQLException e) {
				e.printStackTrace();
			}	
	// movie_genres
	ArrayList<Movie_genre> movieGenre = new ArrayList<>();
	try {
		 br = new BufferedReader(new FileReader("src/hw3/"+args[4]));
		} 
		catch (FileNotFoundException e) {
		e.printStackTrace();
		}
	try{
		while ((currentLine = br.readLine()) != null) 
		{
			try{
				String ar[] = currentLine.split("\\t");
				if(ar.length>1){
				int movieID=Integer.parseInt (ar[0]);
				String genre=ar[1];
				Movie_genre temp= new Movie_genre(movieID,genre);
				movieGenre.add(temp);
				}
			}
			catch(NumberFormatException E)
			{
				continue;




			}
		}}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			try {
				Statement stmt = connection.createStatement();
				String sql = "DELETE FROM MOVIE_GENRE";
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				String sql =	"INSERT INTO MOVIE_GENRE\n" +
								"(MOVIEID, GENRE_NAME)\n" +
								"VALUES (?,?)";
				PreparedStatement pstmt = connection.prepareStatement(sql);
				for (Movie_genre m : movieGenre) {
					pstmt.setInt(1, m.movieId);
					pstmt.setString(2, m.genre);
					pstmt.executeQuery();
				}
				System.out.println("genre data inserted");




			} catch (SQLException e) {
				e.printStackTrace();
			}	
			
			//tag
			ArrayList<Tag> tag = new ArrayList<>();
			try {
				 br = new BufferedReader(new FileReader("src/hw3/"+args[5]));
				} 
				catch (FileNotFoundException e) {
				e.printStackTrace();
				}
			try{
				while ((currentLine = br.readLine()) != null) 
				{
					try{
						String ar[] = currentLine.split("\\t");
						if(ar.length>1){
						int tagID =Integer.parseInt(ar[0]);
						String tagText = ar[1];
						Tag temp= new Tag (tagID, tagText);
						tag.add(temp);
						}
					}
					catch(NumberFormatException E)
					{
						continue;




					}
				}}
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					}
					try {
						Statement stmt = connection.createStatement();
						String sql = "DELETE FROM TAGS";
						stmt.executeUpdate(sql);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					try {
						String sql =	"INSERT INTO TAGS\n" +
										"(TAG_ID, TAG_VALUE)\n" +
										"VALUES (?,?)";
						PreparedStatement pstmt = connection.prepareStatement(sql);
						for (Tag m : tag) {
							pstmt.setInt(1, m.tagId);
							pstmt.setString(2, m.tagText);
							pstmt.executeQuery();
						}
						System.out.println(" tag data inserted");




					} catch (SQLException e) {
						e.printStackTrace();
					}
			// movie_tag
			ArrayList<Movie_tag> movietag = new ArrayList<>();
			try {
				 br = new BufferedReader(new FileReader("src/hw3/"+args[6]));
				} 
				catch (FileNotFoundException e) {
				e.printStackTrace();
				}
			try{
				while ((currentLine = br.readLine()) != null) 
				{
					try{
						String ar[] = currentLine.split("\\t");
						if(ar.length>1){
						int movieID=Integer.parseInt (ar[0]);
						int tagID =Integer.parseInt(ar[1]);
						int tagWeight =Integer.parseInt(ar[2]);
						Movie_tag temp= new Movie_tag(movieID,tagID, tagWeight);
						movietag.add(temp);
						}
					}
					catch(NumberFormatException E)
					{
						continue;




					}
				}}
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					}
					try {
						Statement stmt = connection.createStatement();
						String sql = "DELETE FROM MOVIE_TAG";
						stmt.executeUpdate(sql);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					try {
						String sql =	"INSERT INTO MOVIE_TAG\n" +
										"(MOVIEID, TAG_ID, TAG_WEIGHT)\n" +
										"VALUES (?,?,?)";
						PreparedStatement pstmt = connection.prepareStatement(sql);
						for (Movie_tag m : movietag) {
							pstmt.setInt(1, m.movieId);
							pstmt.setInt(2, m.tagId);
							pstmt.setInt(3, m.tagWeight);
							pstmt.executeQuery();
						}
						System.out.println("movie tag data inserted");




					} catch (SQLException e) {
						e.printStackTrace();
					}	
			


					//user rating
					ArrayList<User_ratedMovies> userRating = new ArrayList<>();
					try {
						 br = new BufferedReader(new FileReader("src/hw3/"+args[7]));
						} 
						catch (FileNotFoundException e) {
						e.printStackTrace();
						}
					try{
						while ((currentLine = br.readLine()) != null) 
						{
							try{
								String ar[] = currentLine.split("\\t");
								if(ar.length>3){
								int userID =Integer.parseInt(ar[0]);
								int movieID =Integer.parseInt(ar[1]);
								int rating =Integer.parseInt(ar[2]);
								String timestamp =ar[3];
								User_ratedMovies temp= new User_ratedMovies (userID, movieID,rating, timestamp );
								userRating.add(temp);
								}
							}
							catch(NumberFormatException E)
							{
								continue;




							}
						}}
						catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							}
							try {
								Statement stmt = connection.createStatement();
								String sql = "DELETE FROM USER_RATING";
								stmt.executeUpdate(sql);
							} catch (SQLException e) {
								e.printStackTrace();
							}
							
							try {
								String sql =	"INSERT INTO USER_RATING\n" +
												"(USER_ID, MOVIEID, RATING, TIMESTAMP)\n" +
												"VALUES (?,?,?,?)";
								PreparedStatement pstmt = connection.prepareStatement(sql);
								for (User_ratedMovies m : userRating) {
									pstmt.setInt(1, m.userId);
									pstmt.setInt(2, m.movieId);
									pstmt.setInt(3, m.rating);
									pstmt.setNString(4, m.timestamp);
									pstmt.executeQuery();
								}
								System.out.println(" user rating data inserted");




							} catch (SQLException e) {
								e.printStackTrace();
							}
									
			
					
			
							//user tag		
				ArrayList<User_taggedmovies> userTag = new ArrayList<>();
				try {
					 br = new BufferedReader(new FileReader("src/hw3/"+args[8]));
					} 
					catch (FileNotFoundException e) {
					e.printStackTrace();
					}
				try{
					while ((currentLine = br.readLine()) != null) 
					{
						try{
							String ar[] = currentLine.split("\\t");
							if(ar.length>3){
							int userId =Integer.parseInt(ar[0]);
							int movieId =Integer.parseInt(ar[1]);
							int tagId =Integer.parseInt(ar[2]);
							String timestamp =ar[3];
							User_taggedmovies temp= new User_taggedmovies (userId, movieId,tagId, timestamp );
							userTag.add(temp);
							}
						}
						catch(NumberFormatException E)
						{
							continue;
						}
			}}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
				try {
					Statement stmt = connection.createStatement();
					String sql = "DELETE FROM USER_TAG";
					stmt.executeUpdate(sql);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				try {
					String sql =	"INSERT INTO USER_TAG\n" +
									"(USER_ID, MOVIEID, TAG_ID, TIMESTAMP)\n" +
									"VALUES (?,?,?,?)";
					PreparedStatement pstmt = connection.prepareStatement(sql);
					for (User_taggedmovies m : userTag) {
						pstmt.setInt(1, m.userId);
						pstmt.setInt(2, m.movieId);
						pstmt.setInt(3, m.tagId);
						pstmt.setNString(4, m.timestamp);
						pstmt.executeQuery();
					}
					System.out.println(" user tag data inserted");




				} catch (SQLException e) {
					e.printStackTrace();
				}

	}
}
