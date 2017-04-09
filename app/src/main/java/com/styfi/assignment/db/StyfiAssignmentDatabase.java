package com.styfi.assignment.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.styfi.assignment.application.StyfiAssignmentApplication;
import com.styfi.assignment.constants.StyfiAssignmentDatabaseConstants;
import com.styfi.assignment.model.ManufacturerData;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * this class is used to create the database and the tables contained in it.
 *
 * @author Kartik
 */
public class StyfiAssignmentDatabase extends OrmLiteSqliteOpenHelper {

    private static final String TAG = StyfiAssignmentDatabase.class.getSimpleName();

    private static final String DATABASE_NAME = "StyfiAssignmentDatabase.sqlite";

    private ConnectionSource connection;

    private static List<Class> tableList;

    /**
     * this constructor is used to create the database and add tables to the database. Add to the tableList parameter to create new tables
     *
     * @param context the application context to be used by the class
     */
    public StyfiAssignmentDatabase(Context context) {
        super(context, DATABASE_NAME, null, StyfiAssignmentDatabaseConstants.DB_VERSION);
        connection = getConnectionSource();
        tableList = new ArrayList<>();
        tableList.add(ManufacturerData.class);
    }

    /*
     * This method create data for start
     */
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connection) {
        try {
            for (Class classToCreate : tableList) {
                TableUtils.createTable(connection, classToCreate);
            }
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage(), e);
        }

    }

    /*
     * this method is called when you update database version
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    public ConnectionSource getConnection() {
        return connection;
    }

    public void setConnection(ConnectionSource connection) {
        this.connection = connection;
    }

    /**
     * Clear the database
     */
    public static void resetDatabase() {
        Log.d(TAG, "Clearing the tables");
        try {
            for (Class classToDrop : tableList) {
                StyfiAssignmentDatabase.drop(classToDrop);
            }
            for (Class classToCreate : tableList) {
                StyfiAssignmentDatabase.create(classToCreate);
            }
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    /**
     * Drop the table with given class
     *
     * @param classType the class to drop the table of
     * @throws SQLException
     */
    public static void drop(Class classType) throws SQLException {
        TableUtils.dropTable(StyfiAssignmentApplication.getDatabase().getConnection(), classType, false);
    }

    /**
     * Create the table with given class
     *
     * @param classType the class to create the table of
     * @throws SQLException
     */
    public static void create(Class classType) throws SQLException {
        TableUtils.createTable(StyfiAssignmentApplication.getDatabase().getConnection(), classType);
    }
}
