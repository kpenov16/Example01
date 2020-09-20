package dk.kaloyan.example01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import entities.Clicks;
import entities.MainActivityViewModel;

public class CounterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonCountMore;
    private TextView textViewCountMore;
    private Button buttonSendEmail;
    private String countClicks;
    private Clicks clicksParcelable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        //clicksParcelable = getIntent().getParcelableExtra(Clicks.KEY_CLICKS);
        countClicks = getIntent().getStringExtra(MainActivity.KEY_CLICKS_COUNT);

        initialize();

        textViewCountMore.setText(countClicks);
        buttonCountMore.setOnClickListener(this);
        buttonSendEmail.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        ArrayList<Parcelable> c = new ArrayList<>(); c.add(new Clicks(countClicks));
        Intent returnedIntent = new Intent();
        returnedIntent.putParcelableArrayListExtra(Clicks.KEY_CLICKS, c);
        returnedIntent.putExtra(MainActivity.KEY_CLICKS_COUNT, countClicks);
        setResult(Activity.RESULT_OK, returnedIntent);

        super.onBackPressed();

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {

        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {

        super.onPause();
    }

    @Override
    public void onClick(View v) {
        final int VIEW_ID = v.getId();
        if(R.id.buttonCountMore == VIEW_ID){
            countClicks = (new BigInteger(textViewCountMore.getText().toString()).add(new BigInteger("1"))).toString();
            textViewCountMore.setText(countClicks);
        }else if(R.id.buttonSendEmail == VIEW_ID){
            String subject = "Current count";
            String text = String.format("The current count: %s", textViewCountMore.getText().toString());
            Intent intent = new Intent(Intent.ACTION_SEND); //implicit
            intent.setType("message/rfc2822");
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, text);
            startActivity(intent);
        }
    }

    private void initialize() {
        buttonSendEmail = findViewById(R.id.buttonSendEmail);
        buttonCountMore = findViewById(R.id.buttonCountMore);
        textViewCountMore = findViewById(R.id.textViewCountMore);
    }


}