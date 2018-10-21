# Package Challenge

## Approach

### 1. Strategy
Solving the problem in lower time and space complexity.


### 2. Algorithm
The package challenge is a problem in combinatorial optimization. The solution is based on the 0/1 [knapsack problem](https://en.wikipedia.org/wiki/Knapsack_problem) with some difference.  A scaled weight used in this solution because the weight of items is decimal. So, the weight of items multiplied by a factor like 1000 to remove (three) precision of weights. 

Dynamic Programming used in this solution for reducing the time and space complexity of the solution. For calculating find(n,w) we only need to compute find(n-1,w) and find(n-1, w-w(n)). So, the knapsackResult matrix has been used for storing the knapsackResult and does not need to compute them again in the recursion. The time and space complexity of the solution is O(nW).

Unlike traditional 0/1 knapsack problem which returns maximum value of backpack, KnapsackResult class has been used for storing indexes of items that build the solution.

### 3. Data Structure

2D array (matrix) used for implementing Knapsack 0/1 using dynamic programming. Also, other data class implemented in entity package.

### 4. Design Pattern
This program is base on SOLID principles and separation of concerns.

### 5. Best Practices
You can use various Knapsack algorithm in the Packer class by implementing the KnapsackStrategy interface in a customized class. 
If the input file format changes then you can implement appropriate file parser by implementing KnapsackFileParser interface without altering the semantics of the program.

## Build

    ./gradlew build
    
## RUN

    ./gradlew run --args="path/to/input/file"
    or
    java -jar build/libs/pcaker-1.0-SNAPSHOT.jar path/to/input/file

e.g.
    
    java -jar build/libs/pcaker-1.0-SNAPSHOT.jar sample.txt
    
## Test

    ./gradlew test        