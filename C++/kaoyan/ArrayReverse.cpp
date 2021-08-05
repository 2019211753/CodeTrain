/**
 * 要求：
 * 设将n(n>l）个整数存放到一维数组R中，设计一个算法，将R中的序列循环左移P(0 < P < n)个位置，
 * 即将R中的数据由{X_0, X_1, ..., X_n-1}变换为{X_p, X_p+1, ...,X_n-1, X_0, X_1, ..., X_p-1}。
 *
 * 解1：
 * 将前p个倒置，然后将后n-p个倒置，最后整体倒置。
 * 复杂度分析：
 * 空间复杂度 O(1)，时间复杂度 p/2 + (n-p)/2 + n/2 = n，即 O(n)
 *
 * 解2：
 * 将前p个先复制，然后后n-p个向前移位p个，再对移位后的数组的后p个赋值。
 * 复杂度分析：
 * 空间复杂度 p*O(1)，时间复杂度 n-p + p = n，即 O(n)
 *
 * @author 山水夜止
 * @version 1.0
 * @date 2021-07-27
 */

#include <cstdio>

void reverse(int array[], int length, int p);
void RCR(int array0[], int m, int n);

/**
 * @param array 需要前移的数组
 * @param length 数组的长度
 * @param p 需要左移几个
 */
void reverse(int array[], int length, int p)
{
    RCR(array, 0, p);
    RCR(array, p, length - p);
    RCR(array, 0, length);
}


/**
 * @param array0 需要倒置的数组
 * @param m 倒置开始的数组索引
 * @param n 需要倒置几个
 */
void RCR(int array0[], int m, int n)
{
    int temp;
    int j = 0;
    for (int i = m; i < (m + n) / 2 ; i++, j++) {
        temp = array0[i];
        array0[i] = array0[m + n -1 - j];
        array0[m + n -1 -j] = temp;
    }
}

int main() {
    int array[] = {1, 2, 3, 4, 5};
    reverse(array, 5, 3);
    for (int i = 0; i < 5; i++) {
        printf("%d, ", array[i]);
    }

    return 0;
}
