#include <jni.h>
#include <ctype.h>
#include <string.h>

int calculate(const int *numbers, const char *operations, int n) {
    unsigned int i;
    int acc = numbers[0];
    for(i = 0; i < n; i++){
        switch(operations[i]){
            case '+':
                acc += numbers[i+1];
                break;
            case '-':
                acc -= numbers[i+1];
                break;
            case '*':
                acc *= numbers[i+1];
                break;
            case '/':
                acc /= numbers[i+1];
                break;
        }
    }
    return acc;
}

JNIEXPORT jint JNICALL
Java_mmiszczyk_verysecureapp_calc_CalcNativeHelper_performCalculations(JNIEnv *env, jobject thiz,
                                                                  jstring calc_string) {

    const char *strFromKotlin;
    char tmp[16], operations[10], curr;
    int numbers[10];
    unsigned int i, j, k;
    strFromKotlin = (*env)->GetStringUTFChars(env, calc_string, 0);
    for(i = 0, j = 0, k = 0;;i++){
        curr = strFromKotlin[i];
        if(isdigit(strFromKotlin[i]) && j < 16){
            tmp[j++] = strFromKotlin[i];
            continue;
        }
        if(!curr || curr == '+' || curr == '-' || curr == '*' || curr == '/'){
            tmp[j] = 0;
            numbers[k] = atoi(tmp);
            memset(tmp, 0, 16);
            j = 0;
            if(!curr) break;
            operations[k] = curr;
            k++;
        }
    }
    (*env)->ReleaseStringUTFChars(env, calc_string, strFromKotlin);
    return (jint) calculate(numbers, operations, k);
}