visited = []

def hamiltonianCycle(Graph, nodeCount, currentNode):
  if currentNode not in visited:
    visited.append(currentNode)

    visitedCount = len(visited)
    if visitedCount == nodeCount:
      return visited

    for nextNode in Graph[currentNode]:
      cycle = hamiltonianCycle(Graph, nodeCount, nextNode)

      if cycle:
        return cycle

testCase1Graph = {
  1:[2],
  2:[3],
  3:[4],
  4:[1],
}

testCase2Graph = {
  1:[2],
  2:[3],
  3:[4],
  4:[2],
}

testCase3Graph = {
  1:[],
  2:[],
  3:[],
  4:[],
}

testCase4Graph = {
  1:[3, 4],
  2:[4],
  3:[1, 2],
  4:[1],
}

testCase5Graph = {
  1:[3],
  2:[4],
  3:[1, 2],
  4:[1],
}

visited = []
print("Test Case 1 Hamiltonian cycle:", hamiltonianCycle(testCase1Graph, len(testCase1Graph), 1))

visited = []
print("Test Case 2 Hamiltonian cycle:", hamiltonianCycle(testCase2Graph, len(testCase2Graph), 4))

visited = []
print("Test Case 3 Hamiltonian cycle:", hamiltonianCycle(testCase3Graph, len(testCase3Graph), 2))

visited = []
print("Test Case 4 Hamiltonian cycle:", hamiltonianCycle(testCase4Graph, len(testCase4Graph), 2))

visited = []
print("Test Case 5 Hamiltonian cycle:", hamiltonianCycle(testCase5Graph, len(testCase5Graph), 3))
