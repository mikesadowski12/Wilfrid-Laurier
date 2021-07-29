from itertools import permutations

INT_MAX = 2147483647
matrix = [
  [0,10,11,12,13,14],
  [10,0,15,16,17,18],
  [11,15,0,19,20,21],
  [12,16,19,0,22,23],
  [13,17,20,22,0,24],
  [14,18,21,23,24,0],
]
NUM_NODES = len(matrix[0])
nodes = [None] * NUM_NODES

rootNode = None
visited = []
unvisited = [None] * NUM_NODES
tree = [None] * NUM_NODES

path = []

def initialize():
  for i in range(0, NUM_NODES):
    nodes[i] = i
    tree[i] = []
    unvisited[i] = i

def MST_Prim():
  global rootNode

  currentNode = None
  nextNode = None
  nextNodePos = None

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

def PreorderTraversal(node):
  path.append(node)

  for i in range(0, len(tree[node])):
    PreorderTraversal(tree[node][i])

def ApproxTSPTour():
  cost = 0

  initialize()
  MST_Prim()
  PreorderTraversal(rootNode)
  path.append(rootNode) # go back to the first city, can do this since we are assuming a complete graph as per the spec

  # calculate the total cost of the trip
  for i in range(0, len(path) - 1):
    src = path[i]
    dest = path[i + 1]
    cost += matrix[src][dest]

  print("Approximate Cost: ", cost)

def TSPTour():
  cost = INT_MAX
  nodes = []

  for i in range(1, NUM_NODES): # we start at node 0 so don't include it
    nodes.append(i)

  unvisited = permutations(nodes) # get all combinations of points

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

ApproxTSPTour()
TSPTour()
