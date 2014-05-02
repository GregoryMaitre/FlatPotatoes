package Connection;

/**
 * This class contains usefull information about the database, request, ...
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

	public final static String URL_DATABASE = "jdbc:oracle:thin:@" + HOST + ":"
			+ PORT + "/" + SERVICE;

	// Set of queries
	// Deliverable 2
	// A:
	public final static String NAMES_OF_ARTISTS_FROM_SWITZERLAND = "SELECT art.name "
			+ "FROM artist art, area are "
			+ "WHERE art.area_id = are.id AND are.name='Switzerland'";

	// B:
	public final static String NAME_AREA_HIGHEST_MALE_FEMALE_GROUPS = "SELECT areas.name, "
			+ "(SELECT COUNT(*) "
			+ "FROM artist artists "
			+ "WHERE artists.area_id=areas.id "
			+ "AND artists.gender   ='Male'), "
			+ "(SELECT COUNT(*) FROM artist artists "
			+ "WHERE artists.area_id=areas.id "
			+ "AND artists.gender   ='Female'), "
			+ "(SELECT COUNT(*) FROM artist artists "
			+ "WHERE artists.area_id=areas.id "
			+ "AND artists.type     ='Group') "
			+ "FROM area areas WHERE areas.id IN "
			+ "(SELECT max_male_id "
			+ "FROM (SELECT COUNT(*) AS max_male, "
			+ "a1.area_id     AS max_male_id "
			+ "FROM artist a1 WHERE a1.gender='Male' "
			+ "GROUP BY a1.area_id ORDER BY max_male DESC) "
			+ "WHERE rownum=1) "
			+ "UNION ALL SELECT areas.name, "
			+ "(SELECT COUNT(*) FROM artist artists "
			+ "WHERE artists.area_id=areas.id "
			+ "AND artists.gender   ='Male'), "
			+ "(SELECT COUNT(*) FROM artist artists "
			+ "WHERE artists.area_id=areas.id "
			+ "AND artists.gender ='Female'), "
			+ "(SELECT COUNT(*) FROM artist artists "
			+ "WHERE artists.area_id=areas.id "
			+ "AND artists.type ='Group') "
			+ "FROM area areas WHERE areas.id IN "
			+ "(SELECT max_male_id FROM "
			+ "(SELECT COUNT(*) AS max_male, "
			+ "a1.area_id AS max_male_id "
			+ "FROM artist a1 WHERE a1.gender='Female' "
			+ "GROUP BY a1.area_id ORDER BY max_male DESC) "
			+ "WHERE rownum<=2) "
			+ "UNION ALL SELECT areas.name, (SELECT COUNT(*) "
			+ "FROM artist artists WHERE artists.area_id=areas.id "
			+ "AND artists.gender='Male'), (SELECT COUNT(*) "
			+ "FROM artist artists WHERE artists.area_id=areas.id "
			+ "AND artists.gender='Female'), (SELECT COUNT(*) "
			+ "FROM artist artists WHERE artists.area_id=areas.id "
			+ "AND artists.type='Group') FROM area areas "
			+ "WHERE areas.id IN (SELECT max_male_id FROM "
			+ "(SELECT COUNT(*) AS max_male, a1.area_id AS max_male_id "
			+ "FROM artist a1 WHERE a1.type='Group' GROUP BY a1.area_id "
			+ "ORDER BY max_male DESC) WHERE rownum<=2)";

	// C:
	public final static String NAME_10_GROUPS_MOST_TRACKS = "SELECT a.name FROM artist a WHERE a.type='Group' "
			+ "AND a.id IN (SELECT artist_id FROM (SELECT a.artist_id, "
			+ "COUNT(*) AS nb FROM artist_track a, artist a2 "
			+ "WHERE a.artist_id=a2.id AND a2.type='Group' "
			+ "GROUP BY a.artist_id ORDER BY nb DESC) WHERE rownum <= 10)";

	// D:
	public final static String NAME_10_GROUPS_MOST_RELEASES = "SELECT a.name FROM artist a WHERE a.type='Group' "
			+ "AND a.id IN (SELECT artist_id FROM (SELECT a.artist_id, COUNT(*) AS nb "
			+ "FROM artist_track a, artist a2, track t, medium m, release r "
			+ "WHERE a2.id = a.artist_id AND a2.type = 'Group' AND a.track_id = t.id "
			+ "AND t.medium_id = m.id AND m.release_id = r.id GROUP BY a.artist_id "
			+ "ORDER BY nb DESC) WHERE rownum <= 10)";

	// E:
	public final static String NAME_FEMALE_ARTIST_MOST_GENRES = "SELECT a.name FROM artist a "
			+ "WHERE a.gender='Female' AND a.id IN (SELECT ag.artist_id "
			+ "FROM artist_genre ag GROUP BY ag.artist_id HAVING COUNT(ag.genre_id) = "
			+ "(SELECT MAX(COUNT(ag2.genre_id)) FROM artist_genre ag2, artist a2 "
			+ "WHERE ag2.artist_id=a2.id AND a2.gender='Female' GROUP BY ag2.artist_id))";

	// F:
	public final static String ALL_CITIES_MORE_FEMALE_THAN_MALE_ARTISTS = "SELECT DISTINCT areas.name "
			+ "FROM area areas, (SELECT a1.area_id AS male_id, COUNT(a1.id) AS male_count FROM artist a1 "
			+ "WHERE a1.gender='Male' GROUP BY a1.area_id), (SELECT a2.area_id AS female_id, COUNT(a2.id) "
			+ "AS female_count FROM artist a2 WHERE a2.gender='Female' GROUP BY a2.area_id) "
			+ "WHERE areas.id=male_id AND areas.id=female_id AND female_count > male_count";
	
	// G:
	public final static String RELEASES_HIGHEST_NUMBER_TRACKS = "SELECT rel.name FROM RELEASE rel "
			+ "WHERE rel.id IN (SELECT med.release_id FROM medium med WHERE med.id IN "
			+ "(SELECT max_medium_id FROM (SELECT COUNT(*) AS tracks_count, t.medium_id AS max_medium_id "
			+ "FROM track t GROUP BY (t.medium_id) ORDER BY tracks_count DESC) WHERE rownum = 1))";
}
