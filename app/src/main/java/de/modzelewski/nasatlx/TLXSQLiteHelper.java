package de.modzelewski.nasatlx;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TLXSQLiteHelper extends SQLiteOpenHelper {
	private static final String tag = "TLXSQLiteHelper";

	private static final String DATABASE_NAME = "tlx.db";
	private static final int DATABASE_VERSION = 4;

	private static final String TABLE_NAME = "entries";
	private static final String TABLE_SCHEMA =
			"(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
					"timestamp DATETIME DEFAULT CURRENT_TIMESTAMP, " +
					"participant TEXT NOT NULL, " +
					"entry TEXT NOT NULL, " +
					"videochannel INTEGER, " +
					"support INTEGER, " +
					"age TEXT NOT NULL, " +
					"sex INTEGER, " +
					"glasses INTEGER, " +
					"stability INTEGER, " +
					"reactiontime INTEGER, " +
					"rating INTEGER, " +
					"somethingelse TEXT);";

	private static final String DATABASE_CREATE =
			"CREATE TABLE " + TABLE_NAME + TABLE_SCHEMA;

	public TLXSQLiteHelper(Context context) {
		super(context,DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		Log.w(tag, "(New version detected; Clearing db)");
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(database);
	}
}