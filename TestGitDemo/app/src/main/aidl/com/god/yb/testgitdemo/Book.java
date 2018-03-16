package com.god.yb.testgitdemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yaobing on 2018/3/16.
 * Description 书籍类（可序列化）
 */

public class Book implements Parcelable {

    public String BookName;
    public int BookId;

    public Book(String bookName, int bookId) {
        BookName = bookName;
        BookId = bookId;
    }

    protected Book(Parcel in) {
        BookId = in.readByte();
        BookName = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
