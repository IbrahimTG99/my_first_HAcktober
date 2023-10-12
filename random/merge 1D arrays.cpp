//Merging two Single Dimensional sorted arrays merging two Single Dimensional sorted arrays using arrrays
#include <iostream>
using namespace std;
int* merge(int arr1[],int arr1Size, int arr2[], int arr2Size, int arr3[] )
{
int i=0, j=0, k=0;
while(i<arr1Size && j<arr2Size)
{
if(arr1[i]<arr2[j])
{arr3[k]=arr1[i]; i++;}
else
{arr3[k]=arr2[j]; j++;}
k++;
}
//Remaining elements of arr1 and arr2 get appended on the end of arr3
while(i<arr1Size)
{arr3[k]=arr1[i]; k++;i++;}
while (j<arr2Size)
{arr3[k]=arr2[j]; k++;j++;}
return arr3;
}
void print(int arr[],int size)
{
for (int i = 0; i < size; i++)
{
cout << arr[i] << ", ";
}
cout << endl;
}
int main()
{
int A[5]={1,2,4,5,7};
int B[5]={6,8,9,10,11};
int C[10]={0};
print (merge(A,5,B,5,C),10);
return 0;
}
