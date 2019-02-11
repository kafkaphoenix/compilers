int i1,i2,i3;
int m[3]={0,1,2};
for(i1:m) {
  for(i2:m)
    for(i3:m) 
      m[(i2+i3)%3] = i2+i3;
  print(i1);  
}
