package shaz.practice_contentprovider.persistance.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.provider.BaseColumns;

/**
 * Created by ${Shahbaz} on 16-11-2017
 */

@Entity(tableName = UserEntity.TABLE_NAME)
public class UserEntity {

    public static final String TABLE_NAME = "UserEntity";
    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_USER_NAME = "name";
    public static final String COLUMN_USER_EMAIL = "email";
    public static final String COLUMN_USER_ADDRESS = "address";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID, collate = ColumnInfo.BINARY, index = true)
    private long ID;
    @ColumnInfo(name = COLUMN_USER_NAME, typeAffinity = ColumnInfo.TEXT, index = true)
    private String name;
    @ColumnInfo(name = COLUMN_USER_EMAIL, typeAffinity = ColumnInfo.TEXT, index = true)
    private String email;
    @ColumnInfo(name = COLUMN_USER_ADDRESS, typeAffinity = ColumnInfo.TEXT)
    private String address;

    public UserEntity(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
