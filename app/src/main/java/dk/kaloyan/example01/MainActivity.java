package dk.kaloyan.example01;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.BigInteger;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private final String WEB_URL = "http://www.javabog.dk/";
    private final String MAIN_ACTIVITY = "MainActivity";
    private final String START_LABEL = "Click Me !";
    private final String INCREMENT_VALUE = "1";
    private final String START_VALUE = "0";
    private final String TIMES_CLICKED_S = "Times Clicked: %s";
    private TextView textViewMessage;
    private Button buttonClickMe;
    private ImageView imageView;
    private TextView textViewWelcome;
    private WebView webViewJavaSite;
    private Button buttonBrowseUrl;
    private EditText editTextUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(MAIN_ACTIVITY,"OnCreate was called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        this.webViewJavaSite.loadUrl(WEB_URL);
        this.webViewJavaSite.setWebViewClient(new WebViewClient());

        buttonBrowseUrl.setOnClickListener(v -> {
            Log.i(MAIN_ACTIVITY,"buttonBrowseUrl.setOnClickListener was called");
            this.webViewJavaSite.loadUrl(this.editTextUrl.getText().toString());
            this.webViewJavaSite.setWebViewClient(new WebViewClient());
        });

        this.textViewMessage.setText(this.START_VALUE);
        this.textViewMessage.setTextSize(TypedValue.COMPLEX_UNIT_SP, 34);

        this.buttonClickMe.setText(START_LABEL);
        this.buttonClickMe.setOnClickListener(v -> {
            Log.i(MAIN_ACTIVITY,"buttonClickMe.setOnClickListener was called");

            final BigInteger COUNT_BINDING = new BigInteger(this.textViewMessage.getText().toString()).add(new BigInteger(INCREMENT_VALUE));
            this.textViewMessage.setText( COUNT_BINDING.toString() );
            ((Button)v).setText(String.format(TIMES_CLICKED_S, COUNT_BINDING.toString()));
        });
        
        this.imageView.setOnClickListener(v -> {
            Log.i(MAIN_ACTIVITY,"imageView.setOnClickListener was called");
            findViewById(R.id.imageViewPerson).getRootView().setBackgroundColor(getRandomColor());
        });

    }

    public int getRandomColor(){
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    private void initialize(){
        this.editTextUrl = findViewById(R.id.editTextUrl);
        this.buttonBrowseUrl = findViewById(R.id.buttonBrowseUrl);
        this.webViewJavaSite = findViewById(R.id.webViewJavaSite);
        this.textViewMessage = findViewById(R.id.textViewMessage);
        this.textViewWelcome = findViewById(R.id.textViewWelcome);
        this.buttonClickMe = findViewById(R.id.buttonClickMe);
        this.imageView = findViewById(R.id.imageViewPerson);
    }
}