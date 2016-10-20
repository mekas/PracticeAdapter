package com.example.pustikom.adapterplay.com.example.pustikom.com.example.pustikom.db;

import android.provider.BaseColumns;

/**
 * Created by pustikom on 20/10/16.
 */

public final class StudentEntry implements BaseColumns{
    public static final String TABLE_NAME="student";
    public static final String _ID = BaseColumns._ID;
    public static final String COLUMN_NIM="nim";
    public static final String COLUMN_NAME="name";
    public static final String COLUMN_MAIL="mail";
    public static final String COLUMN_PHONE="phone";
    public static final String COLUMN_GENDER="gender";
    public static final int GENDER_MALE=0;
    public static final int GENDER_FEMALE=1;

}
