int a[3][2][5];
int i;
int j;
int k;
int conta;
conta=0;
for(i=0;i<3;i=i+1) {
  for(j=0;j<2; j=j+1) {
     for(k=0; k<5; k=k+1) {
        a[i][j][k] = conta;
        conta = conta+1;
     }
  }
}   
print(a[2][1][3]);

