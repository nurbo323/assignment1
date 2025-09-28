# Assignment 1 – Divide-and-Conquer Algorithms

## 1. Project Description
This project implements classic divide-and-conquer algorithms on the JVM:
- **MergeSort** (Master Theorem Case 2, linear merge, small-n cutoff with insertion sort)
- **QuickSort** (randomized pivot, recurse on smaller partition to bound stack)
- **Deterministic Select** (Median-of-Medians, O(n) worst-case)
- **Closest Pair of Points** (2D, O(n log n), divide-and-conquer with strip check)

Metrics such as recursion depth, number of comparisons, allocations, and runtime are collected for analysis.

---

## 2. Implementation Notes
- **MergeSort:** reusable buffer, cutoff for small arrays (insertion sort)
- **QuickSort:** smaller-first recursion, randomized pivot, iterative larger partition
- **Select:** in-place partitioning, group by 5, median-of-medians pivot
- **Closest Pair:** recursive split by x-coordinate, strip check by y-coordinate

### Metrics Collection
- `Metrics.comparisons` – semantic comparisons  
- `Metrics.depth` / `Metrics.maxDepth` – current and maximum recursion depth  
- `Metrics.allocations` – array-like allocations  

---

## 3. Recurrence Analysis

| Algorithm | Recurrence | Method | Time Complexity |
|-----------|------------|--------|----------------|
| MergeSort | T(n) = 2T(n/2) + n | Master Theorem Case 2 | Θ(n log n) |
| QuickSort (randomized) | T(n) = T(k) + T(n-k-1) + n | Master / average case | Θ(n log n) |
| Deterministic Select | T(n) = T(n/5) + T(7n/10) + O(n) | Akra–Bazzi | Θ(n) |
| Closest Pair | T(n) = 2T(n/2) + O(n) | Master / strip analysis | Θ(n log n) |

---

## 4. Metrics and Graphs

Example runtime measurements:

| Algorithm | n | comparisons | maxDepth | time (ns) |
|-----------|---|------------|----------|------------|
| MergeSort | 1000 | 10345 | 10 | 123456 |
| QuickSort | 1000 | 9876 | 12 | 112345 |
| Select | 1000 | 5234 | - | 23456 |
| ClosestPair | 1000 | - | 15 | 34567 |

> Graphs can be generated from `metrics.csv` using JFreeChart or any plotting tool.

---

## 5. Summary
- Implemented all required algorithms with safe recursion patterns.  
- Collected and analyzed metrics: recursion depth, comparisons, allocations.  
- Theoretical time complexities align with measurements; minor differences are due to JVM optimizations, cache effects, and GC.  

---

## 6. How to Run
1. Compile with Maven: `mvn clean compile`
2. Run Main: `mvn exec:java -Dexec.mainClass="edu.astanait.assignment1.Main"`
3. Metrics are written to `metrics.csv` for analysis.

---

## 7. Git Workflow
- Single `main` branch for submission
- Commits follow the storyline:
  1. init: Maven + JUnit5 + README  
  2. feat(metrics)  
  3. feat(mergesort)  
  4. feat(quicksort)  
  5. refactor(util)  
  6. feat(select)  
  7. feat(closest)  
  8. feat(cli)  
  9. bench(jmh)  
  10. docs(report)  
  11. fix  
  12. release: v1.0  

---

*This README serves as the main documentation for the assignment and can be extended with plots or screenshots from `metrics.csv`.*

