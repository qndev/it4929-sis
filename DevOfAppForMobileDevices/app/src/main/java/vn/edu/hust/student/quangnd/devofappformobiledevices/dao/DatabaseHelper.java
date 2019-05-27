package vn.edu.hust.student.quangnd.devofappformobiledevices.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
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

    // Register table, columns
    private static final String REGISTER_TABLE = "register";
    private static final String REGISTER_ID = "id";

    // Class table, columns
    private static final String CLASS_TABLE = "class";
    private static final String ID = "id";
    private static final String CLASS_ID = "class_id";
    private static final String START_TIME = "start_time";
    private static final String END_TIME = "class_id";
    private static final String DAY = "day";
    private static final String WEEK = "week";
    private static final String CLASS_TYPE = "class_type";
    private static final String GROUP = "group_class";
    private static final String ROOM = "room";
    private static final String SEMESTER = "semester";
    private static final String REGISTERED = "total_registered";
    private static final String MAX_REGISTER = "max_register";
    private static final String EXPIRATION_DATE = "expiration_date";

    // Subject table, columns
    private static final String SUBJECT_TABLE = "subject";
    private static final String SUBJECT_ID = "subject_id";
    private static final String SUBJECT_NAME = "subject_name";
    private static final String DURATION = "duration";
    private static final String CREDIT = "credit";
    private static final String TUITION_CREDIT = "tuition_credit";
    private static final String WEIGHT = "weight";

    private static final int SCHEMA_VERSION = 2;
    private static final String TAG = "DatabaseHelper";
    private Context context;

    private String createUserTable = "CREATE TABLE IF NOT EXISTS " + USER_TABLE + " (" +
            USER_NAME + " TEXT PRIMARY KEY," +
            PASSWORD + " TEXT," +
            ROLE + " TEXT)";

    private String createStudentTable = "CREATE TABLE IF NOT EXISTS " + STUDENT_TABLE + " (" +
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

    private String createClassTable = "CREATE TABLE IF NOT EXISTS " + SUBJECT_TABLE + " (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            CLASS_ID + " TEXT," +
            SUBJECT_ID + " TEXT," +
            START_TIME + " TEXT," +
            END_TIME + " TEXT," +
            DAY + " TEXT," +
            WEEK + " TEXT," +
            CLASS_TYPE + " TEXT," +
            GROUP + " TEXT," +
            ROOM + " TEXT," +
            SEMESTER + " TEXT," +
            REGISTERED + " TEXT," +
            MAX_REGISTER + " TEXT," +
            EXPIRATION_DATE + " TEXT," +
            " FOREIGN KEY ("+ SUBJECT_ID +") REFERENCES " + SUBJECT_TABLE + "("+ SUBJECT_ID +"))";

    private String createRegisterTable = "CREATE TABLE IF NOT EXISTS " + REGISTER_TABLE + " (" +
            REGISTER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            STUDENT_ID + " TEXT," +
            CLASS_ID + " TEXT," +
            " FOREIGN KEY ("+ STUDENT_ID +") REFERENCES " + STUDENT_TABLE + "("+ STUDENT_ID +")," +
            " FOREIGN KEY ("+ CLASS_ID +") REFERENCES " + CLASS_TABLE + "("+ CLASS_ID +"))";

    private String createSubjectTable = "CREATE TABLE IF NOT EXISTS " + SUBJECT_TABLE + " (" +
            SUBJECT_ID + " TEXT PRIMARY KEY," +
            SUBJECT_NAME + " TEXT," +
            DURATION + " TEXT," +
            CREDIT + " TEXT," +
            TUITION_CREDIT + " TEXT," +
            WEIGHT + " TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createUserTable);
        db.execSQL(createStudentTable);
        db.execSQL(createClassTable);
        db.execSQL(createRegisterTable);
        db.execSQL(createSubjectTable);
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
                STUDENT_ID,
                NAME,
                DATE_OF_BIRTH,
                STUDENT_CLASS,
                EDU_PROGRAM,
                TRAINING,
                STATE,
                YEAR,
                DEPARTMENT,
                EMAIL
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

    @Override
    public long insertStudent(Student student) throws SQLiteException {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(STUDENT_ID, student.getStudentId());
        values.put(NAME, student.getName());
        values.put(DATE_OF_BIRTH, student.getDateOfBirth());
        values.put(STUDENT_CLASS, student.getStudentClass());
        values.put(EDU_PROGRAM, student.getEduProgram());
        values.put(TRAINING, student.getTrainingSystem());
        values.put(STATE, student.getState());
        values.put(YEAR, student.getYear());
        values.put(DEPARTMENT, student.getDepartment());
        values.put(EMAIL, student.getEmail());
        // values.put(TUITION, student.getStudentId());

        // Insert the new row, returning the primary key value of the new row
        long insStudent = db.insert(STUDENT_TABLE, null, values);
        db.close();
        return insStudent;
    }

    @Override
    public int updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(NAME, student.getName());
        values.put(DATE_OF_BIRTH, student.getDateOfBirth());
        values.put(EMAIL, student.getEmail());

        // Which row to update, based on the title
        // UPDATE student(name, date_of_birth, email) VALUES(?, ?, ?) WHERE student_id = ?;
        String selection = STUDENT_ID + " LIKE ?";
        String[] selectionArgs = {student.getStudentId()};

        int count = db.update(STUDENT_TABLE, values, selection, selectionArgs);

        return count;
    }

    @Override
    public int deleteStudent(String studentId) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Define 'where' part of query.
        String selection = STUDENT_ID + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = {studentId};
        // Issue SQL statement.
        int deletedStudentRows = db.delete(STUDENT_TABLE, selection, selectionArgs);
        db.close();
        return deletedStudentRows;
    }

    // +++++++++++++++++++++++++++++++ Seed data ++++++++++++++++++++++++++++++++++++
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
        String password = "admin";
        String encryptPass = passwordUtils.encryptPassword(password);
        ContentValues values = new ContentValues();
        values.put(USER_NAME, "admin");
        values.put(PASSWORD, encryptPass);
        values.put(ROLE, "admin");

        long checkSeed = db.insert(USER_TABLE, null, values);
        return (checkSeed == -1) ? false : true;
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
        if (ins == -1) {
            return false;
        } else {
            return true;
        }
    }
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
