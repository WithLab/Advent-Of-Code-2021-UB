# = open("test.txt", "r")
f = open("input.txt", "r")

horizontal = 0
depth = 0

for l in f:
    x = l.split(" ")
    if x[0] == "forward":
        horizontal += int(x[1])
    elif x[0] == "down":
        depth += int(x[1])
    elif x[0] == "up":
        depth -= int(x[1])

print(horizontal, depth, horizontal * depth)
