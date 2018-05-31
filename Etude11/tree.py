class Node:

    # Constructor to create a node
    def __init__(self , value):
        self.data = value
        self.parent = None;
        self.add = None
        self.multiply = None



    def insert(self, value):
        if self.data:
            if self.add is None:
                self.add = Node(value)
            else:
                self.add.insert(value)

            if self.multiply is None:
                self.multiply = Node(value)
            else:
                self.multiply.insert(value)
        else:
            self.data = data

    def printInorder(self):
        if self.add:
            self.add.printInorder()
        print(self.data),
        if self.multiply:
            self.multiply.printInorder()


root = Node(1)
root.insert(2)
root.insert(3)

root.printTree()


