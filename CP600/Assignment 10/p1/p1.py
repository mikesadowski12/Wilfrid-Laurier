def longestPalindrome(s):
  if not s:
    return ''

  n = len(s)
  matrix = [['0'] * n for _ in range(n)]

  # Initialize diagonal of matrix to each letter (own start position to its self ending position)
  for i in range(n):
    matrix[i][i] = s[i]

  for i in range(2, n + 1):
    for j in range(n - i + 1):
      k = j + i - 1

      if s[j] == s[k]:
        matrix[j][k] = s[j] + matrix[j + 1][k - 1] + s[k]
      else:
        matrix[j][k] = max([matrix[j + 1][k - 1], matrix[j + 1][k], matrix[j][k - 1]], key = len)

  return matrix[0][n - 1]

print("longest palindrome in \'\':", longestPalindrome(""))
print("longest palindrome in \'bab\':", longestPalindrome("bab"))
print("longest palindrome in \'character\':", longestPalindrome("character"))
print("longest palindrome in \'baroihgfsdjnb\':", longestPalindrome("baroihgfsdjnb"))
print("longest palindrome in \'abcdefg\':", longestPalindrome("abcdefg"))
