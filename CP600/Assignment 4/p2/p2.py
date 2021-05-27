import random
import sys

# sys.setrecursionlimit(10**6)

# recursionCounter = 0
# swapCounter = 0

# Taken from Assignment 3 Problem 1
def swapNodes(list, pos1, pos2):
  # global swapCounter
  # swapCounter += 1

  temp = list[pos1]
  list[pos1] = list[pos2]
  list[pos2] = temp

# Taken from Assignment 3 Problem 1
def Partition(A, lo, hi): # Partition A[lo..hi] using A[hi] as pivot
  x = A[hi] # x = A[hi] is the pivot element
  i = (lo - 1) # A[lo .. i] is the ≤ x

  # A[i+1 .. j-1] are greater than x
  for j in range(lo, hi):
    if A[j] <= x: # compare current element A[j] with x
      i = i + 1 # if A[j] ≤ x, then left array is increased
      swapNodes(A, i, j) # > x array is shifted one place and A[j] is added to ≤x array

  # Exchange A[i+1] ← A[hi]
  swapNodes(A, i + 1, hi)

  # return i + 1
  return i + 1

# Taken from Assignment 3 Problem 1
def QuickSort(A, lo, hi):
  # global recursionCounter
  # recursionCounter += 1

  if len(A) <= 1:
    return A

  # if lo < hi: there are more than one elements
  if lo < hi:
    q = Partition(A, lo, hi) # Partition A into ≤x and >x sub-arrays

    QuickSort(A, q + 1, hi) # Recurs on right array, i.e., > x array
    QuickSort(A, lo, q - 1) # Recurs on left array, i.e. ≤ x array

def InsertionSort(A):
  n = len(A) # length of input array A

  for j in range(1, n):
    key = A[j]

    i = j - 1

    while i >= 0 and A[i] > key:
      A[i + 1] = A[i]
      i -= 1

    A[i + 1] = key

def QuickInsertionSort(A, n, k):
  if n > k:
    QuickSort(A, 0, len(A) - 1)

  InsertionSort(A)

# k = 10
# inputList = [random.randint(1, k) for _ in range(10000)]
# QuickInsertionSort(inputList, len(inputList), k)
# print("Recursion count is ", recursionCounter)
# print("Swap count is ", swapCounter)

testCase1 = [104, 226, 3, 7, 69, 77, 144, 15, 29, 30, 31]
print("testCase1 Input:", testCase1)
QuickInsertionSort(testCase1, len(testCase1), 144)
print("testCase1 Output:", testCase1)
print("----------------------------------------")
testCase2 = [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]
print("testCase2 Input:", testCase2)
QuickInsertionSort(testCase2, len(testCase2), 1)
print("testCase2 Output:", testCase2)
print("----------------------------------------")
testCase3 = []
print("testCase3 Input:", testCase3)
QuickInsertionSort(testCase3, len(testCase3), 0)
print("testCase3 Output:", testCase3)
print("----------------------------------------")
testCase4 = [1, 3, -2]
print("testCase4 Input:", testCase4)
QuickInsertionSort(testCase4, len(testCase4), 3)
print("testCase4 Output:", testCase4)
print("----------------------------------------")
testCase5 = [1]
print("testCase5 Input:", testCase5)
QuickInsertionSort(testCase5, len(testCase5), 1)
print("testCase5 Output:", testCase5)
