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
# Explanation:
#   1. Loop until k numbers are inputted from the stream, store all temperatures in an array
#   2. When k temperatures have been inputted, store the final temperature and transform input array
#      into a max heap
#   3. As more temperatures are inputted, compare the root (which will be the k-th smallest temperature seen so far)
#      to the current inputted temperature. If the current temperature is smaller, replace root with the current
#      temperature and heapify the array to get the largest temperature to the root (k-th smallest temperature)
# =================================================================================================
count = 0
list = []
k = int(input("Enter integer for k: "))

while 1:
  temperature = int(input("Enter a temperature: "))

  # append to list for first k temperatures
  if (count < k - 1):
    list.append(temperature)
  else:
    if count == k - 1: # found the k-th temperature, build a heap
      list.append(temperature)
      Build_Max_Heap(list, len(list))
    else:
      if (temperature < list[0]): # check if current k-th temperature is smaller than current temperature
        # replace root if necessary and re-heapify
        list[0] = temperature
        Build_Max_Heap(list, len(list))

    # k-th smallest temperature is always the root of the max-heap
    print("k-th smallest temperature is ", list[0])

  count += 1
