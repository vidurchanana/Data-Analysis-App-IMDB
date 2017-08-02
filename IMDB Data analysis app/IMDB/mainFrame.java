package hw3;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;
import org.jdatepicker.impl.UtilDateModel;

import hw3.Res;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;


public class mainFrame extends JFrame {
private ArrayList<JCheckBox> countrychkbox = new ArrayList<JCheckBox>();
private ArrayList<JCheckBox> genreCheckBoxes= new ArrayList<JCheckBox>();
private ArrayList<String> genre= new ArrayList<String>();
private ArrayList<String> country= new ArrayList<String>();
private JPanel contentPane, countriesPanel;
private JTextField tf_cast1;
private JTextField tf_cast2;
private JTextField tf_cast3;
private JTextField tf_cast4;
private JTextField tf_director;
private JTextField tf_rating;
private JTextField tf_noRating;
private JTextField tf_yearfrom;
private JTextField tf_yearTo;
private JTextField tf_userID;
private JTextField tf_userRating;
private JTextArea queryArea, resultArea;
private JButton execute;
private JComboBox<String> userRatingSign, noOfRatingSign, ratingSign, attr,comboBox, comboBox_1, comboBox_2, comboBox_3, comboBox_4; 
private JDatePickerImpl datePicker, datePicker1;
private JScrollPane countriesScrollPane;
private Get g= new Get();
GetChoice l= new GetChoice();
/**


* Launch the application.


*/


public static void main(String[] args) {


	EventQueue.invokeLater(new Runnable() {
	
	public void run() {


		try {
		
		mainFrame frame = new mainFrame();
		
		frame.setVisible(true);
		
		} catch (Exception e) {
		
		e.printStackTrace();
		
		}


	}


	});

}



public mainFrame() {
	
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	setBounds(300, 20, 880, 700);
	
	contentPane = new JPanel();
	
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	
	setContentPane(contentPane);
	
	contentPane.setLayout(null);
	
	
	JLabel title = new JLabel("IMDB Search");
	
	title.setHorizontalAlignment(SwingConstants.CENTER);
	
	title.setBounds(0, 0, 880, 60);
	
	title.setBackground(new Color(211, 211, 211));
	
	title.setOpaque(true);
	
	contentPane.add(title);


	JLabel lblNewLabel = new JLabel("Genre");
	
	lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	
	lblNewLabel.setForeground(Color.BLACK);
	
	lblNewLabel.setBackground(Color.PINK);
	
	lblNewLabel.setBounds(0, 53, 176, 60);
	
	lblNewLabel.setOpaque(true);
	
	contentPane.add(lblNewLabel);
	
	
	JLabel label = new JLabel("Country");
	
	label.setHorizontalAlignment(SwingConstants.CENTER);
	
	label.setOpaque(true);
	
	label.setForeground(Color.BLACK);
	
	label.setBackground(Color.PINK);
	
	label.setBounds(176, 53, 176, 60);
	
	contentPane.add(label);
	
	
	JLabel label_1 = new JLabel("Cast");
	
	label_1.setHorizontalAlignment(SwingConstants.CENTER);
	
	label_1.setOpaque(true);
	
	label_1.setForeground(Color.BLACK);
	
	label_1.setBackground(Color.PINK);
	
	label_1.setBounds(352, 53, 176, 60);
	
	contentPane.add(label_1);
	
	
	JLabel label_2 = new JLabel("Rating");
	
	label_2.setHorizontalAlignment(SwingConstants.CENTER);
	
	label_2.setOpaque(true);
	
	label_2.setForeground(Color.BLACK);
	
	label_2.setBackground(Color.PINK);
	
	label_2.setBounds(526, 53, 176, 60);
	
	contentPane.add(label_2);
	
	
	JLabel label_3 = new JLabel("User's Tags and Rating");
	
	label_3.setHorizontalAlignment(SwingConstants.CENTER);
	
	label_3.setOpaque(true);
	
	label_3.setForeground(Color.BLACK);
	
	label_3.setBackground(Color.PINK);
	
	label_3.setBounds(702, 53, 176, 60);
	
	contentPane.add(label_3);


	JPanel genrePanel = new JPanel();
	
	genrePanel.setLayout(new GridLayout(30,0,0,0));

	ArrayList<String> genreGet= g.getGenre();

	int i=0;

	for(String c: genreGet){

		JCheckBox chckbxNewCheckBox = new JCheckBox(c);
		
		chckbxNewCheckBox.setBounds(19, 20+i, 129, 23);
		
		chckbxNewCheckBox.addActionListener(l);
		genrePanel.add(chckbxNewCheckBox);
		
		genreCheckBoxes.add(chckbxNewCheckBox);
		
		i=i+25;}

	genreCheckBoxes.get(1).setSelected(true);
	JScrollPane genreScrollPane = new JScrollPane();
	genreScrollPane.setViewportView(genrePanel);
	genreScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	genreScrollPane.setBounds(0, 110, 176, 340);
	contentPane.add(genreScrollPane);
	
	//country
	countriesPanel = new JPanel();
	countriesPanel.setLayout(new GridLayout(80,0,0,0));
	String s=genreCheckBoxes.get(1).getText();
	genre.add(s);
	ArrayList<String> countries= g.getCountries(genre, "AND");
	System.out.println(countries.size());
	i=0;
	for(String c: countries){
		JCheckBox chckbxNewCheckBox = new JCheckBox(c);
		chckbxNewCheckBox.setBounds(19, 20+i, 129, 23);
		chckbxNewCheckBox.addActionListener(l);
		countriesPanel.add(chckbxNewCheckBox);
		countrychkbox.add(chckbxNewCheckBox);
		i=i+25;}
	
	countrychkbox.get(1).setSelected(true);
	countriesScrollPane = new JScrollPane();
	countriesScrollPane.setViewportView(countriesPanel);
	countriesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	countriesScrollPane.setBounds(176, 110, 176, 340);
    contentPane.add(countriesScrollPane);

	JPanel panel_2 = new JPanel();
	panel_2.setLayout(null);
	panel_2.setBounds(352, 110, 176, 340);
	contentPane.add(panel_2);
	
	tf_cast1 = new JTextField();
	tf_cast1.setBounds(17, 17, 100, 26);
	panel_2.add(tf_cast1);
	tf_cast1.setColumns(10);
	tf_cast2 = new JTextField();
	tf_cast2.setBounds(17, 55, 100, 26);
	panel_2.add(tf_cast2);
	tf_cast2.setColumns(10);
	tf_cast3 = new JTextField();
	tf_cast3.setBounds(17, 89, 100, 26);
	panel_2.add(tf_cast3);
	tf_cast3.setColumns(10);
	tf_cast4 = new JTextField();
	tf_cast4.setBounds(17, 121, 100, 26);
	panel_2.add(tf_cast4);
	tf_cast4.setColumns(10);
	tf_director = new JTextField();
	tf_director.setBounds(17, 264, 100, 26);
	panel_2.add(tf_director);
	tf_director.setColumns(10);
	JLabel lblDirector = new JLabel("Director");
	lblDirector.setOpaque(true);
	lblDirector.setHorizontalAlignment(SwingConstants.CENTER);
	lblDirector.setForeground(Color.BLACK);
	lblDirector.setBackground(Color.PINK);
	lblDirector.setBounds(0, 177, 176, 60);
	panel_2.add(lblDirector);
	
	comboBox = new JComboBox<String>();
	comboBox.setBounds(17, 285, 111, 24);
	String directorNames[]=g.getDirector();
	comboBox.setModel(new DefaultComboBoxModel<String>(directorNames));
	panel_2.add(comboBox);
	comboBox.addActionListener(l);
	
	comboBox_1 = new JComboBox<String>();
	comboBox_1.setBounds(125, 17, 32, 24);
	String actorNames[]=g.getActor();
	comboBox_1.setModel(new DefaultComboBoxModel<String>(actorNames));
	panel_2.add(comboBox_1);
	comboBox_1.addActionListener(l);

	comboBox_2 = new JComboBox<String>();
	comboBox_2.setBounds(129, 55, 32, 24);
	comboBox_2.setModel(new DefaultComboBoxModel<String>(actorNames));
	comboBox_2.addActionListener(l);
	panel_2.add(comboBox_2);
	
	comboBox_3 = new JComboBox<String>();
	comboBox_3.setBounds(129, 89, 32, 24);
	comboBox_3.setModel(new DefaultComboBoxModel<String>(actorNames));
	comboBox_3.addActionListener(l);
	panel_2.add(comboBox_3);
	
	comboBox_4 = new JComboBox<String>();
	comboBox_4.setBounds(125, 121, 32, 24);
	comboBox_4.setModel(new DefaultComboBoxModel<String>(actorNames));
	comboBox_4.addActionListener(l);
	panel_2.add(comboBox_4);
	
	
	JPanel panel_3 = new JPanel();
	
	panel_3.setLayout(null);
	
	panel_3.setBounds(536, 110, 176, 340);
	
	contentPane.add(panel_3);

    ratingSign = new JComboBox();


	ratingSign.setModel(new DefaultComboBoxModel(new String[] {"=", ">", "<", ">=", "<="}));
	
	ratingSign.setBounds(21, 18, 109, 27);
	
	panel_3.add(ratingSign);

	tf_rating = new JTextField();
	
	tf_rating.setBounds(21, 44, 130, 26);
	
	panel_3.add(tf_rating);
	
	tf_rating.setColumns(10);

    noOfRatingSign = new JComboBox();
	noOfRatingSign.setModel(new DefaultComboBoxModel(new String[] {"=", ">", "<", ">=", "<="}));
	noOfRatingSign.setBounds(21, 154, 109, 27);
	panel_3.add(noOfRatingSign);

	tf_noRating = new JTextField();
	
	tf_noRating.setColumns(10);
	
	tf_noRating.setBounds(21, 176, 130, 26);
	
	panel_3.add(tf_noRating);




	JLabel lblNoOfRating = new JLabel("No. Of Rating");
	
	lblNoOfRating.setOpaque(true);
	
	lblNoOfRating.setHorizontalAlignment(SwingConstants.CENTER);
	
	lblNoOfRating.setForeground(Color.BLACK);
	
	lblNoOfRating.setBackground(Color.PINK);
	
	lblNoOfRating.setBounds(0, 82, 176, 60);
	
	panel_3.add(lblNoOfRating);




	JLabel lblMovieYear = new JLabel("Movie Year");
	
	lblMovieYear.setOpaque(true);
	
	lblMovieYear.setHorizontalAlignment(SwingConstants.CENTER);
	
	lblMovieYear.setForeground(Color.BLACK);
	
	lblMovieYear.setBackground(Color.PINK);
	
	lblMovieYear.setBounds(0, 205, 176, 60);
	
	panel_3.add(lblMovieYear);




	tf_yearfrom = new JTextField();
	
	tf_yearfrom.setColumns(10);
	
	tf_yearfrom.setBounds(21, 276, 130, 26);
	
	panel_3.add(tf_yearfrom);
	
	
	tf_yearTo = new JTextField();
	
	tf_yearTo.setColumns(10);
	
	tf_yearTo.setBounds(21, 308, 130, 26);
	
	panel_3.add(tf_yearTo);
	
	
	JPanel panel_4 = new JPanel();
	
	panel_4.setLayout(null);
	
	panel_4.setBounds(712, 110, 176, 340);
	
	contentPane.add(panel_4);
	
	
	JLabel lblUserId = new JLabel("User ID");
	
	lblUserId.setBounds(6, 20, 61, 16);
	
	panel_4.add(lblUserId);
	
	
	tf_userID = new JTextField();
	
	tf_userID.setBounds(0, 36, 130, 26);
	
	panel_4.add(tf_userID);
	
	tf_userID.setColumns(10);




    userRatingSign = new JComboBox();
	userRatingSign.setModel(new DefaultComboBoxModel(new String[] {"=", ">", "<", ">=", "<="}));
	
	userRatingSign.setBounds(6, 229, 109, 27);
	
	panel_4.add(userRatingSign);




	JLabel lblUserRating = new JLabel("User Rating");
	
	lblUserRating.setBounds(6, 201, 98, 16);
	
	panel_4.add(lblUserRating);




	JLabel lblValue = new JLabel("Value");
	
	lblValue.setBounds(6, 268, 98, 16);
	
	panel_4.add(lblValue);


	Properties p = new Properties();
	p.put("text.today", "Today");
	p.put("text.month", "Month");
	p.put("text.year", "Year");


	class DateLabelFormatter extends AbstractFormatter {


	   private String datePattern = "yyyy-MM-dd";
	   private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
	
	   @Override
	   public Object stringToValue(String text) throws ParseException {
	       return dateFormatter.parseObject(text);
	   }


   @Override
	   public String valueToString(Object value) throws ParseException {
	       if (value != null) {
	           Calendar cal = (Calendar) value;
	           return dateFormatter.format(cal.getTime());
	       }
	
	       return "";
	   }
	}




	SqlDateModel model = new SqlDateModel();
	JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
    datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
	
	datePicker.setBorder(new LineBorder(new Color(0, 0, 0), 3));
	datePicker.setBackground(Color.LIGHT_GRAY);
	datePicker.setBounds(6, 93, 143, 35);
	panel_4.add(datePicker);

	UtilDateModel model1 = new UtilDateModel();
	JDatePanelImpl datePanel1 = new JDatePanelImpl(model1, p);
    datePicker1 = new JDatePickerImpl(datePanel1, new DateLabelFormatter());


	datePicker1.setBounds(6, 154, 143, 35);
	datePicker1.setBorder(new LineBorder(new Color(0, 0, 0), 3));
	datePicker1.setBackground(Color.LIGHT_GRAY);
	panel_4.add(datePicker1);


	tf_userRating = new JTextField();
	
	tf_userRating.setColumns(10);
	
	tf_userRating.setBounds(0, 296, 130, 26);
	
	panel_4.add(tf_userRating);
	
	
	JLabel lblSearchFor = new JLabel("Search For");
	
	lblSearchFor.setBounds(20, 462, 82, 16);
	
	contentPane.add(lblSearchFor);




    attr = new JComboBox();
	attr.setModel(new DefaultComboBoxModel(new String[] {"AND", "OR"}));
	attr.setBounds(106, 462, 107, 27);
	attr.addActionListener(l);
	contentPane.add(attr);
	
	
	queryArea = new JTextArea();
	
	queryArea.setBounds(26, 583, 234, 169);
	
	queryArea.setLayout(new GridLayout(1,0,0,0));

	JScrollPane queryscrollPane = new JScrollPane();
	
	queryscrollPane.setBounds(30, 490, 826, 35);
	
	contentPane.add(queryscrollPane);
	
	queryscrollPane.setViewportView(queryArea);
	
	queryscrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	
	queryscrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	
	
	resultArea = new JTextArea();
	
	resultArea.setBounds(26, 583, 234, 169);
	
	resultArea.setLayout(new GridLayout(1,0,0,0));
	
	
	JScrollPane resultscrollPane = new JScrollPane();
	
	resultscrollPane.setBounds(20, 552, 836, 97);
	
	contentPane.add(resultscrollPane);
	
	resultscrollPane.setViewportView(resultArea);
	
	resultscrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	
	resultscrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


	execute = new JButton("Execute Query");
	
	execute.setBounds(302, 524, 117, 29);
	
	contentPane.add(execute);
	
	execute.addActionListener(l);




}


class GetChoice implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==comboBox ){
			tf_director.setText((String) comboBox.getSelectedItem());
			}
		if(e.getSource()==comboBox_1){
		tf_cast1.setText((String) comboBox_1.getSelectedItem());
		}
		if(e.getSource()==comboBox_2){
		tf_cast2.setText((String) comboBox_2.getSelectedItem());
		}
		if(e.getSource()==comboBox_3){
		tf_cast3.setText((String) comboBox_3.getSelectedItem());
		}
		if(e.getSource()==comboBox_4){
		tf_cast4.setText((String) comboBox_4.getSelectedItem());
		}
		if(genreCheckBoxes.contains(e.getSource()) || e.getSource()==attr){
			
			System.out.println("genre clicked");
			genre.clear();
			 for (JCheckBox checkBox : genreCheckBoxes) {
			        if (checkBox.isSelected()) {
			        	genre.add(checkBox.getText());}
			  }
	        for(String c: genre){
	        	System.out.println(c);
	        }
	        String attrS=(String) attr.getSelectedItem();
	        ArrayList<String> countries= g.getCountries(genre, attrS);
	    	int i=0;
	    	countriesPanel.removeAll();
	    	
		    for(String c: countries){
			    JCheckBox chckbxNewCheckBox = new JCheckBox(c);
			    
		    	chckbxNewCheckBox.setBounds(19, 20+i, 129, 23);
		    	
		    	chckbxNewCheckBox.addActionListener(l);
		    	
		    	countriesPanel.add(chckbxNewCheckBox);
		    	
		    	countrychkbox.add(chckbxNewCheckBox);
		    	
		    	i=i+25;
		    }
		    
		    System.out.println("done");
		    repaint();
		    revalidate(); 
		}
			    
		
	if(e.getSource()==execute){
	
			ArrayList<String> actors=new ArrayList<>();
			
			ArrayList<String> directors=new ArrayList<>();
			
			int yearFrom=0, yearTo=0, userId=0, noRatings=0;
			
			double ratingValue=0, userRatingValue=0;
			
			String ratingS="=", norS="=", userRatingS="=", attrS="AND";
			
			long timestampTo=0, timestampFrom=0;
			
			String cast= tf_cast1.getText();
			
			if(!cast.equals(""))
			
				{actors.add(cast);}
			
			cast= tf_cast2.getText();
			
			if(!cast.equals(""))
			
				{actors.add(cast);}
			
			cast= tf_cast3.getText();
			
			if(!cast.equals(""))
			
				{actors.add(cast);}
			
			cast= tf_cast4.getText();
			
			if(!cast.equals(""))
			
				{actors.add(cast);}
			
			
			String director= tf_director.getText();
			
			if(!director.equals(""))
			
				{directors.add(director);}
			
			if(!tf_rating.getText().equals(""))
			
				ratingValue= Double.parseDouble(tf_rating.getText());
			
			ratingS= (String) ratingSign.getSelectedItem();
			
			if(!tf_noRating.getText().equals(""))
			
				noRatings= Integer.parseInt(tf_noRating.getText());
			
			norS=(String) noOfRatingSign.getSelectedItem();
			
			if(!tf_userRating.getText().equals(""))
			
				userRatingValue= Double.parseDouble(tf_userRating.getText());
			
			userRatingS=(String) userRatingSign.getSelectedItem();
			
			if(!tf_yearfrom.getText().equals(""))
			
				yearFrom=Integer.parseInt(tf_yearfrom.getText());
			
			if(!tf_yearTo.getText().equals(""))
			
				yearTo=Integer.parseInt(tf_yearTo.getText());
			
			if(!tf_userID.getText().equals(""))
			
				userId=Integer.parseInt(tf_userID.getText());
			
			attrS=(String) attr.getSelectedItem();
			if(datePicker.getModel().getValue()!=null)
			{ Date fromDate = (Date) datePicker.getModel().getValue();
			 timestampFrom=fromDate.getTime();
			}
			if(datePicker1.getModel().getValue()!=null)
			{ Date toDate = (Date) datePicker1.getModel().getValue();
			 timestampTo=toDate.getTime();}
			
			
			String sqlQuery=generateQuery(attrS,country,genre,actors, directors, yearFrom,  yearTo,  userId,  noRatings,  ratingValue,  userRatingValue,  ratingS,  norS,  userRatingS,  timestampTo,  timestampFrom);	
			
			if(sqlQuery.equals("")){
				queryArea.setText("No options were selected");
				resultArea.setText("Nothing to display");
			}
			else{
			queryArea.setText(sqlQuery);
			ArrayList<Res> result= g.gRes(sqlQuery);
			
			if(result.size()==0) {
				resultArea.setText("No movie match this criteria");}
			else{
				resultArea.setText("");
				for(int i=0;i<result.size();i++)
					{String line=result.get(i).title+"\n "+result.get(i).country+"\n"+result.get(i).rating+"\n"+result.get(i).reviews+"\n";
					for(int j=0;j<result.get(i).genN.size();j++){
						line+=result.get(i).genN.get(j)+", ";
					}
					line+="\n";
					for(int j=0;j<result.get(i).tag.size();j++){
						line+=result.get(i).tag.get(j)+", ";
					}
					resultArea.append("\n"+line+"\n\n");
					}
				}
			}
	}

country.clear();
genre.clear();


for (JCheckBox checkBox : countrychkbox) {				


        if (checkBox.isSelected()) {


        country.add(checkBox.getText());
        

        }}

repaint();

revalidate(); 


}

}


//createQuery starts
public String generateQuery(String attribute,
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
			utmp+="select movieid from user_rating where rating"+usrRSign+usrRval;
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
}



