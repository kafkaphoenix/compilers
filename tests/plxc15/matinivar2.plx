int a[3];
int b[3];
int c[2][3];
int suma;
int i;
int j;
a = {1,2,3};
b = {4,5,6};
c = {a,b};
int signo; 
for(i=0; i<2; i=i+1) {
   for(j=0; j<3; j=j+1) {
   		 if (signo==1) signo = -1; else signo = 1;
         suma = suma + signo * c[i][j];
   }
}
print(suma);

