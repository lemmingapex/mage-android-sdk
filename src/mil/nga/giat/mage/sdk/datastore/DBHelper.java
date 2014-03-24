package mil.nga.giat.mage.sdk.datastore;

import java.sql.SQLException;

import mil.nga.giat.mage.sdk.datastore.location.Location;
import mil.nga.giat.mage.sdk.datastore.location.LocationGeometry;
import mil.nga.giat.mage.sdk.datastore.location.LocationProperty;
import mil.nga.giat.mage.sdk.datastore.observation.Attachment;
import mil.nga.giat.mage.sdk.datastore.observation.Observation;
import mil.nga.giat.mage.sdk.datastore.observation.ObservationGeometry;
import mil.nga.giat.mage.sdk.datastore.observation.ObservationProperty;
import mil.nga.giat.mage.sdk.datastore.user.User;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * This is an implementation of OrmLite android database Helper. Go here to get
 * daos that you may need. Manage your table creation and update strategies here
 * as well.
 * 
 * @author travis, wiedemannse
 * 
 */
public class DBHelper extends OrmLiteSqliteOpenHelper {

	private static DBHelper helperInstance;

	private static final String DATABASE_NAME = "mage.db";
	private static final String LOG_NAME = DBHelper.class.getName();
	private static final int DATABASE_VERSION = 1;

	//Observation DAOS
	private Dao<Observation, Long> observationDao;
	private Dao<ObservationGeometry, Long> geometryDao;
	private Dao<ObservationProperty, Long> propertyDao;
	private Dao<Attachment, Long> attachmentDao;
	
	//User and Location DAOS
	private Dao<User, Long> userDao;
	private Dao<Location, Long> locationDao;
	private Dao<LocationGeometry, Long> locationGeometryDao;
	private Dao<LocationProperty, Long> locationPropertyDao;
	
	/**
	 * Singleton implementation.
	 * @param context
	 * @return
	 */
	public static DBHelper getInstance(Context context) {
		if (helperInstance == null) {
			helperInstance = new DBHelper(context);
		}
		return helperInstance;
	}

	/**
	 * Constructor that takes an android Context.
	 * @param context+
	 * @return
	 */
	private DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
		//initialize DAOs
		try {
			getObservationDao();
			getGeometryDao();
			getPropertyDao();
			getAttachmentDao();
			getUserDao();
			getLocationDao();
			getLocationGeometryDao();
			getLocationPropertyDao();
		}
		catch(SQLException sqle) {
			//TODO: handle this...
			sqle.printStackTrace();
		}
		
	}

	@Override
	public void onCreate(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, Observation.class);
			TableUtils.createTable(connectionSource, ObservationGeometry.class);
			TableUtils.createTable(connectionSource, ObservationProperty.class);
			TableUtils.createTable(connectionSource, Attachment.class);

			TableUtils.createTable(connectionSource, User.class);
			TableUtils.createTable(connectionSource, Location.class);
			TableUtils.createTable(connectionSource, LocationGeometry.class);
			TableUtils.createTable(connectionSource, LocationProperty.class);
		} catch (SQLException se) {
			Log.e(LOG_NAME, "Could not create tables.", se);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			TableUtils.dropTable(connectionSource, Observation.class, Boolean.TRUE);

			TableUtils.dropTable(connectionSource, ObservationGeometry.class, Boolean.TRUE);
			TableUtils.dropTable(connectionSource, ObservationProperty.class, Boolean.TRUE);
			TableUtils.dropTable(connectionSource, Attachment.class, Boolean.TRUE);

			TableUtils.dropTable(connectionSource, User.class, Boolean.TRUE);
			TableUtils.dropTable(connectionSource, Location.class, Boolean.TRUE);
			TableUtils.dropTable(connectionSource, LocationGeometry.class, Boolean.TRUE);
			TableUtils.dropTable(connectionSource, LocationProperty.class, Boolean.TRUE);
		} catch (SQLException se) {
			Log.e(LOG_NAME, "could not create table Observation", se);
		}
	}

	@Override
	public void close() {
		super.close();
		helperInstance = null;
	}
	
	/**
	 * Getter for the ObservationDao.
	 * @return This instance's ObservationDao
	 * @throws SQLException
	 */
	public Dao<Observation, Long> getObservationDao() throws SQLException {
		if (observationDao == null) {
			observationDao = getDao(Observation.class);
		}
		return observationDao;
	}
	
	/**
	 * Getter for the GeometryDao
	 * @return This instance's GeometryDao
	 * @throws SQLException
	 */
	public Dao<ObservationGeometry, Long> getGeometryDao() throws SQLException {
		if (geometryDao == null) {
			geometryDao = getDao(ObservationGeometry.class);
		}
		return geometryDao;
	}
	
	/**
	 * Getter for the PropertyDao
	 * @return This instance's PropertyDao
	 * @throws SQLException
	 */
	public Dao<ObservationProperty, Long> getPropertyDao() throws SQLException {
		if (propertyDao == null) {
			propertyDao = getDao(ObservationProperty.class);
		}
		return propertyDao;
	}
	
	/**
	 * Getter for the AttachmentDao
	 * @return This instance's AttachmentDao
	 * @throws SQLException
	 */
	public Dao<Attachment, Long> getAttachmentDao() throws SQLException {
		if (attachmentDao == null) {
			attachmentDao = getDao(Attachment.class);
		}
		return attachmentDao;
	}
	
	/**
	 * Getter for the UserDao
	 * @return This instance's UserDao
	 * @throws SQLException
	 */
	public Dao<User, Long> getUserDao() throws SQLException {
		if (userDao == null) {
			userDao = getDao(User.class);
		}
		return userDao;
	}

	/**
	 * Getter for the LocationDao
	 * @return This instance's LocationDao
	 * @throws SQLException
	 */
	public Dao<Location, Long> getLocationDao() throws SQLException {
		if (locationDao == null) {
			locationDao = getDao(Location.class);
		}
		return locationDao;
	}
	
	/**
	 * Getter for the LocationGeometryDao
	 * @return This instance's LocationGeometryDao
	 * @throws SQLException
	 */
	public Dao<LocationGeometry, Long> getLocationGeometryDao() throws SQLException {
		if (locationGeometryDao == null) {
			locationGeometryDao = getDao(LocationGeometry.class);
		}
		return locationGeometryDao;
	}
	
	/**
	 * Getter for the LocationPropertyDao
	 * @return This instance's LocationPropertyDao
	 * @throws SQLException
	 */
	public Dao<LocationProperty, Long> getLocationPropertyDao() throws SQLException {
		if (locationPropertyDao == null) {
			locationPropertyDao = getDao(LocationProperty.class);
		}
		return locationPropertyDao;
	}
	
}
