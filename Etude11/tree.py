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
                self.add.parent = self
            else:
                self.add.insert(value)

            if self.multiply is None:
                self.multiply = Node(value)
                self.multiply.parent = self
            else:
                self.multiply.insert(value)
        else:
            self.data = data

    def printInOrder(self):
        if self.add:
            self.add.printInOrder()
        print(self.data),
        if self.multiply:
            self.multiply.printInOrder()


root = Node(1)
root.insert(2)
root.insert(3)

root.printInOrder()


