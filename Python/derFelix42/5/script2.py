#f = open("test.txt", "r")
f = open("input.txt", "r")

vents = []

maxX = 0
maxY = 0

for l in f:
    a,b = l.strip().split(" -> ")
    x1,y1 = a.split(",")
    x2,y2 = b.split(",")
    z = ((int(x1),int(y1)),(int(x2),int(y2)))
    vents.append(z)

    if int(x1) > maxX:
        maxX = int(x1)
    if int(x2) > maxX:
        maxX = int(x2)
    if int(y1) > maxY:
        maxY = int(y1)
    if int(y2) > maxY:
        maxY = int(y2)

    #print(z, maxX, maxY)

map = [[0 for x in range(maxX+1)] for x in range(maxY+1)]

def printMap(map):
    for row in map:
        for cell in row:
            if cell > 0:
                print(cell, end="")
            else:
                print(".", end ="")
        print()

#printMap(map)

for vent in vents:
    a,b = vent
    x1,y1 = a
    x2,y2 = b

    if x1 == x2:
        print(vent)
        if y1 > y2:
            y = y2
            y2 = y1
            y1 = y
        for i in range(y1, y2+1):
            print("  ->",(x1,i))
            map[i][x1] = map[i][x1] + 1
        #printMap(map)
    elif y1 == y2:
        print(vent)
        if x1 > x2:
            x = x2
            x2 = x1
            x1 = x
        for i in range(x1, x2+1):
            print("  ->",(i,y1))
            map[y1][i] = map[y1][i] + 1
        #printMap(map)
    else:
        print(vent)
        if x1 > x2 and y1 > y2:
            for i in range(x1-x2+1):
                x = x1-i
                y = y1-i
                print("  ->",(x,y))
                map[y][x] = map[y][x] + 1
        if x1 < x2 and y1 > y2:
            for i in range(x2-x1+1):
                x = x1+i
                y = y1-i
                print("  ->",(x,y))
                map[y][x] = map[y][x] + 1

        if x1 > x2 and y1 < y2:
            for i in range(x1-x2+1):
                x = x1-i
                y = y1+i
                print("  ->",(x,y))
                map[y][x] = map[y][x] + 1
        if x1 < x2 and y1 < y2:
            for i in range(x2-x1+1):
                x = x1+i
                y = y1+i
                print("  ->",(x,y))
                map[y][x] = map[y][x] + 1
        #printMap(map)


count = 0
for i in range(len(map)):
    for j in range(len(map[i])):
        if map[i][j] >= 2:
            count += 1

#printMap(map)

print(">>",count,"<<")
