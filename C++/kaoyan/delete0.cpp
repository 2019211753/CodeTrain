#include <iostream>

using namespace std;
/*
 * 考研中的顺序表删除
 * 熟悉一下
 */

typedef struct List {
    int array[4];
    int length;
} List;

int delete(List *list, int site);

int delete(List *list, int site) {
    if (site < 0 || site > list->length) {
        return 0;
    }
    int i = site;
    for (i; i < list->length - 1; i++) {
        list->array[i] = list->array[i + 1];
    }
    list->length = list->length - 1;
}
int main() {
    List *list = (List*)malloc(sizeof(List));
    list->array[0] = 1;
    list->array[1] = 3;
    list->array[2] = 2;
    list->array[3] = 4;
    list->length = 4;
    delete(list, 2);
    for (int i = 0; i < 4; i++) {
        printf("array[%d]: %d\n", i, list->array[i]);
    }
}