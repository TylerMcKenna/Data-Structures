import numpy as np
from matplotlib import pyplot as plt

path = 'data.txt'

with open(path, 'rb') as fl:
    text = fl.read().decode("utf16")
text = text.replace('(', '')
text = text.replace(')', '')
matrix = [[int(i) for i in line.split(',')] for line in text.split('\n')[:-1]]
matrix = np.array(matrix)
plt.title('Comparision of Sorting Algorithms')
plt.xlabel('Size of the array divided by a thousand $(n/1000)$')
plt.ylabel('Milliseconds')
#plt.plot(matrix[0][0::2], matrix[0][1::2], label='Insertion')
#plt.plot(matrix[1][0::2], matrix[1][1::2], label='Merge')
plt.plot(matrix[2][0::2], matrix[2][1::2], label='Quick')
plt.plot(matrix[3][0::2], matrix[3][1::2], label='Loopy Quick')
#plt.plot(matrix[4][0::2], matrix[4][1::2], label='Counting')
#plt.plot(matrix[5][0::2], matrix[5][1::2], label='Arrays.sort(...)')
plt.legend()
plt.show()
