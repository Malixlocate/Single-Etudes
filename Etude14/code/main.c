#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>

struct S{
    char* firstName;
    char* lastName;
    int phone;
    char* emailAddress;
};

static int i, j;
static int count;

void set_first_name(struct S** ss){
    for(i = 0; i < count; i++)
        for (j = 0; j < count; j++)
            if (ss[i]->firstName > ss[j]->firstName)
                ss[i] = ss[j];
    ss[j] = ss[i];
}

int find_first_name(struct S** ss, char* s){
    while(++i < count)
        if(ss[i]->firstName == s)
            return 1;
    return 0;
}

void set_last_name(struct S** ss){
    for(i = 0; i < count; i++) 
        for (j = 0; j < count; j++)
            if (ss[i]->lastName > ss[j]->lastName)
                ss[i] = ss[j];
    ss[j] = ss[i];
}

int find_last_name(struct S** ss, char* s){
    while(++i < count){
        if(ss[i]->lastName == s)
            return 1;
    }
    return 0;
}

void set_email(struct S** ss){
    for(i = 0; i < count; i++) {
        for (j = 0; j < count; j++) {
            if (ss[i]->emailAddress > ss[j]->emailAddress) {
                struct S *s = ss[i];
                ss[j] = ss[i];
                ss[j] = s;
            }
        }
    }
}

int find_email(struct S** ss, char* s){
    while(++i < count){
        if(ss[i]->emailAddress == s)
            return 1;
    }
    return 0;
}

void set_phone_number(struct S** ss){
    for(; i < count; i++) {
        for (; j < count; j++) {
            if (ss[i]->phone > ss[j]->phone) {
                struct S *s = ss[i];
                ss[i] = ss[j];
                ss[j] = s;
            }
        }
    }
}

int find_phone_number(struct S** ss, int s){
    while(++i < count){
        if(ss[i]->phone == s)
            return 1;
    }
    return 0;
}

int main(int argc, char **argv) {
    
    int i;
    int count = 0;
    char buffer[10];
    

    struct S** ss = (struct S**) malloc(100*sizeof(struct S**));
    struct S* s = malloc(sizeof(*s));
   

    FILE *f = fopen(argv[1], "r");

    for(i = 0; i < 50; i++){
        
        s->firstName = (char*) malloc(80 * sizeof(s->firstName[0]));
        s->lastName = (char*) malloc(80 * sizeof(s->firstName[0]));
        s->emailAddress = (char*) malloc(80 * sizeof(s->firstName[0]));
        

       while((fscanf(f, "%s %s %d %s", &s->firstName, &s->lastName, &s->phone, &s->emailAddress))!= EOF){

            ss[count] = s;
            count += 1;
            {
                int command = 10;
                while(command != 0){
                    char* val = malloc(100*sizeof(val[0]));
                    gets(buffer);
                    command = atoi(buffer);
                    gets(buffer);
                    strcpy(val, buffer);
                    switch(command){
                        case 1:
                            printf("looking for email %s\n", val);
                            set_email(ss);
                            printf("found it? %d\n", find_email(ss, val));
                            break;
                        case 2:
                            printf("looking for firstname %s\n", val);
                            set_first_name(ss);
                            printf("found it? %d\n", find_first_name(ss, val));
                            break;
                        case 3:
                            printf("looking for lastname %s\n", val);
                            set_last_name(ss);
                            printf("found it? %d\n", find_last_name(ss, val));
                            break;
                        case 4:
                            printf("looking for email %s\n", val);
                            set_phone_number(ss);
                            printf("found it? %d\n", find_phone_number(ss, atoi(val)));
                        default:
                            break;
                    }
                }
            }
        }
    }}
