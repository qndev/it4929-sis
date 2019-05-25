package vn.edu.hust.student.quangnd.devofappformobiledevices.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

import vn.edu.hust.student.quangnd.devofappformobiledevices.R;

public class HomeActivity extends AppCompatActivity {
    private CardView cardViewProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        cardViewProfile = (CardView) findViewById(R.id.user_profile_card_id);
        cardViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
                Toast.makeText(HomeActivity.this, "Profile User!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
