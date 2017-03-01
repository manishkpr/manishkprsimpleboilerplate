
package com.manishkprsimpleboilerplate.models;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Suggestions implements Serializable, Parcelable
{

    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    public final static Creator<Suggestions> CREATOR = new Creator<Suggestions>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Suggestions createFromParcel(Parcel in) {
            Suggestions instance = new Suggestions();
            in.readList(instance.results, (com.manishkprsimpleboilerplate.models.Result.class.getClassLoader()));
            return instance;
        }

        public Suggestions[] newArray(int size) {
            return (new Suggestions[size]);
        }

    }
    ;
    private final static long serialVersionUID = -8071904495608849198L;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(results);
    }

    public int describeContents() {
        return  0;
    }

}
