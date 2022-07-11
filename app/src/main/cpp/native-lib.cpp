#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_najdimu_ndkcalculator_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Error something...";
    return env->NewStringUTF(hello.c_str());
}