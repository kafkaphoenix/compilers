int x;
x = 7;
int *p;
int **q;
int ***r;
p = &x;
q = &p;
r = &q;
print (*p * ***r);

