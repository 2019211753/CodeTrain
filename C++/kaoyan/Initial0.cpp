/**
 * 初始化链表
 */
#include "iostream"

using namespace std;

#define endl '\n'


typedef struct List {
    int value;
    List *next;
} List;

/*
 * 头插法
 */
void initialHead(List *&list, int array[], int n) {
    list = (List*) malloc(sizeof (List));
    list->value = n;
    list->next = nullptr;
    for (int i = 0; i < n; i++) {
        List *newList = (List*) malloc(sizeof (List));
        newList->value = array[i];
        newList->next = list->next;
        list->next = newList;
    }
}

/*
 * 尾插法
 */
void initialTail(List *&list, int array[], int n) {
    list = (List*) malloc(sizeof (List));
    list->value = n;
    list->next = nullptr;
    List *now = list;
    for (int i = 0; i < n; i++) {
        List *newList = (List*) malloc(sizeof (List));
        newList->value = array[i];
        now->next = newList;
        now = now->next;
    }
    now->next = nullptr;
}

void output(List *list) {
    list = list->next;
    while (list != nullptr) {
        cout << list->value << endl;
        list = list->next;
    }
    cout << "********" << endl;
}

int main() {
    List *list;
    int array[4] = {1, 2, 3, 4};
    initialHead(list, array, 4);
    output(list);
}

