package dk.kaloyan.example01;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigInteger;
import java.util.Random;

import entities.Clicks;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String KEY_CLICKS_COUNT = "dk.kaloyan.example01.CLICKS_COUNT";
    private final String WEB_URL = "http://www.javabog.dk/";
    private final String MAIN_ACTIVITY = "MainActivity";
    private final String START_LABEL = "Click Me !";
    private final String INCREMENT_VALUE = "1";
    private final String START_VALUE = "0";
    private final String TIMES_CLICKED_S = "Times Clicked: %s";
    private TextView textViewMessage;
    private Button buttonClickMe;
    private ImageView imageViewPerson;
    private TextView textViewWelcome;
    private WebView webViewJavaSite;
    private Button buttonBrowseUrl;
    private EditText editTextUrl;
    private Button buttonPassClicks;
    private BigInteger clicksCount = new BigInteger("0");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(MAIN_ACTIVITY,"OnCreate was called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        this.buttonPassClicks.setOnClickListener(this);

        this.webViewJavaSite.loadUrl(WEB_URL);
        this.webViewJavaSite.setWebViewClient(new WebViewClient());

        buttonBrowseUrl.setOnClickListener(this);

        this.textViewMessage.setText(this.START_VALUE);
        this.textViewMessage.setTextSize(TypedValue.COMPLEX_UNIT_SP, 34);

        this.buttonClickMe.setText(START_LABEL);
        this.buttonClickMe.setOnClickListener(this);
        
        this.imageViewPerson.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final int VIEW_ID = v.getId();
        if(R.id.buttonBrowseUrl == VIEW_ID){
            Log.i(MAIN_ACTIVITY,"buttonBrowseUrl.setOnClickListener was called");
            this.webViewJavaSite.loadUrl(this.editTextUrl.getText().toString());
            this.webViewJavaSite.setWebViewClient(new WebViewClient());
        }else if(R.id.buttonClickMe == VIEW_ID){
            Log.i(MAIN_ACTIVITY,"buttonClickMe.setOnClickListener was called");

            clicksCount = new BigInteger(this.textViewMessage.getText().toString()).add(new BigInteger(INCREMENT_VALUE));
            this.textViewMessage.setText( clicksCount.toString() );
            ((Button)v).setText(String.format(TIMES_CLICKED_S, clicksCount.toString()));
        }else if(R.id.imageViewPerson == VIEW_ID){
            Log.i(MAIN_ACTIVITY,"imageView.setOnClickListener was called");
            findViewById(R.id.imageViewPerson).getRootView().setBackgroundColor(getRandomColor());
        }else if(R.id.buttonPassClicks == VIEW_ID){
            Log.i(MAIN_ACTIVITY,"buttonPassClicks.setOnClickListener was called");
            //explicit
            Intent intent = new Intent(MainActivity.this, CounterActivity.class);
            intent.putExtra(Clicks.KEY_CLICKS, new Clicks(clicksCount.toString()));
            intent.putExtra(MainActivity.KEY_CLICKS_COUNT, clicksCount.toString());
            startActivity(intent);
        }
    }

    public int getRandomColor(){
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    private void initialize(){
        this.buttonPassClicks = findViewById(R.id.buttonPassClicks);
        this.editTextUrl = findViewById(R.id.editTextUrl);
        this.buttonBrowseUrl = findViewById(R.id.buttonBrowseUrl);
        this.webViewJavaSite = findViewById(R.id.webViewJavaSite);
        this.textViewMessage = findViewById(R.id.textViewMessage);
        this.textViewWelcome = findViewById(R.id.textViewWelcome);
        this.buttonClickMe = findViewById(R.id.buttonClickMe);
        this.imageViewPerson = findViewById(R.id.imageViewPerson);
    }


}