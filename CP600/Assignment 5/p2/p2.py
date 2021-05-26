class Node:
  def __init__(self, k):
    self.left = None
    self.right = None
    self.key = k

def buildBalancedTestTree():
  root = Node(11)

  root.left = Node(3)
  root.left.left = Node(1)
  root.left.right = Node(9)

  root.right = Node(15)
  root.right.left = Node(12)
  root.right.right = Node(14)

  return root

def buildNotBalancedTestTree():
  root = Node(11)

  root.left = Node(3)
  root.left.left = Node(1)
  root.left.right = Node(9)
  root.left.left.left = Node(12)

  root.right = Node(15)

  return root

def getHeight(root):
  if root is None:
    return 0

  heightLeft = getHeight(root.left)
  heightRight = getHeight(root.right)

  return max(heightLeft, heightRight) + 1



def isBalanced(root):
  if root is None:
    return True

  heightLeft = getHeight(root.left)
  heightRight = getHeight(root.right)

  difference = abs(heightLeft - heightRight) # 1, -1, 0
  isLeftBalanced = isBalanced(root.left)
  isRightBalanced = isBalanced(root.right)

  if isLeftBalanced and isRightBalanced and (difference <= 1):
    return True

  return False

tree1 = buildBalancedTestTree()
tree2 = buildNotBalancedTestTree()

print("Tree 1 isBalanced() is ", isBalanced(tree1))
print("Tree 2 isBalanced() is ", isBalanced(tree2))
