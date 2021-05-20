def swapNodes(list, pos1, pos2):
  temp = list[pos1]
  list[pos1] = list[pos2]
  list[pos2] = temp

def Min_Heapify(list, length, pos):
  # The algorithm first finds the indices of the left child (l = 2*i) and the right child (r = 2*i+1)
  leftChild = 2 * pos + 1
  rightChild = 2 * pos + 2
  smallestChild = pos

  # Then it finds the index (smallest) of the maximum of 3 elements: A[i], A[Left[i]] and A[Right[i]]
  if leftChild < length and list[leftChild] < list[smallestChild]:
    smallestChild = leftChild

  if rightChild < length and list[rightChild] < list[smallestChild]:
    smallestChild = rightChild

  if smallestChild != pos:
    # otherwise, if the left child is larger we swap A[i] with A[l]
    swapNodes(list, pos, smallestChild)
    Min_Heapify(list, length, smallestChild)

  # if A[i], i.e., parent, is the smallest, we are done

def Build_Min_Heap(list, length):
  # for i = ⌊n/2⌋  down to 1:
  for i in range(int(length / 2) - 1, -1, -1): # range(start, stop, step)
    Min_Heapify(list, length, i)

def HeapSort(list):
  length = len(inputList)

  Build_Min_Heap(list, length)

  for i in range(length - 1, -1, -1): # range(start, stop, step)
    swapNodes(list, 0, i) # Place the smallest element in the Heap to the end
    Min_Heapify(list, i, 0) # Restore heap at A[1] of size n-1

inputList = [104, 226, 3, 7, 69, 77, 144, 15, 29, 30, 31]

print("Input:", inputList)
HeapSort(inputList)
print("Output:", inputList)
