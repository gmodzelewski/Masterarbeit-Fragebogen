package de.modzelewski.nasatlx;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.*;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements OnClickListener {
	private static final String tag = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.button_done).setOnClickListener(this);
		findViewById(R.id.button_clear).setOnClickListener(this);
		findViewById(R.id.action_export).setOnClickListener(this);

		restoreIds();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == R.id.action_export2) {
			export();
			return true;
		} else if(item.getItemId() == R.id.action_clear) {
			clear();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.button_done) {
			done();
		} else if(v.getId() == R.id.button_clear) {
			reset();
		} else if(v.getId() == R.id.action_export) {
			export();
		}
	}

	private void reset() {
		clearSelections();
		findViewById(R.id.edit_eid).requestFocus();
	}

	private void done() {
		String participantId = ((EditText)findViewById(R.id.edit_pid)).getText().toString();
		String entryId = ((EditText)findViewById(R.id.edit_eid)).getText().toString();
		int sexId = ((RadioGroup)findViewById(R.id.group_sex)).getCheckedRadioButtonId();
		int glassesId = ((RadioGroup)findViewById(R.id.group_glasses)).getCheckedRadioButtonId();

		int sex, glasses, videochannel, support;
		String age = ((EditText)findViewById(R.id.edit_age)).getText().toString();
		int stability, reactiontime, rating;
		String something_else = ((EditText)findViewById(R.id.something_else)).getText().toString();

		int videochannelId = ((RadioGroup)findViewById(R.id.group_videochannel)).getCheckedRadioButtonId();
		int supportId = ((RadioGroup)findViewById(R.id.group_support)).getCheckedRadioButtonId();
		int stabilityId = ((RadioGroup)findViewById(R.id.group_stability)).getCheckedRadioButtonId();
		int reactiontimeId = ((RadioGroup)findViewById(R.id.group_reactiontime)).getCheckedRadioButtonId();
		int ratingId = ((RadioGroup)findViewById(R.id.group_rating)).getCheckedRadioButtonId();


		switch(sexId) {
			case R.id.sex_male:
				sex = 1;
				break;
			case R.id.sex_female:
				sex = 0;
				break;
			default:
				sex = -1;
				break;
		}

		switch(glassesId) {
			case R.id.glasses_yes:
				glasses = 1;
				break;
			case R.id.glasses_no:
				glasses = 0;
				break;
			default:
				glasses = -1;
				break;
		}


		switch(videochannelId) {
			case R.id.videochannel_1:
				videochannel = 1;
				break;
			case R.id.videochannel_2:
				videochannel = 2;
				break;
			case R.id.videochannel_3:
				videochannel = 3;
				break;
			case R.id.videochannel_4:
				videochannel = 4;
				break;
			case R.id.videochannel_5:
				videochannel = 5;
				break;
			case R.id.videochannel_6:
				videochannel = 6;
				break;
			case R.id.videochannel_7:
				videochannel = 7;
				break;
			default:
				videochannel = -1;
				break;
		}

		switch(supportId) {
			case R.id.support_1:
				support = 1;
				break;
			case R.id.support_2:
				support = 2;
				break;
			case R.id.support_3:
				support = 3;
				break;
			case R.id.support_4:
				support = 4;
				break;
			case R.id.support_5:
				support = 5;
				break;
			case R.id.support_6:
				support = 6;
				break;
			case R.id.support_7:
				support = 7;
				break;
			default:
				support = -1;
				break;
		}



//		Georg: stability, reactiontime, rating
		switch(stabilityId) {
			case R.id.stability_1:
				stability = 1;
				break;
			case R.id.stability_2:
				stability = 2;
				break;
			case R.id.stability_3:
				stability = 3;
				break;
			case R.id.stability_4:
				stability = 4;
				break;
			case R.id.stability_5:
				stability = 5;
				break;
			case R.id.stability_6:
				stability = 6;
				break;
			case R.id.stability_7:
				stability = 7;
				break;
			default:
				stability = -1;
				break;
		}

		switch(reactiontimeId) {
			case R.id.reactiontime_1:
				reactiontime = 1;
				break;
			case R.id.reactiontime_2:
				reactiontime = 2;
				break;
			case R.id.reactiontime_3:
				reactiontime = 3;
				break;
			case R.id.reactiontime_4:
				reactiontime = 4;
				break;
			case R.id.reactiontime_5:
				reactiontime = 5;
				break;
			case R.id.reactiontime_6:
				reactiontime = 6;
				break;
			case R.id.reactiontime_7:
				reactiontime = 7;
				break;
			default:
				reactiontime = -1;
				break;
		}

		switch(ratingId) {
			case R.id.rating_1:
				rating = 1;
				break;
			case R.id.rating_2:
				rating = 2;
				break;
			case R.id.rating_3:
				rating = 3;
				break;
			case R.id.rating_4:
				rating = 4;
				break;
			case R.id.rating_5:
				rating = 5;
				break;
			case R.id.rating_6:
				rating = 6;
				break;
			case R.id.rating_7:
				rating = 7;
				break;
			default:
				rating = -1;
				break;
		}

// GEORG ENDE



		saveIds(participantId, entryId);

//		saveRecord(participantId, entryId, mental, physical, temporal, performance, effort, frustration);
		saveRecord(participantId, entryId, videochannel, support, age, sex, glasses, stability, reactiontime, rating, something_else);
		reset();
		((EditText)findViewById(R.id.edit_eid)).setText("");
	}

	private void saveIds(String participantId, String entryId) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
		SharedPreferences.Editor edit = pref.edit();
		if(participantId != null && !participantId.equals(""))
			edit.putString("participantId", participantId);
		else
			edit.remove("participantId");
		if(entryId != null && !entryId.equals(""))
			edit.putString("entryId", entryId);
		else
			edit.remove("entryId");

		edit.commit();
	}

	private void restoreIds() {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
		String participantId = pref.getString("participantId", null);
		String entryId = pref.getString("entryId", null);

		if(participantId != null)
			((EditText)findViewById(R.id.edit_pid)).setText(participantId);

		if(entryId != null)
			((EditText)findViewById(R.id.edit_eid)).setText(entryId);
	}

	private void saveRecord(String participantId, String entryId, int videochannel, int support,
	                        String age, int sex, int glasses, int stability, int reactiontime, int rating, String something_else) {

		TLXSQLiteHelper helper = new TLXSQLiteHelper(this);
		SQLiteDatabase db = helper.getWritableDatabase();

		ContentValues values = new ContentValues();

		String _participantId = participantId.replace(',','_');
		String _entryId = entryId.replace(',','_');

		values.put("participant", _participantId);
		values.put("entry", _entryId);
		values.put("videochannel", videochannel);
		values.put("support", support);

		values.put("age", age);
		values.put("sex", sex);
		values.put("glasses", glasses);
		values.put("stability", stability);
		values.put("reactiontime", reactiontime);
		values.put("rating", rating);
		values.put("somethingelse", something_else);

		db.beginTransaction();
		try {
			db.insert("entries", null, values);
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}

		Toast.makeText(this, "Response saved!", Toast.LENGTH_SHORT).show();
	}

	private void clearSelections() {
		((RadioGroup)findViewById(R.id.group_videochannel)).clearCheck();
		((RadioGroup)findViewById(R.id.group_support)).clearCheck();

		((EditText)findViewById(R.id.edit_age)).setText("");
		((EditText)findViewById(R.id.something_else)).setText("");
		((RadioGroup)findViewById(R.id.group_sex)).clearCheck();
		((RadioGroup)findViewById(R.id.group_glasses)).clearCheck();
		((RadioGroup)findViewById(R.id.group_stability)).clearCheck();
		((RadioGroup)findViewById(R.id.group_reactiontime)).clearCheck();
		((RadioGroup)findViewById(R.id.group_rating)).clearCheck();
	}

	private void clear() {
		ClearDialogFragment f = new ClearDialogFragment();
		f.show(this.getSupportFragmentManager(), "clear_dialog");
	}

	private void doClear() {
		TLXSQLiteHelper helper = new TLXSQLiteHelper(this);
		SQLiteDatabase db = helper.getWritableDatabase();
		db.beginTransaction();
		try {
			db.delete("entries", null, null);
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}

		Toast.makeText(this, "Responses cleared.", Toast.LENGTH_SHORT).show();
	}

	public static class ClearDialogFragment extends DialogFragment {
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

			builder.setMessage("Clear *ALL* data?  This is irreversible")
					.setPositiveButton("Clear Data", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							((MainActivity) getActivity()).doClear();
						}
					})
					.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Toast.makeText(getActivity(), "Cancelled.", Toast.LENGTH_SHORT).show();
						}
					});

			return builder.create();
		}
	}

	private void export() {
		TLXSQLiteHelper helper = new TLXSQLiteHelper(this);
		SQLiteDatabase db = helper.getReadableDatabase();
		StringBuffer buffer = new StringBuffer();
		buffer.append("timestamp,participant_id,entry_id,videochannel," +
				"support," +
				"age,sex,glasses,stability,reactiontime,rating,somethingelse\n");

//		sex: 1 == male, 0 == female

		Cursor c = db.query("entries", null, null, null, null, null, "timestamp");
		c.moveToFirst();
		while(!c.isAfterLast()) {
//			buffer.append(String.format("%s,%s,%s,%d,%d,%d,%d,%d,%d\n",
//					c.getString(1), c.getString(2), c.getString(3),
//					c.getInt(4), c.getInt(5), c.getInt(6),
//					c.getInt(7), c.getInt(8), c.getInt(9)));
			buffer.append(String.format("%s,%s,%s,%d,%d,%s,%d,%d,%d,%d,%d,%s\n",
					c.getString(1), c.getString(2), c.getString(3),
					c.getInt(4), c.getInt(5), c.getString(6),
					c.getInt(7), c.getInt(8), c.getInt(9),
					c.getInt(10), c.getInt(11), c.getString(12)));
			c.moveToNext();
		}
		c.close();

		Intent i = new Intent(Intent.ACTION_SEND);
		i.setType("message/rfc822");
		i.putExtra(Intent.EXTRA_SUBJECT, "TLX Response Data");
		i.putExtra(Intent.EXTRA_TEXT, buffer.toString());
		try {
			startActivity(Intent.createChooser(i, "Send mail..."));
		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
		}
	}

	public void radioClick(View v) {
		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
	}
}