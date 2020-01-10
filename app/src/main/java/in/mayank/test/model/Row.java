package in.mayank.test.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Row implements Parcelable
{

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("imageHref")
    @Expose
    private Object imageHref;
    public final static Parcelable.Creator<Row> CREATOR = new Creator<Row>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Row createFromParcel(Parcel in) {
            return new Row(in);
        }

        public Row[] newArray(int size) {
            return (new Row[size]);
        }

    }
            ;

    protected Row(Parcel in) {
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.imageHref = ((Object) in.readValue((Object.class.getClassLoader())));
    }

    public Row() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getImageHref() {
        return imageHref;
    }

    public void setImageHref(Object imageHref) {
        this.imageHref = imageHref;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(title);
        dest.writeValue(description);
        dest.writeValue(imageHref);
    }

    public int describeContents() {
        return 0;
    }

}