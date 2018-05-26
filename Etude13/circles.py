from graphics import * 
import random
import shlex
from argparse import ArgumentParser

parser = ArgumentParser()
parser.add_argument('-file', metavar='FILE', help='file to give circles inputs')
args = parser.parse_args()

windowWidth= 1000
windowHeight = 1000
windowTitle = "Circ-les"
win = GraphWin(windowTitle, windowWidth, windowHeight)
xCoord = 500
yCoord = 500
point = Point(xCoord, yCoord)
radius = 500
depth = 3
colours = []
ratio = []

if(args.file):    
    with open(args.file) as file:
        inputs = []
        for line in open(args.file):
            line = shlex.split(line)
            inputs.append(line)
    depth = len(inputs)
    """
    for input in inputs:
            if "/" in input[0]:
                input[0] = input[0].split('/', 1)[-1]
    """

def colourGene():
    r = random.randrange(256)
    g = random.randrange(256)
    b = random.randrange(256)
    return color_rgb(r,g,b)

def initializeColours():
    for i in range(len(inputs)):
        inputColour = color_rgb((int(inputs[i][1])),int((inputs[i][2])),int((inputs[i][3])))
        colours.append(inputColour)
        colours.reverse()
def drawCircle(point, radius):
    colour = colourGene()
    c = Circle(point, radius)    # instantiate a Circle object
    c.draw(win)                  # draw the Circle object to the window
    c.setFill(colour)

def sevenCircles(point, radius, depth, colour):
    

    if depth == 0:
        return

    radius = radius / 3

    if(args.file):
        #radius = radius / (ratio[depth])
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

    sevenCircles(leftPoint,radius,depth -1,colour)
    sevenCircles(centerPoint,radius,depth-1,colour)
    sevenCircles(rightPoint,radius,depth-1,colour) 
    sevenCircles(topLeftPoint,radius,depth-1,colour)
    sevenCircles(topRightPoint,radius,depth-1,colour)
    sevenCircles(bottomLeftPoint,radius,depth-1,colour)
    sevenCircles(bottomRightPoint,radius,depth-1,colour)

initializeColours()
drawCircle(point,radius)
if(args.file):
    colour = colours[depth-1]
sevenCircles(point,radius,depth,colour)
win.getMouse()
