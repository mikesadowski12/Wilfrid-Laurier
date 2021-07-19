count = 0

def IsSolution(A, k, S):
  return k > len(A)	# each queen is placed without conflict and we do not use array index 0

def ConstructCandidates(A, k, S):
  result = [] # empty array, will contain the column indexes where the k-th queen has no threat

  for i in range(1, len(A)): # try each column i in row k
    hasThreat = False

    for j in range(1, k - 1): # check all the previously placed queen in rows 1 to k-1
      if i == A[i] or abs(k - j) == abs(i - A[j]): 	# if queen ir row j has same column as i or, queens are in the same diagonal means delta row (i.e., abs(k-j)) == delta column (i.e., abs(i-A[j])
        hasThreat = True # queen [k, i] and [j, A[j]] are in same column or diagonal

    if not hasThreat:
      result.append(i)

  return result

def Process(A, k, S):
  global count
  count += 1	# Count the number of solution in a global variable count. used by IsFinished callback
  print(A)

def IsFinished():
  return count > 0		# Assuming we want to find any one solution

def Backtrack(A, k, S):
  if IsSolution(A, k, S):
    Process(A, k, S)

  else:
    L = ConstructCandidates(A, k, S)

    for c in L:
      A[k + 1] = c
      Backtrack(A, k + 1, S)

    if IsFinished():
      return

print ("Enter the number of queens")
N = int(input())

#chessboard
#NxN matrix with all elements 0
board = [[0] * N for _ in range(N)]

Backtrack(board, 0, 0)
