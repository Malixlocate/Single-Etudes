import itertools
import shlex
import os
from argparse import ArgumentParser

length = 0
operators = []

parser = ArgumentParser()
parser.add_argument('-file', metavar='FILE', help='input a file using -file <filename>')
args = parser.parse_args()

with open(args.file) as file:
    inputs = []
    count = 0
    for line in file:
        count += 1
        nextline = file.next()
        values.append(shlex.split(line + nextline))

def leftToRight(find[], target, current, depth):
    
    #Base case 
    if(depth >= len(values) + 1) {
        return False
        }
    
    length = len(values[depth])

    if(!((values[depth] + current) > target) or !((current * values[depth]) > target)):
        
        if(depth == len(values) - 1):
            
            if(current + values[depth] == target):



