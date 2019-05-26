package vn.edu.hust.student.quangnd.devofappformobiledevices.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import vn.edu.hust.student.quangnd.devofappformobiledevices.R;
import vn.edu.hust.student.quangnd.devofappformobiledevices.dao.DatabaseHelper;
import vn.edu.hust.student.quangnd.devofappformobiledevices.objects.Student;

public class ProfileActivity extends AppCompatActivity {
    private Button buttonEdit;
    private Student student;
    private DatabaseHelper databaseHelper;
    private TextView textViewName, textViewEmail, textViewDate, textViewCLass, textViewDepartment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        buttonEdit = (Button) findViewById(R.id.button_edit_profile);
        textViewName = (TextView) findViewById(R.id.profile_name);
        textViewEmail = (TextView) findViewById(R.id.textEmail);
        textViewDate = (TextView) findViewById(R.id.textDate);
        textViewCLass = (TextView) findViewById(R.id.textClass);
        textViewDepartment = (TextView) findViewById(R.id.textDepartment);
        setDataByBundle();
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void setDataByBundle(){
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra(HomeActivity.BUNDLE);
            if (bundle != null) {
                textViewName.setText(bundle.getString(HomeActivity.BD_NAME));
                textViewEmail.setText(bundle.getString(HomeActivity.BD_EMAIL));
                textViewDate.setText(bundle.getString(HomeActivity.BD_DATE));
                textViewCLass.setText(bundle.getString(HomeActivity.BD_CLASS));
                textViewDepartment.setText(bundle.getString(HomeActivity.BD_DEPARTMENT));
            } else {
                textViewName.setText(intent.getStringExtra(HomeActivity.BD_NAME));
                textViewEmail.setText(intent.getStringExtra(HomeActivity.BD_EMAIL));
                textViewDate.setText(intent.getStringExtra(HomeActivity.BD_DATE));
                textViewCLass.setText(intent.getStringExtra(HomeActivity.BD_CLASS));
                textViewDepartment.setText(intent.getStringExtra(HomeActivity.BD_DEPARTMENT));
            }
        }
    }
}
