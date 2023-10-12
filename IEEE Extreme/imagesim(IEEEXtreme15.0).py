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
import numpy as np
import scipy.signal

a = get_number()

for i in range(a):
    ans = 0
    h = get_number()
    w = get_number()
    
    kernel = np.array([])
    grid = np.array([])
    for i1 in range(h):
        t = list(get_word().replace('#', '1').replace('.', '0'))
        t = np.array([int(i) for i in t])
        kernel = np.append(kernel, t, axis=0)
    kernel = kernel.reshape((h, w))
    h = get_number()
    w = get_number()
    
    for i2 in range(h):
        t = list(get_word().replace('#', '1').replace('.', '0'))
        t = np.array([int(i) for i in t])
        grid = np.append(grid, t, axis=0)
    grid = grid.reshape((h, w))
    
    for x in range(2):
        for _ in range(4):
            out = scipy.signal.convolve2d(kernel, grid)
            for i in out:
                for j in i:
                    ans = max(ans, j)
            kernel = np.rot90(kernel)
        kernel = np.flip(kernel, x)
        if (x == 1):
            kernel = np.flip(kernel, 0)
    
    out = scipy.signal.convolve2d(kernel, grid)
    for i in out:
        for j in i:
            ans = max(ans, j)
    
    print(int(ans))