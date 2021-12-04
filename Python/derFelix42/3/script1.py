#f = open("test.txt", "r")
f = open("input.txt", "r")

ones = []
zeros = []

for l in f:
    line = list(l.split()[0])
    print(line)
    if len(ones) < len(line):
        ones = [0] * len(line)
        zeros = [0] * len(line)
        print(ones,zeros)
    for i in range(len(line)):
        if line[i] == "1":
            ones[i] += 1
        else:
            zeros[i] += 1

print(ones)
print(zeros)

result_string1 = ""

for i in range(len(ones)):
    if ones[i] > zeros[i]:
        result_string1 += "1"
    else:
        result_string1 += "0"

result_string2 = ""

for i in range(len(ones)):
    if ones[i] < zeros[i]:
        result_string2 += "1"
    else:
        result_string2 += "0"

print(result_string2, result_string2, int(result_string1,2)*int(result_string2,2))
