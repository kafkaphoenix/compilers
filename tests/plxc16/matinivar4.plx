int x1[5] = {1,2,3,4,5};
int x2[5] = {6,7,8,9,10};
int x3[5] = {11,12,13,14,15};
int a[3][5] = {x1,x2,x3};
int b[3][5] = {x3,x2,{1,2,3,4,5}}  ;
int c[2][3][5] = {a,b};
int prod;
int i;
int j;
int k;
for(i=0; i<2; i=i+1) {
   for(j=0; j<3; j=j+1) {
      for(k=0; k<5; k=k+1) {
         prod = prod + i*j*k * c[i][j][k] ;
      }
   }
}
print(prod);

