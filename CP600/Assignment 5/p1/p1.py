class Node:
  def __init__(self, k):
    self.left = None
    self.right = None
    self.key = k

def buildTestTree1():
  root = Node(11)

  root.left = Node(3)
  root.left.left = Node(1)
  root.left.right = Node(9)

  root.right = Node(15)
  root.right.left = Node(12)
  root.right.right = Node(14)

  return root

def buildTestTree2():
  root = Node(99)

  root.left = Node(50)
  root.left.left = Node(49)
  root.left.right = Node(51)

  root.right = Node(102)
  root.right.left = Node(100)
  root.right.right = Node(101)

  return root

def buildTestTree3():
  root = Node(3)

  root.left = Node(2)
  root.right = Node(4)

  return root

def BST_Ceil(x, k):
  if x == None: # Case 0: anchor for recursion, there is no floor under x
    return -1

  if k == x.key: # Case 1: this is the highest possible key
    return x.key

  if k > x.key: # Case 2: ceil is in the right tree of x, i.e., x.right
    return BST_Ceil(x.right, k)

  if k < x.key: # Case 3: find the ceil from left subtree, i.e., x.left
    t = BST_Ceil(x.left, k)

    if t >= k: # t is not empty, t.key > x.key so t.key is the ceil
      return t

  return x.key # t is empty, i.e., no ceil from left subtree return x

def printTree(root):
  for num in range(20):
    ceil = BST_Ceil(root, num)
    print(num, "-", ceil)

root1 = buildTestTree1()
root2 = buildTestTree2()
root3 = buildTestTree3()

print("Test Case 1")
printTree(root1)
print("------------------------")

print("Test Case 2")
printTree(root2)
print("------------------------")

print("Test Case 3")
printTree(root3)
print("------------------------")