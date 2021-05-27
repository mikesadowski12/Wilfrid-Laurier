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
  if len(A) == 0:
    return A

  k = max(A)

  p = 1
  while math.floor(k / p) > 0:
    CountingSort(A, B, 10, p) # Sort A on position p using a sable sorting algorithm
    p = p * 10

testCase1 = [ 418, 336, 457, 419, 386, 312, 310, 452 ]
outputList = [0] * (len(testCase1))
print("testCase1 Input:", testCase1)
RadixSort(testCase1, outputList)
print("testCase1 Output:", outputList)
print("----------------------------------------")
testCase2 = []
outputList = [0] * (len(testCase2))
print("testCase2 Input:", testCase2)
RadixSort(testCase2, outputList)
print("testCase2 Output:", outputList)
print("----------------------------------------")
testCase3 = [1]
outputList = [0] * (len(testCase3))
print("testCase3 Input:", testCase3)
RadixSort(testCase3, outputList)
print("testCase3 Output:", outputList)
print("----------------------------------------")
testCase4 = [1, 1, 1, 1]
outputList = [0] * (len(testCase4))
print("testCase4 Input:", testCase4)
RadixSort(testCase4, outputList)
print("testCase4 Output:", outputList)
print("----------------------------------------")
testCase5 = [133, 12, 1444, 1]
outputList = [0] * (len(testCase5))
print("testCase5 Input:", testCase5)
RadixSort(testCase5, outputList)
print("testCase5 Output:", outputList)
print("----------------------------------------")
