from itertools import permutations

INT_MAX = 2147483647

rootNode = None
path = []

def MST_Prim(matrix):
  global rootNode

  nodeCount = len(matrix[0])
  currentNode = None
  nextNode = None
  nextNodePos = None
  visited = []
  unvisited = [None] * nodeCount
  tree = [None] * nodeCount

  for i in range(0, nodeCount):
    tree[i] = []
    unvisited[i] = i

  rootNode = unvisited[0]
  visited.append(rootNode)

  unvisited.pop(0)

  while len(unvisited) > 0:
    max = INT_MAX

    for i in range(0, len(visited)):
      for j in range(0, len(unvisited)):
        cost = matrix[visited[i]][unvisited[j]]

        if cost < max:
          max = cost
          nextNodePos = j
          currentNode = visited[i]
          nextNode = unvisited[j]

    visited.append(unvisited[nextNodePos])
    unvisited.pop(nextNodePos)
    tree[currentNode].append(nextNode)

  return tree

def PreorderTraversal(node, tree):
  path.append(node)

  for i in range(0, len(tree[node])):
    PreorderTraversal(tree[node][i], tree)

def ApproxTSPTour(matrix):
  if len(matrix) <= 0:
    print("Approximate Cost: ", 0)
    return

  global rootNode, path
  rootNode = None
  path = []
  cost = 0

  tree = MST_Prim(matrix)
  PreorderTraversal(rootNode, tree)
  path.append(rootNode) # go back to the first city, can do this since we are assuming a complete graph as per the spec

  # calculate the total cost of the trip
  for i in range(0, len(path) - 1):
    src = path[i]
    dest = path[i + 1]
    cost += matrix[src][dest]

  print("Approximate Cost: ", cost)

def TSPTour(matrix):
  if len(matrix) <= 0:
    print("Cost: ", 0)
    return

  cost = INT_MAX
  nodes = []
  nodeCount = len(matrix[0])

  for i in range(1, nodeCount): # we start at node 0 so don't include it
    nodes.append(i)

  unvisited = permutations(nodes) # get all combinations of points (assuming a complete graph as per the spec)

  # all paths that are possible and choose the shortest (naive/brute force)
  for node in unvisited:
    currentNodeCost = 0
    k = 0

    for path in node:
      currentNodeCost += matrix[k][path]
      k = path

    currentNodeCost += matrix[k][0]
    cost = min(cost, currentNodeCost)

  print("Cost: ", cost)

print("======================")
testCase1 = [
  [0, 12, 13, 11, 14, 10],
  [12, 0, 16, 17, 18, 19],
  [13, 16, 0, 19, 20, 21],
  [11, 17, 19, 0, 22, 23],
  [14, 18, 20, 22, 0, 24],
  [10, 19, 21, 23, 24, 0],
]
print("Test Case #1")
ApproxTSPTour(testCase1)
TSPTour(testCase1)
print("======================")

testCase2 = [
  [0, 9, 11, 20],
  [9, 0, 40, 25],
  [11, 40, 0, 36],
  [20, 25, 36, 0]
]
print("Test Case #2")
ApproxTSPTour(testCase2)
TSPTour(testCase2)
print("======================")

testCase3 = [
  [0, 12, 13, 11, 14, 10],
  [12, 0, 15, 16, 17, 18],
  [13, 15, 0, 19, 20, 21],
  [11, 16, 19, 0, 22, 23],
  [14, 17, 20, 22, 0, 24],
  [10, 18, 21, 23, 24, 0],
]
print("Test Case #3")
ApproxTSPTour(testCase3)
TSPTour(testCase3)
print("======================")

testCase4 = [
  [0, 122, 130, 117, 145, 106],
  [122, 0, 151, 163, 174, 180],
  [130, 151, 0, 194, 208, 218],
  [117, 163, 194, 0, 228, 234],
  [145, 174, 208, 228, 0, 244],
  [106, 180, 218, 234, 244, 0],
]
print("Test Case #4")
ApproxTSPTour(testCase4)
TSPTour(testCase4)
print("======================")

testCase5 = [
  [0, 12, 133, 8, 134, 10],
  [12, 0, 154, 164, 174, 184],
  [133, 154, 0, 19, 20, 21],
  [8, 164, 19, 0, 22, 23],
  [134, 174, 20, 22, 0, 24],
  [10, 184, 21, 23, 24, 0],
]
print("Test Case #5")
ApproxTSPTour(testCase5)
TSPTour(testCase5)
print("======================")

testCase6 = []
print("Test Case #6")
ApproxTSPTour(testCase6)
TSPTour(testCase6)
print("======================")
