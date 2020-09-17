package dk.kaloyan.example01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigInteger;

import entities.Clicks;

public class CounterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonCountMore;
    private TextView textViewCountMore;
    private Button buttonSendEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
        initialize();

        String clicksParcelable = ((Clicks)getIntent().getParcelableExtra(Clicks.KEY_CLICKS)).getClicksCount();
        String clicksStr = getIntent().getStringExtra(MainActivity.KEY_CLICKS_COUNT);

        textViewCountMore.setText(clicksStr);
        /*Toast.makeText(getApplicationContext(), clicksParcelable.equals(clicksStr) ? clicksStr : "Error", Toast.LENGTH_LONG)
                .show();*/

        buttonCountMore.setOnClickListener(this);
        buttonSendEmail.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final int VIEW_ID = v.getId();
        if(R.id.buttonCountMore == VIEW_ID){
            textViewCountMore.setText(
                (new BigInteger(textViewCountMore.getText().toString()).add(new BigInteger("1"))).toString()
            );
        }else if(R.id.buttonSendEmail == VIEW_ID){
            String subject = "Current count";
            String text = String.format("The current count: %s", textViewCountMore.getText().toString());
            Intent intent = new Intent(Intent.ACTION_SEND);
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