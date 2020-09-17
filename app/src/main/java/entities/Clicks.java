package entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Clicks implements Parcelable {
    public static String KEY_CLICKS = "entities.Clicks.CLICKS";
    private String clicksCount;
    public Clicks(String clicksCount){
        setClicksCount(clicksCount);
    }
    protected Clicks(Parcel source) {
        setClicksCount( source.readString() );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Clicks that = (Clicks) o;

        return getClicksCount().equals(that.getClicksCount());
    }

    @Override
    public int hashCode() {
        return getClicksCount().hashCode();
    }


    public static final Creator<Clicks> CREATOR = new Creator<Clicks>() {
        @Override
        public Clicks createFromParcel(Parcel source) {
            return new Clicks(source);
        }

        @Override
        public Clicks[] newArray(int size) {
            return new Clicks[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel destination, int flags) {
        destination.writeString(getClicksCount());
    }

    public String getClicksCount() {
        return clicksCount;
    }

    public void setClicksCount(String clicksCount) {
        this.clicksCount = clicksCount;
    }
}
