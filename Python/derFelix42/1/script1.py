f = open("input.txt", "r")

last_num = 0
cnt = 0

for x in f:
  num = int(x)
  if last_num != 0:
      if num > last_num:
          print(num, "(increased)")
          cnt += 1
      else:
          print(num, "(decreased)")
  last_num = num

print("Counted:", cnt)
