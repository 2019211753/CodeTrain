#include "iostream"

typedef struct ListNode {
    int val;
    ListNode *next;
} ListNode;

void initial0(ListNode *&head, int array[], int n) {
    head = (ListNode*) malloc(sizeof (ListNode));
    head->val = n;
    head->next = nullptr;
    ListNode *swap = head;
    for (int i = 0; i < n; i++) {
        ListNode *next = (ListNode*) malloc(sizeof (ListNode));
        next->val = array[i];
        swap->next = next;
        swap = swap->next;
    }
    swap->next = nullptr;
}

void output(ListNode *head) {
    head = head->next;
    while (head != nullptr) {
        printf("%d\n", head->val);
        head = head->next;
    }
}

int deleteByVal(ListNode *l, int val) {
    ListNode *node;
    while (l->next != nullptr) {
        if (l->next->val == val) {
            while (l->next != nullptr) {
                node = l->next;
                l->next = l->next->next;
                free(node);
            }
            return 1;
        } else {
            l = l->next;
        }
    }
    return 0;
}

int main() {
    ListNode *listNode;
    int array[5] = {1, 2, 0, 4, 5};
    initial0(listNode, array, 5);
    printf("%d\n********\n", deleteByVal(listNode, 3));
    output(listNode);

}