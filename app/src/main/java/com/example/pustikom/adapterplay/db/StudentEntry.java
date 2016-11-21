package com.example.pustikom.adapterplay.db;

import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by pustikom on 21/11/16.
 */

public final class StudentEntry implements BaseColumns {
    public static final String TABLE_NAME="student";
    public static final String _ID = BaseColumns._ID;
    public static final String COLUMN_NIM="nim";
    public static final String COLUMN_NAME="name";
    public static final String COLUMN_MAIL="mail";
    public static final String COLUMN_PHONE="phone";
    public static final String COLUMN_GENDER="gender";
    public static final int CODE_MALE =0;
    public static final int CODE_FEMALE =1;
    public static final String STRING_MALE ="laki-laki";
    public static final String STRING_FEMALE = "Perempuan";
    private static final String[] gender_option ={STRING_MALE, STRING_FEMALE};
    public static final ArrayList<String> GENDERS = new ArrayList<String>(Arrays.asList(gender_option));
}
