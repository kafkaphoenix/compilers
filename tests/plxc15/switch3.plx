int x;
x=2;
int y;
y=x;
switch (x) {
   case 1:
      y=y+1;
    case 2:
      y=y+2;
    case 3:
      y=y+3;
    default:
      y=y+10;
}
print(x*y);
