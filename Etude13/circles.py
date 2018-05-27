from graphics import * 
import random
import shlex
from argparse import ArgumentParser
from fractions import Fraction

parser = ArgumentParser()
parser.add_argument('-file', metavar='FILE', help='file to give circles inputs')
args = parser.parse_args()

windowWidth = 1000
windowHeight = 1000
windowTitle = "Wow. Much circles."
win = GraphWin(windowTitle, windowWidth, windowHeight)
xCoord = windowWidth / 2
yCoord = windowHeight / 2
point = Point(xCoord, yCoord)
radius = 500
depth = 2
colours = []
ratios = []

if(args.file):    
    with open(args.file) as file:
        inputs = []
        for line in open(args.file):
            line = shlex.split(line)
            inputs.append(line)
    depth = len(inputs) -1

#Generate Colours based on RGB values
def colourGene():
    r = random.randrange(256)
    g = random.randrange(256)
    b = random.randrange(256)
    return color_rgb(r,g,b)

#Initlailizes the colours array
def initializeColours():
    if(args.file):
        for i in range(len(inputs)):
            inputColour = color_rgb((int(inputs[i][1])),int((inputs[i][2])),int((inputs[i][3])))
            colours.append(inputColour)
            # colours.reverse()
    else:
       for i in range(depth+1):
         colours.append(colourGene())
    colours.reverse()

#Initliaizes the ratios array
def initializeRatios():
    for i in range(len(inputs)):
        if "/" in inputs[i][0]:
            inputRatio = float(Fraction(inputs[i][0]))
        else:
            inputRatio = float((inputs[i][0]))
        ratios.append(inputRatio)
    ratios.reverse()

#Draws an initial circle using a default or input set size
def drawCircle(point, radius, colour):

    c = Circle(point, radius)    # instantiate a Circle object
    c.draw(win)                  # draw the Circle object to the window
    c.setFill(colour)

def drawCircles(point, radius, depth, colour):
    

    if depth == 0:
        return

    if (args.file):
        radius = radius * ratios[depth]

        if (depth == len(inputs) - 1):
            drawCircle(point, radius, colour)
    else:
        drawCircle(point,radius,colour)


    if(args.file):
            if ratios[depth] != ratios[depth-1]:
                radius = radius * ratios[depth-1]
    else:
        radius = radius / 3

    colour = colours[depth-1]


    centerPoint = point
    center = Circle(centerPoint,radius)
    center.draw(win)
    center.setFill(colour)

    relative = center.getRadius()

    leftPoint = Point (center.p1.getX() - relative, center.p1.getY() + relative)
    left = Circle(leftPoint,radius)
    left.draw(win)
    left.setFill(colour)

    rightPoint = Point(center.p2.getX() + relative, center.p1.getY() + relative)
    right = Circle(rightPoint,radius)
    right.draw(win)
    right.setFill(colour)

    topLeftPoint = Point(center.p1.getX(),center.p1.getY() - relative/1.34)
    topLeft = Circle(topLeftPoint,radius)
    topLeft.draw(win)
    topLeft.setFill(colour)

    topRightPoint = Point(center.p2.getX(),center.p1.getY() - relative/1.34)
    topRight = Circle(topRightPoint,radius)
    topRight.draw(win)
    topRight.setFill(colour)

    bottomLeftPoint = Point(center.p1.getX(), center.p2.getY() + relative/1.34)
    bottomLeft = Circle(bottomLeftPoint,radius)
    bottomLeft.draw(win)
    bottomLeft.setFill(colour)

    bottomRightPoint = Point(center.p2.getX(),center.p2.getY() + relative/1.34)
    bottomRight = Circle(bottomRightPoint,radius)
    bottomRight.draw(win)
    bottomRight.setFill(colour)

    drawCircles(leftPoint, radius, depth - 1, colour)
    drawCircles(centerPoint, radius, depth - 1, colour)
    drawCircles(rightPoint, radius, depth - 1, colour)
    drawCircles(topLeftPoint, radius, depth - 1, colour)
    drawCircles(topRightPoint, radius, depth - 1, colour)
    drawCircles(bottomLeftPoint, radius, depth - 1, colour)
    drawCircles(bottomRightPoint, radius, depth - 1, colour)

initializeColours()

print(colours)
if(args.file):
    initializeRatios()
    ratio = ratios[depth]
    colour = colours[depth]

print(len(colours))
colour = colours[depth]
drawCircles(point, radius, depth, colour)
win.getMouse()
