package vn.edu.hust.student.quangnd.devofappformobiledevices.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import vn.edu.hust.student.quangnd.devofappformobiledevices.objects.Student;
import vn.edu.hust.student.quangnd.devofappformobiledevices.objects.User;

public class DatabaseHelper extends SQLiteOpenHelper implements IUserHelper, IStudentHelper {

    private static final String DATABASE_NAME = "sis.db";
    // User table, columns
    private static final String USER_TABLE = "user";
    private static final String USER_NAME = "user_name";
    private static final String PASSWORD = "password";
    private static final String ROLE = "role";
    // Student table, columns
    private static final String STUDENT_TABLE = "student";
    private static final String STUDENT_ID = "student_id";
    private static final String NAME = "name";
    private static final String DATE_OF_BIRTH = "date_of_birth";
    private static final String STUDENT_CLASS = "student_class";
    private static final String EDU_PROGRAM = "edu_program";
    private static final String TRAINING = "training_system";
    private static final String STATE = "state";
    private static final String YEAR = "year";
    private static final String DEPARTMENT = "department";
    private static final String EMAIL = "email";
    private static final String TUITION = "tuition_unit";
    private static final int SCHEMA_VERSION = 2;
    private static final String TAG = "DatabaseHelper";
    private Context context;

    private String createTableSqlQuery = "CREATE TABLE " + USER_TABLE + " (" +
            USER_NAME + " TEXT PRIMARY KEY," +
            PASSWORD + " TEXT," +
            ROLE + " TEXT)";

    private String createStudentTable = "CREATE TABLE " + STUDENT_TABLE + " (" +
            STUDENT_ID + " TEXT PRIMARY KEY," +
            NAME + " TEXT," +
            DATE_OF_BIRTH + " TEXT," +
            STUDENT_CLASS + " TEXT," +
            EDU_PROGRAM + " TEXT," +
            TRAINING + " TEXT," +
            STATE + " TEXT," +
            YEAR + " TEXT," +
            DEPARTMENT + " TEXT," +
            EMAIL + " TEXT," +
            TUITION + " REAL)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTableSqlQuery);
        db.execSQL(createStudentTable);
        Log.d(TAG, "onCreated!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE);
        onCreate(db);
        Log.d(TAG, "onDeleted!");
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
            if (user.getUserName().equals(studentUserFromDB.getUserName())
                    && user.getIsAdmin().equals(studentUserFromDB.getIsAdmin())
                    && encryptedPassword == true) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public Student getStudentById(String studentId) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] columns = {
                STUDENT_ID, NAME, DATE_OF_BIRTH,
                STUDENT_CLASS, EDU_PROGRAM, TRAINING,
                STATE, YEAR, DEPARTMENT, EMAIL
        };

        // Filter results WHERE "student_id" = "studentId"
        String selection = STUDENT_ID + " =?";
        String[] selectionArgs = {studentId};

        Cursor cursor = db.query(STUDENT_TABLE, columns, selection, selectionArgs,
                null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Student student = new Student(cursor.getString(0),
                cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4),
                cursor.getString(5), cursor.getString(6),
                cursor.getString(7), cursor.getString(8),
                cursor.getString(9)
        );
        return student;
    }

    public boolean insertUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_name", user.getUserName());
        values.put("password", user.getPassword());
        values.put("role", "student");
        long ins = db.insert(USER_TABLE, null, values);
        if (ins == -1) return false;
        else return true;
    }

    public boolean seedUserLogin() {
        SQLiteDatabase db = this.getWritableDatabase();
        PasswordUtils passwordUtils = new PasswordUtils();
        String password = "20146574";
        String encryptPass = passwordUtils.encryptPassword(password);
        ContentValues values = new ContentValues();
        values.put(USER_NAME, "20146574");
        values.put(PASSWORD, encryptPass);
        values.put(ROLE, "student");

        long checkSeed = db.insert(USER_TABLE, null, values);
        if (checkSeed == -1) return false;
        else return true;
    }

    @Override
    public void getStudentProfile(String studentId) {

    }

    public boolean seedStudentInfo() {
        SQLiteDatabase db = this.getWritableDatabase();
        String studentId = "20146574";
        String name = "Nguyen Dinh Quang";
        String dateOfBirth = "1996-04-15";
        String studentClass = "CN-CNTT01 K59";
        String eduProgram = "CNPM";
        String trainingSystem = "20146574";
        String state = "Hoc";
        String year = "2014";
        String department = "Cong nghe thong tin";
        String email = "quangnd.hust@gmail.com";
        Double tuitionUnit = 230.0;
        ContentValues values = new ContentValues();
        values.put(STUDENT_ID, studentId);
        values.put(NAME, name);
        values.put(DATE_OF_BIRTH, dateOfBirth);
        values.put(STUDENT_CLASS, studentClass);
        values.put(EDU_PROGRAM, eduProgram);
        values.put(TRAINING, trainingSystem);
        values.put(STATE, state);
        values.put(YEAR, year);
        values.put(DEPARTMENT, department);
        values.put(EMAIL, email);
        values.put(TUITION, tuitionUnit);

        long ins = db.insert(STUDENT_TABLE, null, values);
        if (ins == -1) return false;
        else return true;
    }
}
