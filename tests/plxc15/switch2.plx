int x;
x=10;
int y;
y=x;
switch (x) {
   case 1:
      y=y+1;
      break;
    case 2:
      y=y+2;
      break;
    case 3:
      y=y+3;
      break;
    default:
      y=y+10;
}
print(x*y);
