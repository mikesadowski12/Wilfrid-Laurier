class Node:
  def __init__(self, k):
    self.left = None
    self.right = None
    self.key = k

def buildTestTree1():
  root = Node(25)

  root.left = Node(10)
  root.left.left = Node(1)
  root.left.right = Node(19)
  root.left.left.right = Node(6)
  root.left.right.left = Node(13)
  root.left.right.left = Node(16)

  root.right = Node(29)
  root.right.left = Node(12)
  root.right.right = Node(14)

  return root

def buildTestTree2():
  root = Node(100)

  root.left = Node(20)
  root.left.left = Node(30)
  root.left.right = Node(40)
  root.left.left.right = Node(10)
  root.left.right.left = Node(11)
  root.left.right.left = Node(12)

  root.right = Node(130)
  root.right.left = Node(14)
  root.right.right = Node(15)

  return root


def BST_LCA(t, x, y):
  if t is None:
    return None

  if (x < t.key and y < t.key):
    return BST_LCA(t.left, x, y)

  if (x > t.key and y > t.key):
    return BST_LCA(t.right, x, y)

  return t

root1 = buildTestTree1()
node1 = BST_LCA(root1, 6, 19)
print("Test 1 - Output: LCA is ", node1.key)

root2 = buildTestTree2()
node2 = BST_LCA(root2, 60, 190)
print("Test 2 - Output: LCA is ", node2.key)
