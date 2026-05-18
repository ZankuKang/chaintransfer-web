#include "com_chaintransfer_native__ChaintransferClient.h"
#include <jni.h>
#include "sillypartner.hpp"

JNIEXPORT void JNICALL Java_com_chaintransfer_native_1_ChaintransferClient_chaintransfer
  (JNIEnv* env, jobject jobj, jstring filename) {
    const char* path_to_file = env->GetStringUTFChars(filename, nullptr);

    SillyPartner client{"192.168.1.51", 30000};
    client.silly_conversation(path_to_file);
    env->ReleaseStringUTFChars(filename, path_to_file);
}