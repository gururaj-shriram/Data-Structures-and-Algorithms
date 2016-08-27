import sys, string, random

def edge(G, i, j):
    try:
        l = G[i]
    except KeyError:
        l = []
    if j not in l:
        l.append(j)
    G[i] = l
    return

N = string.atoi(sys.argv[1])
prob = 0.1

G = {}

for i in range(0, N-1):
    for j in range(i+1, N):
        if i < j:
            p = random.random()
            if p <= prob:
                edge(G, i, j)

out = open('./randomGraph_' + str(sys.argv[1]) +'.net', 'w') 
for i in G.keys():
    out.write(str(i)) 
    for j in G[i]:
        out.write(' ' + str(j))
    out.write('\n')
out.close()
