int a[3][2];
int i;
int j;
for (i=1; i<=3; i=i+1) {
  for(j=1; j<=2; j=j+1) {
     a[i-1][j-1] = 2*(i-1)+j;
  }
}
int factorial  = 1; 
for (i=1; i<=3; i=i+1) {
  for(j=1; j<=2; j=j+1) {
     factorial  = factorial  * a[i-1][j-1];
  }
}
print(factorial);
