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
        record = matrix[visited[i]][unvisited[j]]

        if record < max:
          max = record
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
  cost = INT_MAX
  nodes = []
  nodeCount = len(matrix[0])

  for i in range(1, nodeCount): # we start at node 0 so don't include it
    nodes.append(i)

  unvisited = permutations(nodes) # get all combinations of points (assuming a complete graph as per the spec)

  # all paths that are possible and choose the shortest (naive/brute force)
  for i in unvisited:
    currentNodeCost = 0
    k = 0

    for j in i:
      currentNodeCost += matrix[k][j]
      k = j

    currentNodeCost += matrix[k][0]
    cost = min(cost, currentNodeCost)

  print("Cost: ", cost)

matrix = [
  [0,10,11,12,13,14],
  [10,0,15,16,17,18],
  [11,15,0,19,20,21],
  [12,16,19,0,22,23],
  [13,17,20,22,0,24],
  [14,18,21,23,24,0],
]

ApproxTSPTour(matrix)
TSPTour(matrix)
