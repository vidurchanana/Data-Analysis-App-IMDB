package hw3;

import java.util.ArrayList;

public class Createquery {
	
		public String generateQuery(
				
				String attribute,
				ArrayList<String> country,
				ArrayList<String> genre,
				ArrayList<String> actors,
				ArrayList<String> directors, 
				int yearFrom, 
				int yearTo, 
				int userId, 
				int numRatings, 
				double rVal, 
				double usrRval, 
				String rSign, 
				String norSign, 
				String usrRSign, 
				long timestampTo, 
				long timestampFrom)
		{
			ArrayList<String> q= new ArrayList<>();
			int i=0;
			int count=0;

			if(genre.size()!=0){
				
				i=0;
				String temporary="";
				temporary+="(select movieid from movie_genre where genre_name='"+genre.get(i)+"'";
				for(i=1;i<genre.size();i++){
					temporary+=" INTERSECT (select movieid from movie_genre where genre_name='"+genre.get(i)+"'";
				}
				temporary+=")";
				q.add(temporary);
			}


			//countries
			if(country.size()!=0)
			{
				i=0;
				String temporary="";
				temporary=temporary+"select movieid from movie_country where (country='"+country.get(i)+"'";
				for(i=1;i<country.size();i++){
					temporary+=" OR country='"+country.get(i)+"'";
				}
				temporary+=")";
				q.add(temporary);
			}
			//directors
			if(directors.size()!=0)
			{
				i=0;
				String temporary="";
				temporary="select movieid from movie_directors where (director_name='"+directors.get(i)+"'";
				for(i=1;i<directors.size();i++){
					temporary+=" OR director_name='"+directors.get(i)+"'";
				}
				temporary+=")";
				q.add(temporary);
			}
			//actors
			if(actors.size()!=0){
				i=0;
				String temporary="";
				temporary+="(select movieid from movie_actors where actor_name='"+actors.get(i)+"'";
				for(i=1;i<actors.size();i++){
					temporary+=" INTERSECT select movieid from movie_actors where actor_name='"+actors.get(i)+"'";
				}
				temporary+=")";
				q.add(temporary);
			}
			//userRatedMovies info
			count=0;
			String utmp="";
			if(userId!=0)
			{
				utmp+="select movieid from user_rating where user_id="+userId;
				count++;
			}
			if(usrRval!=0){
				if(count==0)
				{
					utmp+="select movieid from user_rating where rating="+usrRSign+usrRval;
				}
				else
					utmp+=" "+attribute+ " rating"+usrRSign+usrRval;	
				count++;
			}
			if(timestampFrom!=0){
				if(count==0)
				{
					utmp+="select movieid from user_rating where timestamp>="+timestampFrom;
				}
				else
					utmp+=" "+attribute+ " timestamp>="+timestampFrom;
				count++;
			}
			if(timestampTo!=0){
				if(count==0)
				{
					utmp+="select movieid from user_rating where timestamp<="+timestampTo;
				}
				else
					utmp+=" "+attribute+ " timestamp<="+timestampTo;
				count++;
			}
			if(count!=0)
				q.add(utmp);
			
			//movie attributes rating etc
			count=0;
			String mtmp="";
			if(yearFrom!=0){
				mtmp+="select movieid from movies where year>="+yearFrom;
				count++;
			}
			if(yearTo!=0){
				if(count==0)
				{
					mtmp+="select movieid from movies where year<="+yearTo;
				}
				else
					mtmp+=" and year<="+yearTo;
				count++;
			}
			if(rVal!=0){
				if(count==0)
				{
					mtmp+="select movieid from movies where (AC_RATING+ TC_RATING +A_RATING)/3 "+rSign+rVal;
				}
				else
					mtmp+=" "+attribute+" (AC_RATING+ TC_RATING +A_RATING)/3 "+rSign+rVal;
				count++;
			}
			if(numRatings!=0){
				if(count==0)
				{
					mtmp+="select movieid from movies where (AC_NUM_REVIEWS+ TC_NUM_REVIEWS +A_NUM_REVIEWS)"+norSign+numRatings;
				}
				else
					mtmp+=" "+attribute+" (AC_NUM_REVIEWS+ TC_NUM_REVIEWS +A_NUM_REVIEWS)/3"+norSign+numRatings;
				count++;
			}
			if(count!=0)
				q.add(mtmp);
		
			String inquery = "", choice;
			if(attribute.equals("OR"))
				choice="UNION";
			else
				choice="INTERSECT";
			i=0;
			if(q.size()==0)
				return inquery;
			inquery+=q.get(i);
			for(i=1;i<q.size();i++)
			{
				inquery+=" "+choice+" ";
				inquery+=q.get(i);
			}
			
			String outquery="select m.title, m.year, c.country, (m.AC_NUM_REVIEWS+ m.TC_NUM_REVIEWS +m.A_NUM_REVIEWS) as Reviews, "
					
					+ " round((m.AC_RATING+ m.TC_RATING +m.A_RATING)/3,1) as AvgRating"+" from movies m, movie_country c"
					
							+ " where m.movieid=c.movieid"
							
							+ " and m.movieid in ("+inquery+")";
			System.out.println(outquery);
			return outquery;
		}
		public static void main(String[] args) {
			ArrayList<String> country=new ArrayList();
			ArrayList<String> genre=new ArrayList();
			ArrayList<String> actors=new ArrayList();
			ArrayList<String> directors=new ArrayList();
			int yearFrom=0, yearTo=0, userId=0, numRatings=0;
			double rVal=0;
			double usrRval=0;
			String rSign="<=", norSign="<=", usrRSign="<", attrSearch="AND";
			long timestampTo=0, timestampFrom=0;
			country.add("USA");
		
			Createquery q= new Createquery();
			System.out.println(q.generateQuery(attrSearch,country,genre,actors, directors, yearFrom,  yearTo,  userId,  numRatings,  rVal,  usrRval,  rSign,  norSign,  usrRSign,  timestampTo,  timestampFrom));
		}


	}

