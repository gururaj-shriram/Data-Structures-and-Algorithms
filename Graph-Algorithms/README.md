# Breadth First Search

``` BreathFirstSearch.java```  performs the BFS algorithm on an adjacency list of integer nodes (supplied from a text file) and calculates the mean path length of the graph

```scaleFree.py``` and ```randomGraph.py``` create their respective adjacency list of nodes

## Execution
To create the adjacency lists:
``` python (randomGraph, scaleFree).py N```
where ```(randomGraph, scaleFree)``` denotes either the randomGraph script or the scaleFree script
and N denotes the number of nodes in the graph

The python scripts create files entitled ```(scaleFree,randomGraph)_N.net``` which are used to perform BFS

### Example
```python randomGraph.py 10``` creates a file called ```randomGraph_10.net``` which is used in running ``` BreathFirstSearch.java``` 
