package Connection;

/**
 * TODO: Comment this class
 * 
 * @author Gregory Maitre & Patrick Andrade & Beaud Guillaume
 * 
 */
public class Information {
	public final static String HOST = "icoracle.epfl.ch";
	public final static String PORT = "1521";
	public final static String SERVICE = "srso4.epfl.ch";

	public final static String USER = "db2014_g01";
	public final static String PASSWORD = "ic2013db01";

	public final static String URL_DATABASE = "jdbc:oracle:thin:@" + HOST + ":" + PORT
			+ "/" + SERVICE;
	
	//Set of queries
	public final static String NAMES_OF_ARTISTS_FROM_SWITZERLAND = 
			"select art.name from Artist art, Area are where art.AREA_ID = are.ID AND are.NAME = 'Switzerland'";
}
