int x;
x = 3;
int *px;
int **ppx;
px = &x;
ppx = &px;
print(**ppx);
