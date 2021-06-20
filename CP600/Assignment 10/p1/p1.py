def longestPalindrome(s):
  if not s:
    return 0

  n = len(s)
  matrix = [[0] * n for _ in range(n)]

  # Initialize diagonal of matrix to 1's (each letters own start position to its self ending position)
  # Length of palindrome is 1 since string is 1 character long
  for i in range(n):
    matrix[i][i] = 1

  for i in range(2, n + 1):
    for j in range(n - i + 1):
      k = j + i - 1

      if s[j] == s[k]:
        matrix[j][k] = matrix[j + 1][k - 1] + 2

      else:
        matrix[j][k] = max(matrix[j + 1][k - 1], matrix[j + 1][k], matrix[j][k - 1])

  return matrix[0][n - 1]

print("Longest palindrome is: ", longestPalindrome("character"))
