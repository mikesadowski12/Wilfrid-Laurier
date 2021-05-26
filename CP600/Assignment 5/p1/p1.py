class Node:
  def __init__(self, k):
    self.left = None
    self.right = None
    self.key = k

def buildTestTree():
  root = Node(11)

  root.left = Node(3)
  root.left.left = Node(1)
  root.left.right = Node(9)

  root.right = Node(15)
  root.right.left = Node(12)
  root.right.right = Node(14)

  return root

def BST_Ceil(x, k):
  if x == None: # Case 0: anchor for recursion, there is no floor under x
    return -1

  if k == x.key: # Case 1: this is the highest possible key
    return x.key

  if k > x.key: # Case 2: ceil is in the right tree of x, i.e., x.right
    return BST_Ceil(x.right, k)

  if k < x.key: # Case 3: find the ceil from left subtree, i.e., x.leeft
    t = BST_Ceil(x.left, k)

    if t >= k: # t is not empty, t.key > x.key so t.key is the ceil
      return t

  return x.key # t is empty, i.e., no ceil from left subtree return x

root = buildTestTree()

for num in range(20):
  ceil = BST_Ceil(root, num)
  print(num, "-", ceil)
