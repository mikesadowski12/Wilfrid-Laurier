def hamiltonianCycle(Graph, nodeCount, currentNode, visited = []):
  if currentNode not in visited:
    visited.append(currentNode)

    visitedCount = len(visited)
    if visitedCount == nodeCount:
      return visited

    for nextNode in Graph[currentNode]:
      candidate = hamiltonianCycle(Graph, nodeCount, nextNode)

      if candidate is not None:
        return candidate

testCase1Graph = {
  1:[2, 3, 4],
  2:[1, 3, 4],
  3:[1, 2, 4],
  4:[1, 2, 3],
}

testCase2Graph = {
  1:[2],
  2:[3, 4],
  4:[3],
}

print("Test Case 1 Hamiltonian cycle:", hamiltonianCycle(testCase1Graph, len(testCase1Graph), 2))
print("Test Case 2 Hamiltonian cycle:", hamiltonianCycle(testCase2Graph, len(testCase2Graph), 2))
