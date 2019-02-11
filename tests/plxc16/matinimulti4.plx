int a[2][3];
int suma;
int i;
int j;
int signo = 1;
a = { {1,2,3},{4,5,6} };
for(i=0; i<2; i=i+1) {
   for(j=0; j<3; j=j+1) {
         if (signo==1) signo=-1; else signo=1;
         suma = suma + signo*a[i][j];
   }
}
print(suma);

