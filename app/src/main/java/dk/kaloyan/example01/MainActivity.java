package dk.kaloyan.example01;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

import entities.Clicks;
import entities.MainActivityViewModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String KEY_CLICKS_COUNT = "dk.kaloyan.example01.CLICKS_COUNT";
    public static final int RESULT_CODE_COUNTER_ACTIVITY = 0;
    private final String WEB_URL = "http://www.javabog.dk/";
    private final String MAIN_ACTIVITY = "MainActivity";
    private final String START_LABEL = "Click Me !";
    private final String INCREMENT_VALUE = "1";
    private String startValue = "0";
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
    private MainActivityViewModel viewModelMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(MAIN_ACTIVITY,"OnCreate was called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //start - manage activity state
        ViewModelProvider viewModelProvider = new ViewModelProvider(getViewModelStore(),
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()));

        viewModelMainActivity = viewModelProvider.get(MainActivityViewModel.class);

        if(viewModelMainActivity.isNewlyCreated && savedInstanceState != null){
            viewModelMainActivity.restoreState(savedInstanceState);
            startValue = viewModelMainActivity.clicksCount;
            Clicks clicks = viewModelMainActivity.clicksParcelable;
        }
        viewModelMainActivity.isNewlyCreated = false;
        //end - manage activity state

        initialize();
        setup();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MainActivity.RESULT_CODE_COUNTER_ACTIVITY) {
            if(resultCode == Activity.RESULT_OK){

                ArrayList<Parcelable> clicksEkstra = data.getParcelableArrayListExtra(Clicks.KEY_CLICKS);
                Clicks c = (Clicks) clicksEkstra.get(0);
                String newAmountOfClicks = data.getStringExtra(MainActivity.KEY_CLICKS_COUNT);

                updateClicks(newAmountOfClicks);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }

    private void updateClicks(String newAmountOfClicks) {
        clicksCount = new BigInteger(newAmountOfClicks);
        viewModelMainActivity.clicksCount = clicksCount.toString();
        //viewModelMainActivity.clicksParcelable.setClicksCount(clicksCount.toString());
        viewModelMainActivity.clicksParcelable = new Clicks(clicksCount.toString());
        updateViewWithNewClicks();
    }

    private void updateViewWithNewClicks() {
        this.textViewMessage.setText(clicksCount.toString());
        this.buttonClickMe.setText(String.format(TIMES_CLICKED_S, clicksCount.toString()));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e(MAIN_ACTIVITY,"onSaveInstanceState is called");
        if(outState != null){
            viewModelMainActivity.saveState(outState);
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

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

    private void setup() {
        this.buttonPassClicks.setOnClickListener(this);

        this.webViewJavaSite.loadUrl(WEB_URL);
        this.webViewJavaSite.setWebViewClient(new WebViewClient());

        this.buttonBrowseUrl.setOnClickListener(this);

        this.textViewMessage.setText(this.startValue);
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

            viewModelMainActivity.clicksCount = clicksCount.toString();

            updateViewWithNewClicks();

        }else if(R.id.imageViewPerson == VIEW_ID){
            Log.i(MAIN_ACTIVITY,"imageView.setOnClickListener was called");
            findViewById(R.id.imageViewPerson).getRootView().setBackgroundColor(getRandomColor());
        }else if(R.id.buttonPassClicks == VIEW_ID){
            Log.i(MAIN_ACTIVITY,"buttonPassClicks.setOnClickListener was called");
            //explicit
            Intent intent = new Intent(MainActivity.this, CounterActivity.class);
            //intent.putExtra(Clicks.KEY_CLICKS, new Clicks(clicksCount.toString()));
            intent.putExtra(MainActivity.KEY_CLICKS_COUNT, clicksCount.toString());

            //startActivity(intent);
            startActivityForResult(intent, RESULT_CODE_COUNTER_ACTIVITY);
        }
    }


    public int getRandomColor(){
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }




}