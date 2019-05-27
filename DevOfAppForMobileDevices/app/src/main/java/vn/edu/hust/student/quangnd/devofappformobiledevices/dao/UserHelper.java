package vn.edu.hust.student.quangnd.devofappformobiledevices.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import vn.edu.hust.student.quangnd.devofappformobiledevices.objects.User;

public class UserHelper extends SQLiteOpenHelper implements IUserHelper {

    private static final String DATABASE_NAME = "sis.db";
    private static final String TABLE_NAME = "user";
    private static final String USER_NAME = "user_name";
    private static final String PASSWORD = "password";
    private static final String ROLE = "role";
    private static final int SCHEMA_VERSION = 2;
    private static final String TAG = "UserHelper";

    private String createTableSqlQuery = "CREATE TABLE " + TABLE_NAME + " (" +
            USER_NAME + " TEXT PRIMARY KEY," +
            PASSWORD + " TEXT," +
            ROLE + " TEXT)";

    public UserHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTableSqlQuery);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public boolean getStudentUserLogin(User user) {
        String sqlQuery = "SELECT * FROM user WHERE user_name=?";
        String args = user.getUserName();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, new String[]{args});
        if (cursor != null && cursor.moveToFirst()) {
            String userNameGetFromDB = cursor.getString(0);
            String passwordGetFromDB = cursor.getString(1);
            String roleGetFromDB = cursor.getString(2);
            User studentUserFromDB = new User(userNameGetFromDB, passwordGetFromDB, roleGetFromDB);
            boolean encryptedPassword = PasswordUtils.checkPassword(user.getPassword(), studentUserFromDB.getPassword());
            if (user.getUserName().equals(studentUserFromDB.getUserName()) && user.getIsAdmin().equals(studentUserFromDB.getIsAdmin()) && encryptedPassword == true) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean insertUser(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_name", user.getUserName());
        values.put("password", user.getPassword());
        values.put("role", "student");
        long  ins = db.insert(TABLE_NAME, null, values);
        if(ins == -1) return false;
        else return true;
    }

    public boolean seedUserLogin() {
        SQLiteDatabase db = this.getReadableDatabase();
        PasswordUtils passwordUtils = new PasswordUtils();
        String password = "20146574";
        String encryptPass = passwordUtils.encryptPassword(password);
        ContentValues values = new ContentValues();
        values.put(USER_NAME, "20146574");
        values.put(PASSWORD, encryptPass);
        values.put(ROLE, "student");
        long checkSeed = db.insert(TABLE_NAME, null, values);
        if(checkSeed == -1) return false;
        else return true;
    }
}
