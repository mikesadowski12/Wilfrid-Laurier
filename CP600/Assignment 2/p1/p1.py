import math

def combineLeftAndRightList(unsortedList, leftList, rightList):
  mainPos = 0
  leftPos = 0
  rightPos = 0

  while leftPos < len(leftList) and rightPos < len(rightList):
    if leftList[leftPos] < rightList[rightPos]:
      unsortedList[mainPos] = leftList[leftPos]
      leftPos += 1
    elif leftList[leftPos] > rightList[rightPos]:
      unsortedList[mainPos] = rightList[rightPos]
      rightPos += 1
    mainPos += 1

  # Lists may have numbers left over as the might not be equal length. Append them as needed
  while leftPos < len(leftList):
    unsortedList[mainPos] = leftList[leftPos]
    mainPos += 1
    leftPos += 1

  while rightPos < len(rightList):
    unsortedList[mainPos] = rightList[rightPos]
    mainPos += 1
    rightPos += 1

def mergeSort(list):
  listLength = len(list)

  # Conquer: if n=1, i.e., P1 is already sorted. Otherwise conquer each Pn/2 recursively,
  # and find the sorted sub arrays P’n/2.
  if listLength <= 1:
    return

  midPoint = math.floor(listLength / 2)

  # Divide: divide the n-element array Pn into two smaller arrays Pn/2 of size n/2 each.
  leftHalf = list[:midPoint]  # a[start:] items start through the rest of the array
  rightHalf = list[midPoint:] # a[:stop] items from the beginning through stop-1

  # Then recursively call Merge sort on each subarray.
  mergeSort(leftHalf)
  mergeSort(rightHalf)

  # Combine: Once we have two sorted arrays of size P’n/2, merge them to get the sorted array P’n.
  combineLeftAndRightList(list, leftHalf, rightHalf)

# Input: the input problem instance Pn for Merge sort is an unsorted array of n numbers.
inputList = [104, 226, 3, 7, 69, 77, 144, 15, 29, 30, 31]

mergeSort(inputList)
print(inputList)
