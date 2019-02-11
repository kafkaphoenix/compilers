int x;
x=3;
int y;
y=x;
switch (x) {
    case 1:
     break;
    case 2:
    case 3:
    case 4:
      x=x+2;
      y=y+3;
      y=y+4;
      break;
    case 5:
    case 6:
      y=y+7;
    default:
      y=y+10;
}
print(x);
print(y);

