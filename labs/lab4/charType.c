//Steven Cruz
//CS 12B
//Lab4
//sicruz


#include<stdio.h>
#include<stdlib.h>
#include<ctype.h>
#include<assert.h>
#include<string.h>

#define MAX_STRING_LENGTH 100


// function prototype
void extract_chars(char *s, char *a, char *d, char *p, char *w);

// function main which takes command line arguments
int main(int argc, char *argv[])
{
    FILE *in;        // handle for input file
    FILE *out;       // handle for output file
    char *line;      // string holding input line
    char *alpha;
    char *digits;
    char *puncs;
    char *wsp;

    // check command line for correct number of arguments
    if ( argc != 3 )
    {
        printf("Usage: %s input-file output-file\n", argv[0]);
        exit(EXIT_FAILURE);
    }

    // open input file for reading
    if ( (in = fopen(argv[1], "r")) == NULL )
    {
        printf("Unable to read from file %s\n", argv[1]);
        exit(EXIT_FAILURE);
    }

    // open output file for writing
    if ( (out = fopen(argv[2], "w")) == NULL )
    {
        printf("Unable to write to file %s\n", argv[2]);
        exit(EXIT_FAILURE);
    }

    // allocate strings line and extract_chars on the heap
    line = calloc(MAX_STRING_LENGTH + 1, sizeof(char) ); //line chars
    alpha = calloc(MAX_STRING_LENGTH + 1, sizeof(char) ); //alphabetical characters
    digits = calloc(MAX_STRING_LENGTH + 1, sizeof(char) ); //digits chars
    puncs = calloc(MAX_STRING_LENGTH + 1, sizeof(char) ); //punctuations chars
    wsp = calloc(MAX_STRING_LENGTH + 1, sizeof(char) ); //whitespace chars
    assert( line != NULL );


    int c = 1; //counter for the lines
    // read each line in input file, extract characters
    while ( fgets(line, MAX_STRING_LENGTH, in) != NULL )
    {
        extract_chars(line, alpha, digits, puncs, wsp);
        fprintf(out, "line %d contains:\n", c);
        int alength = 0;
        while (alpha[alength] != '\0')
        {
            alength++;
        }
        if (alength == 1)
        {

            fprintf(out, "%d alphabetic character: %s\n", alength, alpha);
        }
        else
        {
            fprintf(out, "%d alphabetic characters: %s\n", alength, alpha); //prints out the alphabetical chars
        }
        int dlength = 0;
        while (digits[dlength] != '\0')
        {
            dlength++;
        }
        if (dlength == 1)
        {
            fprintf(out, "%d numeric character: %s\n", dlength, digits); //prints out digits
        }
        else
        {
            fprintf(out, "%d numeric characters: %s\n", dlength, digits);
        }

        int plength = 0;
        while (puncs[plength] != '\0')
        {
            plength++;
        }
        if (plength == 1)
        {
            fprintf(out, "%d punctuation character: %s\n", plength, puncs); //prints out puncs
        }
        else
        {
            fprintf(out, "%d punctuation characters: %s\n", plength, puncs);
        }

        int wlength = 0;
        while (wsp[wlength] != '\0')
        {
            wlength++;
        }
        if (wlength == 1)
        {
            fprintf(out, "%d whitespace character: %s\n", wlength, wsp); //prints out whitespace
        }
        else
        {
            fprintf(out, "%d whitespace characters: %s\n", wlength, wsp);
        }
        c++;
    }


    // free heap memory
    free(line);
    free(alpha);
    free(digits);
    free(puncs);
    free(wsp);

    // close input and output files
    fclose(in);
    fclose(out);

    return EXIT_SUCCESS;
}

// function definition
void extract_chars(char *s, char *a, char *d, char *p, char *w)
{
    int i = 0, j = 0;
    while (s[i] != '\0' && i < MAX_STRING_LENGTH)
    {
        if ( isalpha( (int)s[i]) ) a[j++] = s[i]; //extracts alphabets
        i++;
    }
    a[j] = '\0';

    int k = 0, l = 0;
    while (s[k] != '\0' && k < MAX_STRING_LENGTH)
    {
        if ( isdigit( (int)s[k]) ) d[l++] = s[k];   //extracts digits
        k++;
    }
    d[l] = '\0';

    int m = 0, n = 0;
    while (s[m] != '\0' && m < MAX_STRING_LENGTH)
    {
        if (ispunct( (int)s[m]) ) p[n++] = s[m];   //extracts punctuations
        m++;
    }
    p[n] = '\0';

    int r = 0, q = 0;
    while (s[r] != '\0' && r < MAX_STRING_LENGTH)
    {
        if (isspace( (int)s[r]) ) w[q++] = s[r];    //extracts spaces
        r++;
    }
    w[q] = '\0';
}