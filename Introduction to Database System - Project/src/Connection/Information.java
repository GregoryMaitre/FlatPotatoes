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
	public final static String A = "SELECT art.name "
			+ "FROM artist art, area are "
			+ "WHERE art.area_id = are.id AND are.name='Switzerland'";

	// B:
	public final static String B = "(SELECT areas1.name, (SELECT COUNT(*) FROM artist artists WHERE artists.area_id=areas1.id AND artists.gender='Male') AS male_count, "
			+ "(SELECT COUNT(*) FROM artist artists WHERE artists.area_id=areas1.id AND artists.gender ='Female') AS female_count, (SELECT COUNT(*) FROM artist artists "
			+ "WHERE artists.area_id=areas1.id AND artists.type='Group') AS group_count FROM area areas1 WHERE areas1.id IN (SELECT area_id_with_highest_males FROM "
			+ "(SELECT COUNT(*) AS number_of_males, a1.area_id AS area_id_with_highest_males FROM artist a1 WHERE a1.gender='Male' GROUP BY a1.area_id "
			+ "ORDER BY number_of_males DESC) WHERE rownum=1)) UNION ALL (SELECT areas2.name, (SELECT COUNT(*) FROM artist artists WHERE artists.area_id=areas2.id "
			+ "AND artists.gender='Male'), (SELECT COUNT(*) FROM artist artists WHERE artists.area_id=areas2.id AND artists.gender='Female'), (SELECT COUNT(*) "
			+ "FROM artist artists WHERE artists.area_id=areas2.id AND artists.type='Group') FROM area areas2 WHERE areas2.id IN (SELECT area_id_with_highest_females "
			+ "FROM (SELECT COUNT(*) AS number_of_females, a2.area_id AS area_id_with_highest_females FROM artist a2 WHERE a2.gender='Female' GROUP BY a2.area_id "
			+ "ORDER BY number_of_females DESC) WHERE rownum=1)) UNION ALL (SELECT areas3.name, (SELECT COUNT(*) FROM artist artists WHERE artists.area_id=areas3.id "
			+ "AND artists.gender='Male'), (SELECT COUNT(*) FROM artist artists WHERE artists.area_id=areas3.id AND artists.gender='Female'), (SELECT COUNT(*) "
			+ "FROM artist artists WHERE artists.area_id=areas3.id AND artists.type='Group') FROM area areas3 WHERE areas3.id IN (SELECT area_id_with_highest_groups "
			+ "FROM (SELECT COUNT(*) AS number_of_groups, a3.area_id AS area_id_with_highest_groups FROM artist a3 WHERE a3.type='Group' GROUP BY a3.area_id "
			+ "ORDER BY number_of_groups DESC ) WHERE rownum=1))";

	// C:
	public final static String C = "SELECT a.name FROM artist a WHERE a.id IN (SELECT artist_id FROM (SELECT a.artist_id, COUNT(*) AS nb FROM artist_track a, artist a2 "
			+ "WHERE a.artist_id=a2.id AND a2.type='Group' GROUP BY a.artist_id ORDER BY nb DESC) WHERE rownum <= 10)";

	// D:
	public final static String D = "SELECT * FROM (SELECT artists.name FROM artist artists, group_release gr "
			+ "WHERE artists.id=gr.group_id ORDER BY gr.releases_count DESC) WHERE rownum <=10";
	
	public final static String D_V = "CREATE OR REPLACE VIEW group_release AS (SELECT artists.id AS group_id, "
			+ "(SELECT COUNT(DISTINCT releases.id) FROM RELEASE releases, medium mediums, track tracks, "
			+ "artist_track artr WHERE artr.artist_id=artists.id AND artr.track_id=tracks.id AND tracks.medium_id=mediums.id "
			+ "AND mediums.release_id=releases.id) AS releases_count FROM artist artists WHERE artists.type='Group' "
			+ "GROUP BY artists.id)";

	// E:
	public final static String E = "SELECT a.name FROM artist a "
			+ "WHERE a.gender='Female' AND a.id IN (SELECT ag.artist_id "
			+ "FROM artist_genre ag GROUP BY ag.artist_id HAVING COUNT(ag.genre_id) = "
			+ "(SELECT MAX(COUNT(ag2.genre_id)) FROM artist_genre ag2, artist a2 "
			+ "WHERE ag2.artist_id=a2.id AND a2.gender='Female' GROUP BY ag2.artist_id))";

	// F:
	public final static String F = "SELECT DISTINCT areas.name FROM area areas, (SELECT a1.area_id AS male_id, COUNT(a1.id) AS male_count FROM artist a1 "
			+ "WHERE a1.gender='Male' GROUP BY a1.area_id), (SELECT a2.area_id AS female_id, COUNT(a2.id) AS female_count FROM artist a2 WHERE a2.gender='Female' "
			+ "GROUP BY a2.area_id) WHERE areas.type ='City' AND areas.id=male_id AND areas.id=female_id AND female_count > male_count";

	// G: 
	public final static String G = "SELECT trax.medium_id FROM track trax GROUP BY trax.medium_id HAVING COUNT(*) = (SELECT tracks_count FROM "
			+ "(SELECT COUNT(*) AS tracks_count, t.medium_id AS max_medium_id FROM track t GROUP BY (t.medium_id) ORDER BY tracks_count DESC) WHERE rownum = 1)";

	// Deliverable 3
	// H:
	public final static String H = "SELECT areas.name AS area_name, male_with_most_tracks.male_name, "
			+ "female_with_most_tracks.female_name, group_with_most_tracks.group_name FROM male_with_most_tracks, "
			+ "female_with_most_tracks, group_with_most_tracks, area areas WHERE female_with_most_tracks.areaid=male_with_most_tracks.areaid "
			+ "AND female_with_most_tracks.areaid  =group_with_most_tracks.areaid AND areas.id=male_with_most_tracks.areaid";

	public final static String H_V1 = "CREATE OR REPLACE VIEW areas_more30 AS (SELECT artists1.area_id FROM artist artists1 "
			+ "GROUP BY artists1.area_id HAVING COUNT(*) > 30)";
	
	public final static String H_V2 = "CREATE OR REPLACE VIEW artist_tracks_count AS (SELECT artr.artist_id   AS art_id, "
			+ "COUNT(artr.track_id) AS tracks_count FROM artist_track artr GROUP BY artr.artist_id)";
	
	public final static String H_V3 = "CREATE OR REPLACE VIEW male_with_most_tracks AS (SELECT areaid, male_name "
			+ "FROM (SELECT areas_more30.area_id AS areaid, male_artist.name AS male_name, atc_male.tracks_count, "
			+ "row_number() over (partition BY areas_more30.area_id order by atc_male.tracks_count DESC) AS RN_male FROM areas_more30, "
			+ "artist_tracks_count atc_male, artist male_artist WHERE male_artist.area_id=areas_more30.area_id AND male_artist.id=atc_male.art_id "
			+ "AND male_artist.gender   ='Male') WHERE RN_male=1)";
	
	public final static String H_V4 = "CREATE OR REPLACE VIEW female_with_most_tracks AS (SELECT areaid, female_name FROM "
			+ "(SELECT areas_more30.area_id AS areaid, female_artist.name  AS female_name, atc_female.tracks_count, "
			+ "row_number() over (partition BY areas_more30.area_id order by atc_female.tracks_count DESC) AS RN_female "
			+ "FROM areas_more30, artist_tracks_count atc_female, artist female_artist WHERE female_artist.area_id=areas_more30.area_id "
			+ "AND female_artist.id=atc_female.art_id AND female_artist.gender   ='Female') WHERE RN_female=1)";
	
	public final static String H_V5 = "CREATE OR REPLACE VIEW group_with_most_tracks AS (SELECT areaid, group_name FROM "
			+ "(SELECT areas_more30.area_id AS areaid, group_artist.name AS group_name, atc_group.tracks_count, "
			+ "row_number() over (partition BY areas_more30.area_id order by atc_group.tracks_count DESC) AS RN_group FROM areas_more30, "
			+ "artist_tracks_count atc_group, artist group_artist WHERE group_artist.area_id=areas_more30.area_id "
			+ "AND group_artist.id=atc_group.art_id AND group_artist.type='Group') WHERE RN_group=1)";
	
	// I:
	public final static String I = "SELECT trax FROM (SELECT rd.records AS rec, tracks.id AS trax "
			+ "FROM records_mediums rd, track tracks WHERE tracks.recording_id=rd.records "
			+ "ORDER BY med_count DESC) WHERE rownum<=25";
	
	public final static String I_V1 = "CREATE OR REPLACE VIEW Metallica AS (SELECT artr.track_id FROM artist_track artr, "
			+ "artist artists WHERE artr.artist_id=artists.id AND artists.name='Metallica')";
	
	public final static String I_V2 = "CREATE OR REPLACE VIEW records_mediums AS (SELECT recordings AS records, COUNT(DISTINCT mediums) AS med_count "
			+ "FROM (SELECT tracks.recording_id AS recordings, tracks.id AS trax, tracks.medium_id AS mediums "
			+ "FROM track tracks, metallica WHERE metallica.track_id=tracks.id) GROUP BY recordings)";

	// J:
	public final static String J = "SELECT genres.name AS top_10_genres, artists.name AS female_artist "
			+ "FROM (SELECT genreid, artid, trackscount, row_number() over (partition BY genreid order by trackscount DESC) AS RN "
			+ "FROM final_view), artist artists, genre genres WHERE RN = 1 AND genreid=genres.id "
			+ "AND artid  =artists.id ORDER BY genreid";

	public final static String J_V1 = "CREATE OR REPLACE VIEW top10genres AS (SELECT genres.id FROM genre genres, "
			+ "(SELECT artist_genre1.genre_ID AS genreid, COUNT(*) AS artist_count FROM artist_genre artist_genre1 "
			+ "GROUP BY artist_genre1.genre_id ORDER BY artist_count DESC) WHERE genres.id=genreid AND rownum<=10)";
	
	public final static String J_V2 = "CREATE OR REPLACE VIEW artist_tracks_count AS (SELECT artr.artist_id   AS art_id, "
			+ "COUNT(artr.track_id) AS tracks_count FROM artist_track artr GROUP BY artr.artist_id)";
	
	public final static String J_V3 = "CREATE OR REPLACE VIEW female_genre AS (SELECT ag.artist_id AS art_id, ag.genre_id AS gen_id "
			+ "FROM artist_genre ag, artist artists WHERE ag.artist_id=artists.id AND artists.gender='Female' )";
	
	public final static String J_V4 = "CREATE OR REPLACE VIEW final_view AS (SELECT top10genres.id AS genreid, "
			+ "female_genre.art_id AS artid, artist_tracks_count.tracks_count AS trackscount FROM top10genres, "
			+ "female_genre, artist_tracks_count WHERE top10genres.id=female_genre.gen_id "
			+ "AND artist_tracks_count.art_id=female_genre.art_id)";
	
	// K:
	public final static String K = "(SELECT genre_without_female FROM genres_without_females) "
			+ "UNION ALL (SELECT genre_without_male FROM genres_without_males) "
			+ "UNION ALL (SELECT genre_without_groups FROM genres_without_groups)";

	public final static String K_V1 = "CREATE OR REPLACE VIEW genres_without_females AS (SELECT genres.name AS genre_without_female "
			+ "FROM genre genres WHERE genres.id NOT IN (SELECT ag.genre_id FROM artist_genre ag, artist female_artists "
			+ "WHERE female_artists.gender='Female' AND female_artists.id=ag.artist_id GROUP BY ag.genre_id))";
	
	public final static String K_V2 = "CREATE OR REPLACE VIEW genres_without_males AS (SELECT genres.name AS genre_without_male FROM genre genres "
			+ "WHERE genres.id NOT IN (SELECT ag.genre_id FROM artist_genre ag, artist male_artists WHERE male_artists.gender='Male' "
			+ "AND male_artists.id=ag.artist_id GROUP BY ag.genre_id))";
	
	public final static String K_V3 = "CREATE OR REPLACE VIEW genres_without_groups AS (SELECT genres.name AS genre_without_groups "
			+ "FROM genre genres WHERE genres.id NOT IN (SELECT ag.genre_id FROM artist_genre ag, artist group_artists WHERE group_artists.type='Group' "
			+ "AND group_artists.id=ag.artist_id GROUP BY ag.genre_id))";
	
	// L:
	public final static String L = "SELECT areas.name AS area_name, male_with_most_tracks.male_name FROM male_with_most_tracks, area areas "
			+ "WHERE areas.id =male_with_most_tracks.areaid";
	
	public final static String L_V1 = "CREATE OR REPLACE VIEW areas_more10 AS (SELECT artists1.area_id FROM artist artists1 WHERE artists1.type='Group' "
			+ "GROUP BY artists1.area_id HAVING COUNT(*) > 10)";
	
	public final static String L_V2 = "CREATE OR REPLACE VIEW artist_tracks_count AS (SELECT artr.artist_id AS art_id, COUNT(artr.track_id) AS tracks_count "
			+ "FROM artist_track artr GROUP BY artr.artist_id)";
	
	public final static String L_V3 = "CREATE OR REPLACE VIEW male_with_most_tracks AS (SELECT areaid, male_name FROM (SELECT am10.area_id AS areaid, "
			+ "male_artist.name AS male_name, atc_male.tracks_count, row_number() over (partition BY am10.area_id order by atc_male.tracks_count DESC) AS RN_male "
			+ "FROM areas_more10 am10, artist_tracks_count atc_male, artist male_artist WHERE male_artist.area_id=am10.area_id AND male_artist.id=atc_male.art_id "
			+ "AND male_artist.gender='Male') WHERE RN_male<=5)";

	// M:
	public final static String M = "SELECT artists.name FROM (SELECT artist_id AS art, COUNT(track_id) AS tracks_on_comp "
			+ "FROM groups, track tracks, compilations compilation WHERE groups.track_id=tracks.id AND tracks.medium_id =compilation.mediums "
			+ "GROUP BY artist_id ORDER BY tracks_on_comp DESC), artist artists WHERE art=artists.id AND rownum <=10";

	public final static String M_V1 = "CREATE OR REPLACE VIEW compilations AS (SELECT tracks.medium_id AS mediums FROM track tracks, "
			+ "artist_track artr WHERE tracks.id=artr.track_id GROUP BY tracks.medium_id HAVING COUNT(DISTINCT artr.artist_id)>1)";
	
	public final static String M_V2 = "CREATE OR REPLACE VIEW groups AS (SELECT arttr.artist_id, arttr.track_id FROM artist artists, "
			+ "artist_track arttr WHERE artists.type='Group' AND artists.id=arttr.artist_id)";
	
	// N:
	public final static String N = "Not implemented";
	// O:
	public final static String O = "SELECT mediums2.release_id FROM medium mediums2, max_meds GROUP BY mediums2.release_id "
			+ "HAVING COUNT(*)=(SELECT max_meds.med_count FROM max_meds)";
	
	public final static String O_V = "CREATE OR REPLACE VIEW max_meds AS (SELECT med_count FROM (SELECT mediums.release_id, "
			+ "COUNT(*) AS med_count FROM medium mediums GROUP BY mediums.release_id ORDER BY med_count DESC) WHERE rownum=1)";

	// P:
	public final static String P = "SELECT genres.name FROM genre genres, (SELECT ag.genre_id AS genreid, "
			+ "COUNT(*) AS artist_count FROM artist_genre ag, groups_3_genres g3g WHERE ag.artist_id=g3g.artist_id "
			+ "GROUP BY ag.genre_id ORDER BY artist_count DESC) WHERE genres.id=genreid AND rownum=1";

	public final static String P_V = "CREATE OR REPLACE VIEW groups_3_genres AS (SELECT ag.artist_id FROM artist_genre ag, "
			+ "artist artists WHERE ag.artist_id=artists.id AND artists.type='Group' HAVING COUNT(*)>=3 "
			+ "GROUP BY ag.artist_id)";
	// Q:
	public final static String Q = "SELECT recordings.name, tracks_count FROM recording recordings, "
			+ "(SELECT tracks.recording_id AS rec_id, COUNT(*) AS tracks_count FROM track tracks "
			+ "GROUP BY tracks.recording_id ORDER BY tracks_count DESC) WHERE recordings.id=rec_id "
			+ "AND rownum <= 5";

	// R:
	public final static String R = "SELECT artists.name FROM artist artists, (SELECT tracks.art_id AS artid, "
			+ "(tracks.tracks_count / releases.releases_count) AS ratio FROM artist_#tracks tracks, "
			+ "artist_#releases releases WHERE tracks.art_id = releases.art_id ORDER BY ratio DESC) "
			+ "WHERE artists.id=artid AND rownum<=10";
	
	public final static String R_V1 = "CREATE OR REPLACE VIEW artist_#tracks AS (SELECT artr.artist_id AS art_id, "
			+ "COUNT(artr.track_id) AS tracks_count FROM artist_track artr GROUP BY artr.artist_id)";
	
	public final static String R_V2 = "CREATE OR REPLACE VIEW artist_#releases AS (SELECT artr.artist_id AS art_id, COUNT(DISTINCT releases.id) AS releases_count "
			+ "FROM artist_track artr, track tracks, medium mediums, RELEASE releases WHERE artr.track_id =tracks.id AND tracks.medium_id=mediums.id "
			+ "AND mediums.id= releases.id GROUP BY artr.artist_id)";

	// S:
	public final static String S = "SELECT artists.name, art_av.average FROM artist artists, "
			+ "artists_average art_av WHERE artists.id=art_av.artistid ORDER BY art_av.average DESC";
	
	public final static String S_V1 = "CREATE OR REPLACE VIEW top_songs_#mediums AS (SELECT tracks.recording_id AS rec_id, "
			+ "COUNT(tracks.medium_id) AS med_count FROM track tracks GROUP BY tracks.recording_id HAVING COUNT(tracks.medium_id) >= 100)";
	
	public final static String S_V2 = "CREATE OR REPLACE VIEW artists_average AS (SELECT artr.artist_id AS artistid, "
			+ "AVG(mediums.med_count) AS average FROM artist_track artr, top_songs_#mediums mediums, track tracks "
			+ "WHERE tracks.id=artr.track_id AND tracks.recording_id=mediums.rec_id GROUP BY artr.artist_id)";
}
