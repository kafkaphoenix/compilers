int x;
x=2;
int y;
y=x;
switch ((x+x)/2) {
   case 1:
      y=y+1;
    case 1+1:
      y=y+2;
    case 1+2+3:
      y=y+3;
      break;
    default:
      y=y+10;
}
print(x*y);
