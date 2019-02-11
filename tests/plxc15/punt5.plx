int x;
x = 7;
int *p;
int *q;
int *r;
p = &x;
q = &x;
x = (*p+*q) ;
r = p+q ;
r = &x;
print (*p * *q * *r);

