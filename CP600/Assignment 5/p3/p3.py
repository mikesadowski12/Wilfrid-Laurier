class Node:
  def __init__(self, k):
    self.left = None
    self.right = None
    self.key = k

def buildTestTree():
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

def BST_LCA(t, x, y):
  if t is None:
    return None

  if (x < t.key and y < t.key):
    return BST_LCA(t.left, x, y)

  if (x > t.key and y > t.key):
    return BST_LCA(t.right, x, y)

  return t

root = buildTestTree()
node = BST_LCA(root, 6, 19)

print("Output: LCA is ", node.key)
