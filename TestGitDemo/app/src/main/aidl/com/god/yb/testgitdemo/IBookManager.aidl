// IBookManager.aidl
package com.god.yb.testgitdemo;

// Declare any non-default types here with import statements
import com.god.yb.testgitdemo.Book;

interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
}
