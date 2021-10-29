def precedence(op):
	
	if op == '+' or op == '-':
		return 1
	if op == '*':
		return 2
	return 0

def applyOp(a, b, op):
	
	if op == '+': return a + b
	if op == '-': return a - b
	if op == '*': return a * b

def evaluate(tokens):
	
	values = []
	ops = []
	i = 0
	
	while i < len(tokens):
		if tokens[i] == ' ':
			i += 1
			continue
		elif tokens[i] == '(':
			ops.append(tokens[i])
		
		elif tokens[i].isdigit():
			val = 0
			
			while (i < len(tokens) and
				tokens[i].isdigit()):
			
				val = (val * 10) + int(tokens[i])
				i += 1
			
			values.append(val)
			
			i-=1
		
		elif tokens[i] == ')':
			if ops[-1] == '(' and values==[]:
				return None
			while len(ops) != 0 and ops[-1] != '(' and len(values)>1:
			
				val2 = values.pop()
				val1 = values.pop()
				op = ops.pop()
				
				values.append(applyOp(val1, val2, op))
			
			ops.pop()
		
		else:
			while (len(ops) != 0 and
				precedence(ops[-1]) >=
				precedence(tokens[i])):
				if len(values) == 0:
					return None
				val2 = values.pop()
				val1 = values.pop()
				op = ops.pop()
				
				values.append(applyOp(val1, val2, op))
			
			ops.append(tokens[i])
		
		i += 1
	while len(ops) != 0 and len(values)!=0:
		
		val2 = values.pop()
		if len(values)!=0:
			val1 = values.pop()
		else:
			return None
		op = ops.pop()
				
		values.append(applyOp(val1, val2, op))
	if values:
		return values[-1]
	else:
		return None

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

a = get_number()
#a=1
ans=0
for i in range(a):
	expression = get_word()
	ans = evaluate(expression)
	if ans:
		if ans<0:
			print((ans+1000000007)%1000000007)
		else:	
			print(ans%1000000007)
	else:
		print("invalid")
