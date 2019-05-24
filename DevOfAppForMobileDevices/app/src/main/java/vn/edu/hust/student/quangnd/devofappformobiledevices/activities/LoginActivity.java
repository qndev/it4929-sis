package vn.edu.hust.student.quangnd.devofappformobiledevices.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mikepenz.iconics.context.IconicsLayoutInflater2;

import java.sql.SQLException;

import vn.edu.hust.student.quangnd.devofappformobiledevices.R;
import vn.edu.hust.student.quangnd.devofappformobiledevices.databinding.ActivityLoginBinding;
import vn.edu.hust.student.quangnd.devofappformobiledevices.logic.CheckUserLogin;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    private View.OnClickListener onLogin = new View.OnClickListener() {
        public void onClick(View v) {
            EditText userName = (EditText) findViewById(R.id.et_email_address);
            EditText password = (EditText) findViewById(R.id.et_password);

            String user = userName.getText().toString();
            String pass = password.getText().toString();
            CheckUserLogin checkUserLogin = new CheckUserLogin();
            try {
                if (checkUserLogin.checkStudentLogin(user, pass) == true) {
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                }
                /*checkUserLogin.checkStudentLogin(user, pass);*/

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory2(getLayoutInflater(), new IconicsLayoutInflater2(getDelegate()));
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        Button loginButton = (Button) findViewById(R.id.btn_login);

        loginButton.setOnClickListener(onLogin);
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
