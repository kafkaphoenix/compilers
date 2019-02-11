int x;
int y;
int z;
x = 1+2*3; 
y = 2*2+3; 
z = x-y ?: (y-x ?: x*y) ?: x+y ;
print(z);
