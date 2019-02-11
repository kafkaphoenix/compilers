int x;
x=2;
int y;
y=1;
switch (x) {
   case 1:
      x = x*2;
   case 2:
      switch (y) {
        case 1:
           x = x+1;
        case 2:
           x = x+1;
           break;
       default:
           y = 5;
      }
      y=x+2;
    case 3:
      y=y+3;
      break;
}
print(x);
print(y);

