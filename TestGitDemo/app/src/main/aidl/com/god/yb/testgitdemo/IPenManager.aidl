// IPenManager.aidl
package com.god.yb.testgitdemo;

// Declare any non-default types here with import statements

interface IPenManager {
    List<String> getPenList();
    void addPen(in String  str);
}
