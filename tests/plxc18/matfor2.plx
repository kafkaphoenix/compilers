char st[3];
int m[3];
m[0] = 0;
m[1] = 2;
m[2] = 4;
char ch;
int i,j;
for(i : m) {
   st[j] = (char) ('K' + i);
   j=j+1;
}
for(ch : st) {
   print(ch);
}

