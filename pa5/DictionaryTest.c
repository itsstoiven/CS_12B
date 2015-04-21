//Steven Cruz
//sicruz
//CS12B
//PA55


//-----------------------------------------------------------------------------
// DictionaryTest.c
// Test client for the Dictionary hash
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

#define MAX_LEN 180

int main(int argc, char *argv[])
{
    Dictionary A = newDictionary();
    char *k;
    char *v;
    char *word1[] = {"ichi", "ni", "san", "shi", "go", "roku", "shichi", "hachi", "ku"};
    char *word2[] = {"uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve"};
    int i;

    printf("%s\n", (isEmpty(A) ? "true" : "false"));

    for (i = 0; i < 9; i++)
    {
        insert(A, word1[i], word2[i]);
    }

    printDictionary(stdout, A);

    for (i = 0; i < 9; i++)
    {
        k = word1[i];
        v = lookup(A, k);
        printf("key=\"%s\" %s\"%s\"\n", k, (v == NULL ? "not found " : "value="), v);
    }

    delete(A, "ichi");
    delete(A, "roku");
    delete(A, "go");
    delete(A, "hachi");

    printf("%s\n", (isEmpty(A) ? "true" : "false"));
    printf("%d\n", size(A));

    printDictionary(stdout, A);

    for (i = 0; i < 7; i++)
    {
        k = word1[i];
        v = lookup(A, k);
        printf("key=\"%s\" %s\"%s\"\n", k, (v == NULL ? "not found " : "value="), v);
    }

    // delete(A, "go");  // error: key not found

    printf("%s\n", (isEmpty(A) ? "true" : "false"));
    printf("%d\n", size(A));
    makeEmpty(A);
    printf("%s\n", (isEmpty(A) ? "true" : "false"));

    freeDictionary(&A);

    return (EXIT_SUCCESS);
}
