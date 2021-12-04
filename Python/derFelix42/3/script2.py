#f = open("test.txt", "r")
f = open("input.txt", "r")

def mostCommonBit(array, pos):
    one = 0
    zero = 0
    for l in array:
        char = l[pos]
        if char == 1:
            one += 1
        else:
            zero += 1
    if one == zero:
        return 1
    return 1 if one > zero else 0

def filterLines(array, inverse=False):
    newArray = array[:]
    print(newArray)
    print()
    for i in range(len(newArray[0])):
        x = mostCommonBit(newArray, i)
        print("-> pos:",i,"bit:",x)
        asdf = []
        for line in newArray:
            print("  ->",line[i], line[i] == x)
            if inverse:
                if line[i] != x:
                    asdf.append(line)
            else:
                if line[i] == x:
                    asdf.append(line)
        newArray = asdf
        print(newArray)
        if len(newArray) == 1:
            break
        print()
    return newArray

lines = []

for l in f:
    line = list(l.split()[0])
    x = []
    for c in line:
        x.append(int(c))
    lines.append(x)

A = filterLines(lines)
B = filterLines(lines, True)



print()

a = int("".join([str(int) for int in A[0]]),2)
b = int("".join([str(int) for int in B[0]]),2)

print(a,b,a*b)
