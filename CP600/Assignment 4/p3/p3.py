import math

def CountingSort(A, B, k, p):
  C = [0] * (k+1)

  # Compute the count of each element in A
  for j in range(0,len(A)):
    i = math.floor(A[j] / p) % 10
    C[i] += 1

  # Compute C[x] == count of A[j] ≤ x
  for i in range(1, k+1):
    C[i] += C[i - 1]

  # Place each A[j] in sorted order in B starting from the end
  for j in range(len(A)-1, -1, -1):
    i = math.floor(A[j] / p) % k
    B[C[i] - 1] = A[j]
    C[i] -= 1
    j -= 1

  for i in range(0,len(A)):
    A[i] = B[i]

def RadixSort(A, B):
  k = max(A)

  p = 1
  while math.floor(k / p) > 0:
    CountingSort(A, B, 10, p) # Sort A on position p using a sable sorting algorithm
    p = p * 10

inputList = [ 418, 336, 457, 419, 386, 312, 310, 452 ]
outputList = [0] * (len(inputList))

print("Input:", inputList)
RadixSort(inputList, outputList)
print("Output:", outputList)
