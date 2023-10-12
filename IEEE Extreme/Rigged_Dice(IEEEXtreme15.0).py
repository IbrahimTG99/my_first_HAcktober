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

iterations = get_number()

for _ in range(iterations):
    t = get_number()
    sixes_1 = 0
    sum_1 = 0
    sixes_2 = 0
    sum_2 = 0
    for i in range(t):
        n1 = get_number()
        n2 = get_number()
        
        if n1 == 6: sixes_1 += 1
        if n2 == 6: sixes_2 += 1
        
        sum_1 += n1
        sum_2 += n2
        
        if n1 != n2:
            sixes_1, sixes_2 = sixes_2, sixes_1
            sum_1, sum_2 = sum_2, sum_1
            
    if sixes_1 > sixes_2:
        print(1)
    elif sixes_2 > sixes_1:
        print(2)
    else:
        if sum_1 > sum_2: print(1)
        else: print(2)
    