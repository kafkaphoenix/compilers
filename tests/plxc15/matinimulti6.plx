int a[2][3][5];
int b[3][2][5] = { {{1,2,3,4,5},{2,4,3,2,1}}, {{3,6,5,4,3},{4,2,3,5,4}}, {{5,4,6,3,2},{6,4,3,2,1}} } ;
int suma;
int prod=1;
int i;
int j;
int k;
for(i=0; i<3; i=i+1) {
   for(j=0; j<2; j=j+1) {
      for(k=0; k<5; k=k+1) {
         suma = suma + b[i][j][k];
         prod = prod + b[i][j][k] * b[i][j][k];
      }
   }
}
print(suma);
print(prod);

