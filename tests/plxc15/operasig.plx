int x = 2;
int z = 4;
float y = 3;
float h = 10;


print(x+=z);
print(h+=y);
print(h+=x);


print(x-=z);
print(h-=y);
print(h-=x);


print(x*=z);
print(h*=y);
print(h*=x);


print(x/=z);
print(h/=y);
print(h/=x);

print(x%=z);
print(h%=y);
print(h%=x);


// Error
print(x+=y);