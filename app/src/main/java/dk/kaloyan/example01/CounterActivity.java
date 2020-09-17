package dk.kaloyan.example01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigInteger;

import entities.Clicks;
import entities.MainActivityViewModel;

public class CounterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonCountMore;
    private TextView textViewCountMore;
    private Button buttonSendEmail;
    private String countClicks;
    MainActivityViewModel mainActivityViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
        initialize();

        ViewModelProvider viewModelProvider = new ViewModelProvider(getViewModelStore(),
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()));

        mainActivityViewModel = viewModelProvider.get(MainActivityViewModel.class);

        String clicksParcelable = ((Clicks)getIntent().getParcelableExtra(Clicks.KEY_CLICKS)).getClicksCount();
        countClicks = getIntent().getStringExtra(MainActivity.KEY_CLICKS_COUNT);

        textViewCountMore.setText(countClicks);

        buttonCountMore.setOnClickListener(this);
        buttonSendEmail.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final int VIEW_ID = v.getId();
        if(R.id.buttonCountMore == VIEW_ID){
            countClicks = (new BigInteger(textViewCountMore.getText().toString()).add(new BigInteger("1"))).toString();
            mainActivityViewModel.clicksCount = countClicks;
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

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if(outState != null){
            mainActivityViewModel.saveState(outState);
        }
    }

}