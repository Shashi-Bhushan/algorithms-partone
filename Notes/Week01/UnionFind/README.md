# Week 1 notes

in Part 1, the focus is to answer whether there is a path that connects two objects.

The focus is not on finding the path between two objects i.e. algorithms like Dikshtra's algorithm.

## Union find: the dynamic connectivity problem

Assume a set of _N_ objects.

1. __Union command:__ connect two objects
2. __Find/connected query:__ is there a path connecting the two objects?

The solutions efficiently supports these two operations.


### Modeling the connections

We assume that the connection between two components _p_ and _q_ is and equivalence relation. It means
* Reflexive: _p_ is connected to _p_
* Symmetric: if _p_ is connected to _q_, then _q_ is connected to _p_
* Transitive: if _p_ is connected to _q_ and _q_ is connected to _r_, then _p_ is connected to _r_

Connected components are the maximal __set__ of objects that are mutually connected

_e.g._ { 0 } { 1 4 5 } { 2 3 6 7 }


### Implementing the operations

* __Find query:__ Check if two objects are in the same component
* __Union command:__ replace components containing two objects with their union

_e.g._

{ 0 } { 1 4 5 } { 2 3 6 7 }

union(2, 5)

{ 0 } { 1 2 3 4 5 6 7 }


### Union-find data type

Design efficient data structure for union-find

* Number of objects _N_ can be huge
* Number of operations _M_ can be huge
* Find queries and union commands may be intermixed


## Quick find (eager approach)

Data structure:

* Integer array id[] of size N
* Interpretation: _p_ and _q_ are connected iff (if and only if) they have the same id

__Find:__ check to see if _p_ and _q_ have the same id

__Union:__ set the value of first term's id to the second term's id


### Quick find is too slow

Cost model: Number of array access (for read or write)

|algorithm |initialize|union|find|
|---------:|:--------:|:---:|:--:|
|quick-find|         N|    N|   1|

__Defect:__ Union command is too slow

It will take quadratic (N<sup>2</sup>) time, which is unacceptable.

### Quadratic algorithms do not scale

Rough standard

* 10<sup>9</sup> operations per second
* 10<sup>9</sup> words of main memory
* Touch all words in approximately 1 second

E.g. huge problem for quick-find

* 10<sup>9</sup> union commands on 10<sup>9</sup> objects
* Quick-find takes more than 10<sup>18</sup> operations
* 30+ years of computer time!


## Quick union

Same data structure as quick find. But the array represents a set of trees (called a "forest"). The root of _i_ is id[id[id[…id[i]…]]].

__Find:__ Check whether _p_ and _q_ have the same root

__Union:__ To merge components containing _p_ and _q_, set the id of _p_'s root to the id of _q_'s root.

### Quick union is too slow

__Cost model__

| algorithm |initialize| union |find|
|----------:|:--------:|:-----:|:--:|
| quick-find|         N|      N|   1|
|quick-union|         N|N&uarr;|   N|

__Quick-find defect__

* Union too expensive (_N_ array accesses)
* Trees are flat, but too expensive to keep them flat

__Quick-union defect__

* Trees can get tall
* Find is too expensive (could be _N_ array accesses)


## Weighted Quick-union

__Weighting:__ as you create trees prevent them from getting too tall.

Keep track of number of objects, balance by connecting smaller tree to larger tree.


### Java implementation

* The constructor is the same
* The find query is the same
* We modify the union query to:
	* Link the root of the smaller tree to the larger tree
	* Update the sz[] array


### Running time

* __Find:__ takes time proportional to depth of _p_ and _q_
* __Union:__ takes constant time, given roots

__Proposition:__
Depth of any node _x_ is at most lg _N_.

__Proof:__
When does depth of _x_ increase?

Increase by 1 when tree _T<sub>1</sub>_ containing _x_ is merged into another tree _T<sub>2</sub>_.
* The size of the tree containing _x_ at least doubles since |_T<sub>2</sub>_| &ge; |_T<sub>1</sub>_|

| algorithm |initialize|   union  |find|
|----------:|:--------:|:--------:|:--:|
| quick-find|         N|         N|   1|
|quick-union|         N|   N&uarr;|   N|
|weighted qu|         N|lg N&uarr;|lg N|


## Adding path compression

__Two-pass implementation:__ add second loop to root() to set the id[] of each examined node to the root.

__Simpler one-pass variant:__ Make every other node in path point to its grandparent (thereby halving path length)

Proven to be nearly time-cost linear.


## Applications

* Percolation
* Dynamic connectivity
* Games (Go, Hex)
* Many others…


### Percolation

* _N_-by-_N_ grid of sites
* Each site is open with probability _p_ (or blocked with probability 1-_p_)
* System percolates iff top and bottom are connected by open sites

E.g.

* Electrons
* Water
* People in a social network




