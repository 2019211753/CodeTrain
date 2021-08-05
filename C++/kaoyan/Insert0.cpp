/**
 * 要求：
 * 将元素插入一个递增有序数组，插入后的数组保持原有的顺序
 *
 * 解1：
 * 先通过折半查找找到元素索引。然后再向后移动元素。
 * 复杂度分析：
 * 空间复杂度 O(1)，时间复杂度UNKNOWN
 *
 * @author 山水夜止
 * @version 1.0
 * @date 2021-07-27
 */

#include <cstdio>

void insert(int *array, int length, int num);
int findTheKeyOrTheSmaller(int *array, int begin, int end, int num);

/**
 *
 * @param array 被插入的数组
 * @param length 数组长度
 * @param num 要插入的数字
 */
void insert(int *array, int length, int num) {
    int site = findTheKeyOrTheSmaller(array, 0, length - 1, num);
    for (int i = length - 1; i > site + 1; i--) {
        array[i] = array[i - 1];
    }
    array[site + 1] = num;
}

/*
 * 折半查找 返回跟插入数字相同或小于插入数字的最大数组元素的索引位置
 *
 * @param array 被查找的数组
 * @param begin 查找范围的开头
 * @param begin 查找范围的结尾
 * @param num 被查找的数字
 */
int findTheKeyOrTheSmaller(int *array, int begin, int end, int num) {
    if (begin > end || (begin == end && array[begin] != num)) {
        return begin - 1;
    }
    int middle = (begin + end) / 2;
    if (array[middle] > num) {
        return findTheKeyOrTheSmaller(array, begin, middle - 1, num);
    } else if (array[middle] == num) {
        return middle;
    } else {
        return findTheKeyOrTheSmaller(array, middle + 1, end, num);
    }
}

int main() {
    int array[4] = {1, 2, 4, 5};
    insert(array, 4, 3);
    for (int i = 0; i < 4; i++) {
        printf("array[%d]: %d\n ", i, array[i]);
    }
}

