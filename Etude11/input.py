import shlex
import os
from argparse import ArgumentParser

parser = ArgumentParser()
parser.add_argument('-file', metavar='FILE', help='input a file using -file <filename>')
args = parser.parse_args()

with open(args.file) as file:
    values = []
    count = 0
    for line in file:
       # if count % 2 == 0:
            count += 1
            nextline = file.next()
            values.append(shlex.split(line + nextline))

print(values)
