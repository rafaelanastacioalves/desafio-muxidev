// Créditos: https://raw.githubusercontent.com/googlesamples/android-ndk/master/hello-jni/app/src/main/cpp/hello-jni.c

#include <string.h>
#include <jni.h>

JNIEXPORT jfloat JNICALL
Java_com_example_rafaelanastacioalves_moby_repository_AppRepository_convertValue( JNIEnv* env,
                                                  jobject thiz, jfloat originalValue)
{
#if defined(__arm__)
    #if defined(__ARM_ARCH_7A__)
    #if defined(__ARM_NEON__)
      #if defined(__ARM_PCS_VFP)
        #define ABI "armeabi-v7a/NEON (hard-float)"
      #else
        #define ABI "armeabi-v7a/NEON"
      #endif
    #else
      #if defined(__ARM_PCS_VFP)
        #define ABI "armeabi-v7a (hard-float)"
      #else
        #define ABI "armeabi-v7a"
      #endif
    #endif
  #else
   #define ABI "armeabi"
  #endif
#elif defined(__i386__)
#define ABI "x86"
#elif defined(__x86_64__)
#define ABI "x86_64"
#elif defined(__mips64)  /* mips64el-* toolchain defines __mips__ too */
#define ABI "mips64"
#elif defined(__mips__)
#define ABI "mips"
#elif defined(__aarch64__)
#define ABI "arm64-v8a"
#else
#define ABI "unknown"
#endif

    return 2*originalValue;
}