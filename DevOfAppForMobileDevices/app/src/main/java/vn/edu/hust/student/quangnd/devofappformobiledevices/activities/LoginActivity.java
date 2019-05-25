package vn.edu.hust.student.quangnd.devofappformobiledevices.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mikepenz.iconics.context.IconicsLayoutInflater2;

import vn.edu.hust.student.quangnd.devofappformobiledevices.R;
import vn.edu.hust.student.quangnd.devofappformobiledevices.dao.DatabaseHelper;
import vn.edu.hust.student.quangnd.devofappformobiledevices.databinding.ActivityLoginBinding;
import vn.edu.hust.student.quangnd.devofappformobiledevices.objects.User;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "User Login";
    ActivityLoginBinding binding;
    private DatabaseHelper databaseHelper;
    private Button loginButton, buttonSeedData;
    private EditText userName;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory2(getLayoutInflater(), new IconicsLayoutInflater2(getDelegate()));
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        databaseHelper = new DatabaseHelper(this);
        loginButton = (Button) findViewById(R.id.btn_login);
        buttonSeedData = (Button) findViewById(R.id.btn_login_fb);
        userName = (EditText) findViewById(R.id.et_email_address);
        password = (EditText) findViewById(R.id.et_password);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = userName.getText().toString();
                String pass = password.getText().toString();
                String role = "student";
                User student = new User(user, pass, role);
                boolean checkLogin = databaseHelper.getStudentUserLogin(student);
                if (checkLogin == true) {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Successfully!");
                } else {
                    Toast.makeText(LoginActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Failed!");
                }
            }
        });
        buttonSeedData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean seedData = databaseHelper.seedUserLogin();
                if (seedData == true) {
                    Toast.makeText(LoginActivity.this, "Seed data Successfully!", Toast.LENGTH_SHORT).show();
                    Log.d("Seed data", "Successfully!");
                } else {
                    Toast.makeText(LoginActivity.this, "Seed data Failed!", Toast.LENGTH_SHORT).show();
                    Log.d("Seed data", "Failed!");
                }
            }
        });
    }

    public void signup(View view) {
        startActivity(new Intent(this, SignupActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return true;
    }
}
