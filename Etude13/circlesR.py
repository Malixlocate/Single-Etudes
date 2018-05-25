from graphics import * 
import random

windowWidth = 1000
windowHeight = 1000
windowTitle = "Circ-les"
win = GraphWin(windowTitle, windowWidth, windowHeight)
xCoord = 500
yCoord = 500
point = Point(xCoord, yCoord)
radius = 500
layer = 0

def colourGene():
    r = random.randrange(256)
    g = random.randrange(256)
    b = random.randrange(256)
    return color_rgb(r,g,b)


def drawCircle(point, radius):
    colour = colourGene()
    c = Circle(point, radius)    # instantiate a Circle object
    c.draw(win)                  # draw the Circle object to the window
    c.setFill(colour)
     

def drawCircles(point, radius):
    colour = colourGene()
    radius = radius / 3
     
    center = Circle(point,radius)
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
    bottomLeft = Circle(point,radius)
    bottomLeft.draw(win)
    bottomLeft.setFill(colour)

    bottomRightPoint = Point(center.p2.getX(),center.p2.getY() + relative/1.34)
    bottomRight = Circle(point,radius)
    bottomRight.draw(win)
    bottomRight.setFill(colour)

    global layer
    layer+=1
    if layer > 3 :
        return
    

drawCircle(point,radius)
drawCircles(point,radius)
win.getMouse()
