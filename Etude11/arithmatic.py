import itertools
import shlex
import os
from argparse import ArgumentParser

parser = ArgumentParser()
parser.add_argument('-file', metavar='FILE', help='input a file using -file <filename>')
args = parser.parse_args()
targetFound = False

with open(args.file) as file:
    inputs = []
    count = 0
    for line in file:
        count += 1
        nextline = file.next()
        inputs.append(shlex.split(line + nextline))

def leftToRight(current, equation, depth):
    global targetFound 
    #print(equation)
    if(targetFound):
        return

    elif(current != target and depth >= len(values) -1):
        return

    elif(current == target and depth >= len(values) -1):
        finished = True
        print("L" + str(target) + " "+ str(equation))
    else:
        depth = depth + 1
        nextVal = values[depth]
        leftToRight(values[depth] + current, equation + "+" + str(nextVal), depth)
        leftToRight(values[depth] * current, equation + "*" + str(nextVal), depth)

for i in range(len(inputs)):
    values = []
    targetFound = False
    for j in range(len(inputs[i]) -2):
        values.append(inputs[i][j])

    values = list(map(int,values))

    target = inputs[i][len(inputs[i]) -2]
    target = int(target)

    sign = inputs[i][len(inputs[i]) -1]

    depth = 0
    equation = ""
    
    if(sign is 'L'):
        leftToRight(values[depth], str(values[depth]), depth)
    elif(sign is 'N'):
        print("Normal order not implemented")
    else:
        print("Incorrect input, please use 'L' or 'N' for the operation sign")
    
"""
Normal order is much more difficults to implement because we would have to consider BEDMAS order, effectily pushing the sign onto the equation, evaluating that equation and then poping that sign off if it was not the deired sign
