package com.example.chozabredtttttttto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

    public class DatabaseHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "users.db";
        private static final int DATABASE_VERSION = 2;

        private static final String TABLE_USERS = "users";
        private static final String COLUMN_ID = "id";
        private static final String COLUMN_USERNAME = "username";
        private static final String COLUMN_PASSWORD = "password";
        private static final String COLUMN_LANGUAGE = "language";

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String createTable = "CREATE TABLE " + TABLE_USERS + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_USERNAME + " TEXT, "
                    + COLUMN_PASSWORD + " TEXT, "
                    + COLUMN_LANGUAGE + " TEXT)"; // Добавляем язык
            db.execSQL(createTable);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
            //onCreate(db);
            if (oldVersion < 2) {
                db.execSQL("ALTER TABLE " + TABLE_USERS + " ADD COLUMN " + COLUMN_LANGUAGE + " TEXT");
            }
        }

        public boolean addUser(String username, String password, String language) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_USERNAME, username);
            values.put(COLUMN_PASSWORD, password);
            values.put(COLUMN_LANGUAGE, language);

            long result = db.insert(TABLE_USERS, null, values);
            return result != -1; // Вернет true, если добавление прошло успешно
        }

        public boolean checkUser(String username, String password) {
            SQLiteDatabase db = this.getReadableDatabase();
            String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + "=? AND " + COLUMN_PASSWORD + "=?";
            Cursor cursor = db.rawQuery(query, new String[]{username, password});

            boolean exists = cursor.getCount() > 0;
            cursor.close();
            return exists;
        }
        // Проверяем пользователя и получаем язык
        public final String getUserLanguage(String username, String password) {
            SQLiteDatabase db = this.getReadableDatabase();
            String query = "SELECT " + COLUMN_LANGUAGE + " FROM " + TABLE_USERS +
                    " WHERE " + COLUMN_USERNAME + "=? AND " + COLUMN_PASSWORD + "=?";
            Cursor cursor = db.rawQuery(query, new String[]{username, password});

            String language = null;
            if (cursor.moveToFirst()) {
                language = cursor.getString(0); // Извлекаем язык
            }
            cursor.close();
            return language; // Вернем null, если пользователь не найден
        }
    }


