#f = open("test.txt", "r")
f = open("input.txt", "r")

nums = []

boards = []

currentBoard = []

def newNumberForBoard(board, num):
    for i in range(len(board)):
        line = board[i]
        for j in range(len(line)):
            entry = line[j]
            #print(entry)
            if entry == num:
                #print("same number found")
                board[i][j] = -1

def checkBoard(board): # returns true if won
    for i in range(len(board)):
        line = board[i]
        lineGood = True
        for x in line:
            if x != -1:
                lineGood = False
                break
        if lineGood:
            return True

    for j in range(len(board[0])):
        columnGood = True
        for i in range(len(board)):
            if board[i][j] != -1:
                columnGood = False
                break
        if columnGood:
            return True

    return False

def sumOfBoard(board):
    sum = 0
    for lines in board:
        for num in lines:
            if num != -1:
                sum += num
    return sum

# Go through Boards
for l in f:
    l = l.strip()
    if l == "":
        if len(currentBoard) > 0:
            boards.append(currentBoard)
            currentBoard = []
    elif "," in l:
        nums = l.split(",")
        nums = [int(x) for x in nums if x]
    else:
        line = l.split(" ")
        line = [x for x in line if x != ""]
        line = [int(x) for x in line if x]
        currentBoard.append(line)
        print(line)

if len(currentBoard) > 0:
    boards.append(currentBoard)

for board in boards:
    for lines in board:
        print(" ".join(str(lines)))
    print()

# find first winning board
for num in nums:
    for board in boards:
        newNumberForBoard(board, num)
        if checkBoard(board):
            print("Board won!",board)
            sum = sumOfBoard(board)
            print(sum, num, sum*num)
            exit()
 
# print(nums)
# print(boards)
