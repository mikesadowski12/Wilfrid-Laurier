# Taken from Assignment 3 Problem 1
def swapNodes(list, pos1, pos2):
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

  if n <= k:
    InsertionSort(A)

inputList = [104, 226, 3, 7, 69, 77, 144, 15, 29, 30, 31]

print("Input:", inputList)
QuickInsertionSort(inputList, len(inputList) - 1, 100)
print("Output:", inputList)
