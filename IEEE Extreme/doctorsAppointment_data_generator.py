# # a simple parser for python. use get_number() and get_word() to read
# def parser():
#     while 1:
#         data = list(input().split(' '))
#         for number in data:
#             if len(number) > 0:
#                 yield(number)   

# input_parser = parser()

# def get_word():
#     global input_parser
#     return next(input_parser)

# def get_number():
#     with open("in.txt") as f:
#         data = f.read().split('\n')
#         for d in data:
#             for val in d.split(' '):
#                 yield val

# # # numpy and scipy are available for use
# # import numpy
# # import scipy

# iterations = get_number()

# for _ in range(iterations):
#     t = get_number()
#     sixes_1 = 0
#     sum_1 = 0
#     sixes_2 = 0
#     sum_2 = 0
#     for i in range(t):
#         n1 = get_number()
#         n2 = get_number()
        
#         if n1 == 6: sixes_1 += 1
#         if n2 == 6: sixes_2 += 1
        
#         sum_1 += n1
#         sum_2 += n2
        
#         if sum_1 != sum_2:
#             sixes_1, sixes_2 = sixes_2, sixes_1
#             sum_1, sum_2 = sum_2, sum_1
            
#     if sixes_1 > sixes_2:
#         print(1)
#     elif sixes_2 > sixes_1:
#         print(2)
#     else:
#         if sum_1 > sum_2: print(1)
#         else: print(2)


import random

sum_1 = 0
sum_2 = 0
rev = False

f= open("in.txt", 'w')

for i in range(1000):
    d = random.randint(0, 6)
    dr = min(random.randint(0, 7), 6)

    if not rev:
        sum_1 += d
        sum_2 += dr
    else:
        sum_1 += dr
        sum_2 += d

    if rev:
        print(dr, d, file=f)
    else:
        print(d, dr, file=f)

    if sum_1 != sum_2:
        rev = not rev

f.close()