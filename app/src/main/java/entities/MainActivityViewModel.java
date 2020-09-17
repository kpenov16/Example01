package entities;

import android.os.Bundle;

import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {
    public static final String KEY_CLICKS_COUNT = "dk.kaloyan.example01.CLICKS_COUNT";
    public String clicksCount;
    public boolean isNewlyCreated = true;

    public void saveState(Bundle outState) {
        outState.putString(KEY_CLICKS_COUNT, clicksCount);
    }

    public void restoreState(Bundle inState) {
        clicksCount = inState.getString(KEY_CLICKS_COUNT);
    }

}
