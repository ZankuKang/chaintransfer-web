package com.chaintransfer.native_;

public class ChaintransferClient {
    static {
        System.loadLibrary("chaintransferclient");
    }

    public native void chaintransfer(String filename);
}
