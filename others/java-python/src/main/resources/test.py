import sys

def add(a, b):
    return a + b


if __name__ == '__main__':
    a = []

    for i in range(1, len(sys.argv)):
        print("print===>" + sys.argv[i])
        a.append((sys.argv[i]))