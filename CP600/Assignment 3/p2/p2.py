def Partition(A, lo, hi): # Partition A[lo..hi] using A[hi] as pivot
  x = A[hi] # x = A[hi] is the pivot element
  i = (lo - 1) # A[lo .. i] is the ≤ x

  # A[i+1 .. j-1] are greater than x
  for j in range(lo, hi):
    if A[j] <= x: # compare current element A[j] with x
      i = i + 1 # if A[j] ≤ x, then left array is increased
      A[i], A[j] = A[j], A[i] # > x array is shifted one place and A[j] is added to ≤x array

  # Exchange A[i+1] ← A[hi]
  temp = A[i + 1]
  A[i + 1] = A[hi]
  A[hi] = temp

  # return i + 1
  return i + 1


def QuickSort(A, lo, hi):
  if len(A) <= 1:
    return A

  # if lo < hi: there are more than one elements
  if lo < hi:
    q = Partition(A, lo, hi) # Partition A into ≤x and >x sub-arrays

    QuickSort(A, q + 1, hi) # Recurs on right array, i.e., > x array
    QuickSort(A, lo, q - 1) # Recurs on left array, i.e. ≤ x array

inputList = [104, 226, 3, 7, 69, 77, 144, 15, 29, 30, 31]

print("Input:", inputList)
QuickSort(inputList, 0, len(inputList) - 1)
print("Output:", inputList)

