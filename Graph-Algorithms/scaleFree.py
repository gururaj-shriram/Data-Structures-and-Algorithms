import sys, string#, random
import random

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

G = {}
edge(G, 0, 1)
edge(G, 0, 2)

nodes = [0, 0, 1, 2]
for i in range(3, N):
    r = random.randint(0, len(nodes)-1)
    edge(G, nodes[r], i)
    nodes.append(i)
    nodes.append(nodes[r])

out = open('./scalefree_' + str(sys.argv[1]) +'.net', 'w') 
for i in G.keys():
    out.write(str(i)) 
    for j in G[i]:
        out.write(' ' + str(j))
    out.write('\n')
out.close()
