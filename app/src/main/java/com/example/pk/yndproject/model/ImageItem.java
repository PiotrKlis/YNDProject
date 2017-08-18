package com.example.pk.yndproject.model;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by PK on 17.08.2017.
 */

public class ImageItem implements Parcelable {

    @SerializedName("format")
        @Expose
        private String format;
        @SerializedName("width")
        @Expose
        private Integer width;
        @SerializedName("height")
        @Expose
        private Integer height;
        @SerializedName("filename")
        @Expose
        private String filename;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("author")
        @Expose
        private String author;
        @SerializedName("author_url")
        @Expose
        private String authorUrl;
        @SerializedName("post_url")
        @Expose
        private String postUrl;

    public String getFormat() {
            return format;
        }

        public void setFormat(String format) {
            this.format = format;
        }

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getAuthorUrl() {
            return authorUrl;
        }

        public void setAuthorUrl(String authorUrl) {
            this.authorUrl = authorUrl;
        }

        public String getPostUrl() {
            return postUrl;
        }

        public void setPostUrl(String postUrl) {
            this.postUrl = postUrl;
        }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(author);
        parcel.writeInt(width);
        parcel.writeInt(height);

    }

    public static final Creator<ImageItem> CREATOR = new Creator<ImageItem>() {
        @Override
        public ImageItem createFromParcel(Parcel parcel) {
            return new ImageItem(parcel);
        }

        @Override
        public ImageItem[] newArray(int i) {
            return new ImageItem[i];
        }
    };

    public ImageItem(Parcel in) {
        id = in.readInt();
        author = in.readString();
        width = in.readInt();
        height = in.readInt();
    }
}
