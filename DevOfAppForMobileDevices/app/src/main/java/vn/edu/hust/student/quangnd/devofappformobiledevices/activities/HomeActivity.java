package vn.edu.hust.student.quangnd.devofappformobiledevices.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import vn.edu.hust.student.quangnd.devofappformobiledevices.R;
import vn.edu.hust.student.quangnd.devofappformobiledevices.dao.DatabaseHelper;
import vn.edu.hust.student.quangnd.devofappformobiledevices.objects.Student;

public class HomeActivity extends AppCompatActivity {

    public static final String BD_STUDENT_ID = "st_id";
    public static final String BD_NAME = "st_name";
    public static final String BD_DATE = "st_date";
    public static final String BD_CLASS = "st_class";
    public static final String BD_PROGRAM = "st_program";
    public static final String BD_TRAINING_SYSTEM = "st_training_system";
    public static final String BD_STATE = "st_state";
    public static final String BD_YEAR = "st_year";
    public static final String BD_DEPARTMENT = "st_department";
    public static final String BD_EMAIL = "st_email";
    public static final String BUNDLE = "bundle";
    private CardView cardViewProfile;
    private TextView textViewProfile;
    private DatabaseHelper databaseHelper;
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        databaseHelper = new DatabaseHelper(this);
        student = new Student();
        cardViewProfile = (CardView) findViewById(R.id.user_profile_card_id);
        textViewProfile = (TextView) findViewById(R.id.text_view_profile);
        Intent intent = getIntent();
        final String studentId = intent.getStringExtra(LoginActivity.USER_ID);
        textViewProfile.setText(studentId);
        cardViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student = databaseHelper.getStudentById(studentId);
                bdBundle(student);
                Log.d("Profile User", "Loadesd!");
                Toast.makeText(HomeActivity.this, "Profile User!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void bdBundle(Student student) {
        Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(BD_STUDENT_ID, student.getStudentId());
        bundle.putString(BD_NAME, student.getName());
        bundle.putString(BD_DATE, student.getDateOfBirth());
        bundle.putString(BD_CLASS, student.getStudentClass());
        bundle.putString(BD_PROGRAM, student.getEduProgram());
        bundle.putString(BD_TRAINING_SYSTEM, student.getTrainingSystem());
        bundle.putString(BD_STATE, student.getState());
        bundle.putString(BD_YEAR, student.getYear());
        bundle.putString(BD_DEPARTMENT, student.getDepartment());
        bundle.putString(BD_EMAIL, student.getEmail());
        intent.putExtra(BUNDLE, bundle);
        startActivity(intent);
    }
}
