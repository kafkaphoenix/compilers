int a[3] = {1,2,3};
int b[3] = {4,5,6};
int c[2][3] = {a,b};
int d[2][3] = c;
int suma;
int i;
int j;
int signo; 
for(i=0; i<2; i=i+1) {
   for(j=0; j<3; j=j+1) {
         if (signo==1) signo = -1; else signo = 1;
         suma = suma + signo*d[i][j];
   }
}
print(suma);

