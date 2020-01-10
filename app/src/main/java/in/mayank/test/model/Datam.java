package in.mayank.test.model;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datam implements Parcelable {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("rows")
    @Expose
    private List<Row> rows = null;
    public final static Parcelable.Creator<Datam> CREATOR = new Creator<Datam>() {

        @SuppressWarnings({
                "unchecked"
        })
        public Datam createFromParcel(Parcel in) {
            return new Datam(in);
        }

        public Datam[] newArray(int size) {
            return (new Datam[size]);
        }

    };

    protected Datam(Parcel in) {
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.rows, (in.mayank.test.model.Row.class.getClassLoader()));
    }

    public Datam() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(title);
        dest.writeList(rows);
    }

    public int describeContents() {
        return 0;
    }

}