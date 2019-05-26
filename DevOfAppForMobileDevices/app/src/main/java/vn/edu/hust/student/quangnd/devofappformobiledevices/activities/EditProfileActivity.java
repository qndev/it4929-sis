package vn.edu.hust.student.quangnd.devofappformobiledevices.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import vn.edu.hust.student.quangnd.devofappformobiledevices.R;

public class EditProfileActivity extends AppCompatActivity {
    private EditText editTextStudentId, editTextName, editTextClass, editTextDate, editTextProgram,
            editTextSystem, editTextState, editTextYear, editTextDepartment, editTextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        /*editTextStudentId = (EditText) findViewById(R.id.text_id_student);
        editTextStudentId.setEnabled(false);*/
        editTextStudentId = (EditText) findViewById(R.id.text_id_student);
        editTextName = (EditText) findViewById(R.id.text_name);
        editTextDate = (EditText) findViewById(R.id.text_date);
        editTextClass = (EditText) findViewById(R.id.text_class);
        editTextProgram = (EditText) findViewById(R.id.text_program);
        editTextSystem = (EditText) findViewById(R.id.text_system);
        editTextState = (EditText) findViewById(R.id.text_state);
        editTextYear = (EditText) findViewById(R.id.text_year);
        editTextDepartment = (EditText) findViewById(R.id.text_department);
        editTextEmail = (EditText) findViewById(R.id.text_email);

        setDataByBundle();
    }

    private void setDataByBundle() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra(HomeActivity.BUNDLE);
            if (bundle != null) {
                editTextStudentId.setText(bundle.getString(HomeActivity.BD_STUDENT_ID));
                editTextName.setText(bundle.getString(HomeActivity.BD_NAME));
                editTextDate.setText(bundle.getString(HomeActivity.BD_DATE));
                editTextClass.setText(bundle.getString(HomeActivity.BD_CLASS));
                editTextProgram.setText(bundle.getString(HomeActivity.BD_PROGRAM));
                editTextSystem.setText(bundle.getString(HomeActivity.BD_TRAINING_SYSTEM));
                editTextState.setText(bundle.getString(HomeActivity.BD_STATE));
                editTextYear.setText(bundle.getString(HomeActivity.BD_YEAR));
                editTextDepartment.setText(bundle.getString(HomeActivity.BD_DEPARTMENT));
                editTextEmail.setText(bundle.getString(HomeActivity.BD_EMAIL));
            } else {
                editTextStudentId.setText(intent.getStringExtra(HomeActivity.BD_STUDENT_ID));
                editTextName.setText(intent.getStringExtra(HomeActivity.BD_NAME));
                editTextDate.setText(intent.getStringExtra(HomeActivity.BD_DATE));
                editTextClass.setText(intent.getStringExtra(HomeActivity.BD_CLASS));
                editTextProgram.setText(intent.getStringExtra(HomeActivity.BD_PROGRAM));
                editTextSystem.setText(intent.getStringExtra(HomeActivity.BD_TRAINING_SYSTEM));
                editTextState.setText(intent.getStringExtra(HomeActivity.BD_STATE));
                editTextYear.setText(intent.getStringExtra(HomeActivity.BD_YEAR));
                editTextDepartment.setText(intent.getStringExtra(HomeActivity.BD_DEPARTMENT));
                editTextEmail.setText(intent.getStringExtra(HomeActivity.BD_EMAIL));
            }
        }
    }
}
