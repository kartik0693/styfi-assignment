package com.styfi.assignment.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.styfi.assignment.constants.StyfiAssignmentDatabaseConstants;
import com.styfi.assignment.db.Model;

/**
 * Created by Kartik on 9/4/17.
 */

@DatabaseTable(tableName = StyfiAssignmentDatabaseConstants.TABLE_MANUFACTURER_DATA)
public class ManufacturerData extends Model {

    private static final String COLUMN_MANUFACTURER_ID = "manufacturerID";
    private static final String COLUMN_MANUFACTURER_DATA = "manufacturerData";

    @DatabaseField(id = true, columnName = COLUMN_MANUFACTURER_ID)
    @SerializedName("manufacturer_id")
    private String manufacturerID;

    @DatabaseField(columnName = COLUMN_MANUFACTURER_DATA)
    @SerializedName("manufacturer_data")
    private String manufacturerData;

    public String getManufacturerID() {
        return manufacturerID;
    }

    public String getManufacturerData() {
        return manufacturerData;
    }
}
