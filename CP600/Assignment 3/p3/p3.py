def swapNodes(list, pos1, pos2):
  temp = list[pos1]
  list[pos1] = list[pos2]
  list[pos2] = temp

def Max_Heapify(list, length, pos):
  # The algorithm first finds the indices of the left child (l = 2*i) and the right child (r = 2*i+1)
  leftChild = 2 * pos + 1
  rightChild = 2 * pos + 2
  largestChild = pos

  # Then it finds the index (largest) of the maximum of 3 elements: A[i], A[Left[i]] and A[Right[i]]
  if leftChild < length and list[leftChild] > list[largestChild]:
    largestChild = leftChild

  if rightChild < length and list[rightChild] > list[largestChild]:
    largestChild = rightChild

  if largestChild != pos:
    # otherwise, if the left child is larger we swap A[i] with A[l]
    swapNodes(list, pos, largestChild)
    Max_Heapify(list, length, largestChild)

  # if A[i], i.e., parent, is the largest, we are done

def Build_Max_Heap(list, length):
  # for i = ⌊n/2⌋  down to 1:
  for i in range(int(length / 2) - 1, -1, -1): # range(start, stop, step)
    Max_Heapify(list, length, i)

# =================================================================================================
count = 0
list = []
k = int(input("Enter integer for k: "))

while 1:
  value = int(input("Enter an integer: "))

  # append to list for first k elements
  if (count < k - 1):
    list.append(value)
  else:
    if count == k - 1: # found the k-th value, build a heap
      list.append(value)
      Build_Max_Heap(list, len(list))
    else:
      if (value < list[0]): # check if current k-th value is smaller than current value
        # replace root if necessary and re-heapify
        list[0] = value
        Build_Max_Heap(list, len(list))

    print("list is ", list)
    print("k-th smallest value is ", list[0])

  count += 1
