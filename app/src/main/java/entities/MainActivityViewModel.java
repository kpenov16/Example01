package entities;

import android.os.Bundle;

import androidx.lifecycle.ViewModel;

import dk.kaloyan.example01.MainActivity;

public class MainActivityViewModel extends ViewModel {
    public static final String KEY_CLICKS_COUNT = "dk.kaloyan.example01.CLICKS_COUNT";
    public String clicksCount;
    public Clicks clicksParcelable;
    public boolean isNewlyCreated = true;

    public void saveState(Bundle outState) {
        outState.putParcelable(Clicks.KEY_CLICKS, new Clicks(clicksCount));
        outState.putString(KEY_CLICKS_COUNT, clicksCount);
    }

    public void restoreState(Bundle inState) {
        clicksParcelable = inState.getParcelable(Clicks.KEY_CLICKS);
        clicksCount = inState.getString(KEY_CLICKS_COUNT);
    }

}
