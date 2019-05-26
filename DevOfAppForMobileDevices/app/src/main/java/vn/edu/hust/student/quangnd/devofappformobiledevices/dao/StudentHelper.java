package vn.edu.hust.student.quangnd.devofappformobiledevices.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import vn.edu.hust.student.quangnd.devofappformobiledevices.objects.Student;

public class StudentHelper extends SQLiteOpenHelper implements IStudentHelper{

    private static final String DATABASE_NAME = "sis.db";
    private static final String TABLE_NAME = "student";
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
    private static final int SCHEMA_VERSION = 1;
    private static final String TAG = "StudentHelper";

    private String createStudentTable = "CREATE TABLE " + TABLE_NAME + " (" +
            STUDENT_ID + " TEXT PRIMARY KEY," +
            NAME + " TEXT," +
            DATE_OF_BIRTH + " NUMERIC," +
            STUDENT_CLASS + " TEXT," +
            EDU_PROGRAM + " TEXT," +
            TRAINING + " TEXT," +
            STATE + " TEXT," +
            YEAR + " TEXT," +
            DEPARTMENT + " TEXT," +
            EMAIL + " TEXT," +
            TUITION + " REAL)";

    public StudentHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
    }

    @Override
    public void getStudentProfile(String studentId) {

    }

    @Override
    public Student getStudentById(String studentId) {
        return null;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createStudentTable);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
