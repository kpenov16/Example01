package dk.kaloyan.example01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import entities.Clicks;

public class CounterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        String clicksParcelable = ((Clicks)getIntent().getParcelableExtra(Clicks.KEY_CLICKS)).getClicksCount();
        String clicksStr = getIntent().getStringExtra(MainActivity.KEY_CLICKS_COUNT);

        Toast.makeText(getApplicationContext(), clicksParcelable.equals(clicksStr) ? clicksStr : "Error", Toast.LENGTH_LONG)
                .show();

    }
}