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

def drawC(point, radius, depth):
    if depth == 0:
        return

    drawCircles(point, radius)
    drawC(point, radius/3, depth - 1)
    #drawC(Point(c.p1.getX(), c.p1.getY() - c.getRadius()/1.34), radius,depth-1)
    #drawC(point, radius/3, depth-1)
    #drawC(Point(166.6666666666667, 500.0), radius / 3, depth - 1)
    #drawC(Point(833.3333333333333, 500.0), radius / 3, depth - 1)
    #drawC(Point(333.33333333333337, 208.95522388059706), radius / 3, depth - 1)
   # drawC(Point(666.6666666666666, 208.95522388059706), radius / 3, depth - 1)
   # drawC(Point(333.33333333333337, 791.044776119403), radius / 3, depth - 1)
    #drawC(Point(666.6666666666666, 791.044776119403), radius / 3, depth - 1)
   # drawC(Point(c.p2.getX() + c.getRadius(), c.p1.getY() + c.getRadius()), radius/3, depth-1)
   # drawC(Point(c.p2.getX() + c.getRadius(), c.p1.getY() + c.getRadius()), radius / 3, depth - 1)
   # drawC(Point(c.p2.getX() + c.getRadius(), c.p1.getY() + c.getRadius()), radius / 3, depth - 1)
   # drawC(Point(c.p2.getX() + c.getRadius(), c.p1.getY() + c.getRadius()), radius / 3, depth - 1)

def drawCircles(point, radius):
    
   # if depth == 0:
  #      return

    colour = colourGene()
    radius = radius / 3
    
    center = Circle(point,radius)
    center.draw(win)
    center.setFill(colour)

    relative = center.getRadius()

    point = Point (center.p1.getX() - relative, center.p1.getY() + relative)
    left = Circle(point,radius)
    left.draw(win)
    left.setFill(colour)
    print (point)

    point = Point(center.p2.getX() + relative, center.p1.getY() + relative)
    right = Circle(point,radius)
    right.draw(win)
    right.setFill(colour)
    print (point)

    point = Point(center.p1.getX(),center.p1.getY() - relative/1.34)
    topLeft = Circle(point,radius)
    topLeft.draw(win)
    topLeft.setFill(colour)
    print(point)
    point = Point(center.p2.getX(),center.p1.getY() - relative/1.34)
    topRight = Circle(point,radius)
    topRight.draw(win)
    topRight.setFill(colour)
    print(point)
    point = Point(center.p1.getX(), center.p2.getY() + relative/1.34)
    bottomLeft = Circle(point,radius)
    bottomLeft.draw(win)
    bottomLeft.setFill(colour)
    print(point)
    point = Point(center.p2.getX(),center.p2.getY() + relative/1.34)
    bottomRight = Circle(point,radius)
    bottomRight.draw(win)
    bottomRight.setFill(colour) 
    print(point)
    #drawCircles(point, radius, depth + -1)
drawCircle(point,radius)
drawC(point, radius, 4)
#drawCircles(point,radius,1)
#drawCircle(point,radius)
#drawCircles(point,radius, 3)
win.getMouse()
