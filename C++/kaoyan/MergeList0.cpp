/**
 * 合并两个递增有序的链表为一个非递减有序的链表（含头结点）
 *
 * 解1：以链表1的头结点作为新链表的头结点，设出一个指针A始终指向当前链表的末端，
 * 再设出两个指针BC分别指向两个链表下一个需要比较的结点，然后选择新A是B还是C。最后返回链表1的头结点。
 */
#include "iostream"

using namespace std;

#define endl '\n'

typedef struct List {
    int value;
    List *next;
} List;

/*
 * 尾插法初始化链表
 *
 * list 需要初始化的链表的头结点
 * array 链表中存储的值
 * n 链表的长度（除结点外）
 */
void initialTail(List *&list, int array[], int n) {
    list = (List*) malloc(sizeof (List));
    list->next = nullptr;
    list->value = n;
    List *now = list;
    for (int i = 0; i < n; i++) {
        List *newList = (List*) malloc(sizeof (List));
        newList->value = array[i];
        now->next = newList;
        now = now->next;
    }
    now->next = nullptr;
}

/*
 * list1 链表1
 * list2 链表2
 */
List* mergeTwoLists(List* l1, List* l2) {
    List *head = l1;
    List *L1 = l1->next;
    List *L2 = l2->next;
    while (L1 != nullptr && L2 != nullptr) {
        if (L1->value >= L2->value) {
            l1->next = L2;
            l1 = l1->next;
            L2 = L2->next;
        } else {
            l1->next = L1;
            l1 = L1;
            L1 = L1->next;
        }
    }
    if (L1 != nullptr) {
        l1->next = L1;
    }
    if (L2 != nullptr) {
        l1->next = L2;
    }
    return head;
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
    int array[3] = {1, 2, 4};
    initialTail(list, array, 3);
    List *list2;
    int array1[4] = {1, 3, 4};
    initialTail(list2, array1, 3);
    mergeTwoLists(list, list2);
    output(list);

}
