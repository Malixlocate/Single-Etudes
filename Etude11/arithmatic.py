import itertools
import shlex
import os
from argparse import ArgumentParser


parser = ArgumentParser()
parser.add_argument('-file', metavar='FILE', help='input a file using -f <filename>')
args = parser.parse_args()

with open(args.file) as file:
    inputs = []
    for line1,line2 in args.file:
        inputs.append(line1)
        inputs.append(line2)
    print(inputs)
    
