#include <jni.h>
#include <string>

extern "C" JNIEXPORT jdouble JNICALL
Java_com_najdimu_ndkcalculator_MainActivity_add(
        JNIEnv* env,
        jobject /* this */,
        jdouble a, jdouble b) {
    return a+b;
}

extern "C" JNIEXPORT jdouble JNICALL
Java_com_najdimu_ndkcalculator_MainActivity_sub(
        JNIEnv* env,
        jobject /* this */,
        jdouble a, jdouble b) {
    return a-b;
}

extern "C" JNIEXPORT jdouble JNICALL
Java_com_najdimu_ndkcalculator_MainActivity_mut(
        JNIEnv* env,
        jobject /* this */,
        jdouble a, jdouble b) {
    return a*b;
}

extern "C" JNIEXPORT jdouble JNICALL
Java_com_najdimu_ndkcalculator_MainActivity_div(
        JNIEnv* env,
        jobject /* this */,
        jdouble a, jdouble b) {
    return a/b;
}

