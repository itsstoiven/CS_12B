#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"


// private types --------------------------------------------------------------

//arbitrary value
const int tableSize = 101;

// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift)
{
    int sizeInBits = 8 * sizeof(unsigned int);
    shift = shift & (sizeInBits - 1);
    if ( shift == 0 )
        return value;
    return (value << shift) | (value >> (sizeInBits - shift));
}

// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char *input)
{
    unsigned int result = 0xBAE86554;
    while (*input)
    {
        result ^= *input++;
        result = rotate_left(result, 5);
    }
    return result;
}

// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(char *key)
{
    return pre_hash(key) % tableSize;
}
// NodeObj
typedef struct NodeObj
{
    char *key;
    char *value;
    struct NodeObj *next;
} NodeObj;

// Node
typedef NodeObj *Node;

// newNode()
// constructor of the Node type
Node newNode(char *k, char *v)
{
    Node N = malloc(sizeof(NodeObj));
    assert(N != NULL);
    N->key = k;
    N->value = v;
    N->next = NULL;
    return (N);
}

// freeNode()
// destructor for the Node type
void freeNode(Node *pN)
{
    if ( pN != NULL && *pN != NULL )
    {
        free(*pN);
        *pN = NULL;
    }
}

// LinkedObj              //used to keep track of the list
typedef struct LinkedObj
{
    Node head;
} LinkedObj;

// Linked List
typedef LinkedObj *LinkedList;

// Constructor for the LinkedList type
LinkedList newLinkedList()
{
    LinkedList L = malloc(sizeof(LinkedObj));
    L->head = NULL;
    return L;
}

typedef struct DictionaryObj
{
    LinkedList table;
    int numPairs;
} DictionaryObj;

//finds key within the hash table
Node findKey(Dictionary D, char *k)
{
    //traverse through nodes to check if node contains the key
    for (Node N = D->table[hash(k)].head; N != NULL; N = N->next) //run through the hash to find the key

        if (strcmp(k, N->key) == 0)    //check if the key looked for is found and is if found return the key and its value
        {
            return N;
        }
    return NULL; //key hasn't been found so just return null
}


// public functions -----------------------------------------------------------

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void)
{
    Dictionary D = malloc(sizeof(DictionaryObj));
    assert(D != NULL);
    D->table = calloc(tableSize, sizeof(LinkedObj));    //allocate the array size for the linked lists
    D->numPairs = 0;
    return D;
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary *pD)
{
    if ( pD != NULL && *pD != NULL )
    {
        if ( !isEmpty(*pD) ) makeEmpty(*pD);
        free(*pD);
        *pD = NULL;
    }
}

// isEmpty()
// returns 1 (true) if S is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D)
{
    if (D->numPairs == 0)
    {
        return (1);
    }
    return 0;
}

int size(Dictionary D)
{
    //check if pointing to NULL
    if (D == NULL)
    {
        fprintf(stderr, "Dictionary Error: Calling size() on null pointer.\n");
    }
    return (D->numPairs);

}

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no
// such value v exists.
// pre: none
char *lookup(Dictionary D, char *k)
{
    if ( D == NULL )
    {
        fprintf(stderr, "Dictionary Error: calling lookup() on NULL Dictionary reference.\n");
        exit(EXIT_FAILURE);
    }

    if ( D->numPairs == 0 )
    {
        fprintf(stderr, "Dictionary Error: calling lookup() on empty Dictionary list.\n");
        exit(EXIT_FAILURE);
    }
    if (findKey(D, k) != NULL)    //searches for the value using hashing
    {
        return findKey(D, k)->value;
    }
    else
    {
        return NULL;
    }
}

// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char *k, char *v)
{
    if ( D == NULL )
    {
        fprintf(stderr, "Dictionary Error: calling insert() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    if (findKey(D, k) != NULL)
    {
        fprintf(stderr, "Dictionary Error: calling insert() on a duplicate key: %s\n", k );
        exit(EXIT_FAILURE);
    }

    //passes both the error checks so now we insert the key into the array of linked lists
    if (D->table[hash(k)].head == NULL)     //if the array is empty, then we insert a new node for a linkedList
    {
        Node N = newNode(k, v);
        N->next = D->table[hash(k)].head;   //travels down through the table to insert keys and values
        D->table[hash(k)].head = N;         //set new head
        D->numPairs++;                      //increment the numPairs
    }
    else
    {
        if (lookup(D, k) == NULL)             //make sure value isn't already there
        {
            //if the index in where we want to store our keys and value in
            //are taken, we keep traversing through the linked address to find a null
            //pointer and then chain the key and value in there
            for (Node N = D->table[hash(k)].head; N->next != NULL; N = N->next)
            {
                N->next = newNode(k, v);
            }
            D->numPairs++;      //increment the numPiars
        }
    }
}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char *k)
{
    if (D == NULL)
    {
        fprintf(stderr, "Dictionary Error: calling delete() on a NULL Dictionary reference.\n");
        exit(EXIT_FAILURE);
    }
    if (lookup(D, k) == NULL)
    {
        fprintf(stderr, "Dictionary Error: calling delete() on a key not even in the table.\n");
        exit(EXIT_FAILURE);
    }
    //now that we passed the error checks, we go about deleting nodes from the table of linked lists
    Node N = D->table[hash(k)].head;
    if (N == findKey(D, k))       //found the key but check for its next elements in the list
    {
        if (D->table[hash(k)].head->next == NULL)    //there is nothing ahead of this list so
        {
            freeNode(&D->table[hash(k)].head);    //we can just free it without loss of memory
            D->numPairs--;                  //decrement the numPairs
        }
        //there are some elements ahead of the found key so we need to move the head without losing the list
        else
        {
            N = D->table[hash(k)].head;
            D->table[hash(k)].head = D->table[hash(k)].head->next; //set its new head so we can get rid of the element before us
            freeNode(&N);
            D->numPairs--;
        }
    }
}


// makeEmpty()
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D)
{
    for (int i = 0; i < tableSize; i++)
    {
        Node N = D->table[i].head;
        Node T; //create a temporary node to save the position
        while (N != NULL)
        {
            T = N;
            N = N->next;
            freeNode(&T);
            D->numPairs--;
        }
    }
    free(D->table);
}


// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE *out, Dictionary D)
{
    if (D == NULL)
    {
        fprintf(stderr, "Dictionary Error: calling printDictionary() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    for (int i = 0 ; i < tableSize; i++)
    {
        for (Node N = D->table[i].head; N != NULL; N = N->next)
        {
            fprintf(out, "%s %s\n", N->key, N->value);    //print out the values
        }
    }
}