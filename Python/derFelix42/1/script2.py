f = open("input.txt", "r")

last_num1 = 0
last_num2 = 0
last_sum = 0
cnt = 0

for x in f:
  curr_num = int(x)
  if last_num1 != 0 and last_num2 != 0:
      curr_sum = curr_num + last_num1 + last_num2
      if last_sum != 0:
          if curr_sum > last_sum:
              print(curr_sum, "(increased)")
              cnt += 1
          else:
              print(curr_sum, "(decreased)")
      else:
          print(curr_sum, "N/A")
      last_sum = curr_sum

  last_num2 = last_num1
  last_num1 = curr_num

print("Counted:", cnt)
