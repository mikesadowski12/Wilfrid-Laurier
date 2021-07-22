QUEEN = 1
EMPTY = 0

count = 0
numberOfQueens = 8
board = [[0] * numberOfQueens for _ in range(numberOfQueens)]

def IsSolution(A, k, S):
  return k == len(A)	# each queen is placed without conflict and we do not use array index 0

def ConstructCandidates(A, k, S):
  for i in range(k): # try each column i in row k
    if A[i][S] == QUEEN:
      return False # can't place queen as it is attacked vertically


  # check all the previously placed queen in rows 1 to k-1

  i = k
  j = S
  while i >= 0 and j >= 0:
    if A[i][j] == QUEEN:
      return False # can't place queen as it is attacked diagonally

    i = i - 1
    j = j - 1

  i = k
  j = S
  while i >= 0 and j < numberOfQueens:
    if A[i][j] == QUEEN:
      return False # can't place queen as it is attacked diagonally

    i = i - 1
    j = j + 1

  return True # can place queen in the current position as it is not attack by any of queen

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
      L = ConstructCandidates(A, k, i)

      if L:
        A[k][i] = QUEEN # place queen
        Backtrack(A, k + 1, S) # try queen for the next row
        A[k][i] = EMPTY # backtrack and remove the queen

      if IsFinished():
        return

Backtrack(board, 0, 0)
print("Solutions:", count)
