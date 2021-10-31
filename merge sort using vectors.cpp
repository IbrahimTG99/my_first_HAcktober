#include <iostream>
using namespace std;
void mergeVector(vector<int> &arr1,vector<int> &arr2,vector<int> &arr3)
{
int i=0, j=0,k=0;
int arr1Size=arr1.size();
int arr2Size=arr2.size();
while(i< arr1Size && j< arr2Size)
{
if(arr1[i]<arr2[j])
{arr3[k]=arr1[i]; i++;}
else
{arr3[k]=arr2[j]; j++;}
k++;
}
//Remaining elements of arr1 and arr2 get appended on the end of arr3
while(i<arr1Size)
{arr3[k]=arr1[i]; i++;k++;}
while(j<arr2Size)
{arr3[k]=arr2[j]; j++;k++;}
}
void sort(vector<int> &arr)
{
int size=arr.size();
if(size<=1) return;//base case
int mid = size/2;//splitter
vector<int> left, right;
for (int i = 0; i < mid; i++)
left.push_back(arr[i]);
for (int j = 0; j < size-mid; j++)
right.push_back (arr[mid+j]);
sort(left);sort(right);
mergeVector(left,right,arr);
}
void printVector(vector<int> &v)
{
for (int i = 0; i < v.size(); i++)
{cout << v[i] << ",";}
cout << endl;
}
int main()
{
vector<int> arr{1,54,6,68,13,1,6,46,89,49,46,513,1654,984};
printVector(arr);
sort(arr);

printVector(arr);
return 0;
}
