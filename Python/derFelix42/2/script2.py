#f = open("test.txt", "r")
f = open("input.txt", "r")

horizontal = 0
depth = 0
aim = 0

for l in f:
    x = l.split(" ")
    #print(x)
    if x[0] == "forward":
        horizontal += int(x[1])
        depth += aim * int(x[1])
    elif x[0] == "down":
        aim += int(x[1])
    elif x[0] == "up":
        aim -= int(x[1])
    #print(" -> ",horizontal, aim, depth)

print(horizontal, depth, horizontal * depth)
