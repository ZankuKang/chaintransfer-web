package com.chaintransfer.native_;

public class ChaintransferClient {

    static {
        try {
            System.loadLibrary("chaintransferclient");
        } catch (UnsatisfiedLinkError e) {
            // In dev, this happens on hot reload; if that's the case, it's "fine" to ignore it, if it's not the case,
            // then we are screwed...
            // It's kinda ugly, but I couldn't find a more "idiomatic" or cleaner way to avoid this
            if (!e.getMessage().contains("already loaded in another classloader")) {
                throw e;
            }
        }
    }

    public native void chaintransfer(String filename);
}