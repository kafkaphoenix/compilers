int x;
int y;
int z;
x = 1;
y = 2;
z = 3;
x *= y -= z += x *= y += z;
x /= y %= z;
print (x*y*z+x+y+z);
