/**
 *
 */

package com.styfi.assignment.db;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

/**
 * this class should be extended by all the models which are to be inserted into the database
 *
 * @author Kartik
 */
public abstract class Model {

    public static final String COLUMN_VERSION = "version";
    public static final String COLUMN_ACTIVE = "active";

    /**
     * the version of the Model instance available. Useful for client - server applications
     * where the client needs to maintain the version of the data that it gets from the server end.
     */
    @DatabaseField(columnName = COLUMN_VERSION, canBeNull = true)
    private int version;

    /**
     * indicates whether this Model instance is active or no. Encourages soft delete among Model instances
     */
    @DatabaseField(columnName = COLUMN_ACTIVE, canBeNull = true, dataType = DataType.BOOLEAN, defaultValue = "1")
    private boolean active = true;

    public Model() {
        super();
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}