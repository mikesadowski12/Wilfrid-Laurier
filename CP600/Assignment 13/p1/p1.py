# def ConstructCandidates(A, k, S):
#   result = [] # empty array, will contain the column indexes where the k-th queen has no threat

#   for i in range(0, len(A)): # try each column i in row k
#     hasThreat = False

#     for j in range(0, k - 1): # check all the previously placed queen in rows 1 to k-1
#       if i == A[i] or abs(k - j) == abs(i - A[j]): 	# if queen ir row j has same column as i or, queens are in the same diagonal means delta row (i.e., abs(k-j)) == delta column (i.e., abs(i-A[j])
#         hasThreat = True # queen [k, i] and [j, A[j]] are in same column or diagonal

#     if not hasThreat:
#       result.append(i)

#   return result


# def Backtrack(A, k, S):
#   if IsSolution(A, k, S):
#     Process(A, k, S)

#   else:
#     L = ConstructCandidates(A, k, S)

#     for c in L:
#       A[k + 1] = c
#       Backtrack(A, k + 1, S)

#     if IsFinished():
#       return

# print ("Enter the number of queens")
# N = int(input())

count = 0
numberOfQueens = 8
board = [[0] * numberOfQueens for _ in range(numberOfQueens)]

def IsSolution(A, k, S):
  return k == len(A)	# each queen is placed without conflict and we do not use array index 0

def ConstructCandidates(mat, r, c):
  # return false if two queens share the same column
  for i in range(r):
    if mat[i][c] == 1:
      return False

  # return false if two queens share the same `` diagonal
  (i, j) = (r, c)
  while i >= 0 and j >= 0:
    if mat[i][j] == 1:
      return False
    i = i - 1
    j = j - 1

  # return false if two queens share the same `/` diagonal
  (i, j) = (r, c)
  while i >= 0 and j < numberOfQueens:
    if mat[i][j] == 1:
      return False
    i = i - 1
    j = j + 1

  return True

def Process(A, k, S):
  global count
  count += 1	# Count the number of solution in a global variable count. used by IsFinished callback

def IsFinished():
  # return count > 0		# Assuming we want to find any one solution
  return False

def Backtrack(A, k, S):
  if IsSolution(A, k, S):
    Process(A, k, S)
    return

  else:
    for i in range(numberOfQueens):
      if ConstructCandidates(A, k, i):
        A[k][i] = 1 # place queen
        Backtrack(A, k + 1, S) # try the queen for the next row
        A[k][i] = 0 # backtrack and remove the queen

      if IsFinished():
        return

Backtrack(board, 0, 0)
print("Solutions:", count)
