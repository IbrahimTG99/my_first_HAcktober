# a simple parser for python. use get_number() and get_word() to read
def parser():
    while 1:
        data = list(input().split(' '))
        for number in data:
            if len(number) > 0:
                yield(number)   

input_parser = parser()

def get_word():
    global input_parser
    return next(input_parser)

def get_number():
    data = get_word()
    try:
        return int(data)
    except ValueError:
        return float(data)

# numpy and scipy are available for use
import numpy
import scipy

N=get_number()
cond=False
apoint=[]
output=[]

for i in range (N):
    x=get_number()
    apoint=[0]*(x+1)
    for j in range(x):
        L=get_number()
        R=get_number()
        D=R-L
        for k in range(D+1):
            if(apoint[L+k-1]):
                cond=True
            else:
                apoint[L+k-1]=1
                cond=False
                break
    if cond:
        print("impossible")
    else:
        output=[0]*(x)
        for l in range(len(apoint)-1):
            if apoint[l]==1:
                output[l]=l+1
        print(' '.join(str(o) for o in output))