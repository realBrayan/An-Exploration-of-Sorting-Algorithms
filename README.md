# An Exploration of Sorting Algorithms

This project compares sorting algorithm behavior in Java using the `Sorting` program.

The program:
- asks you to choose one algorithm,
- generates four input distributions of the same size,
- sorts each dataset,
- verifies correctness, and
- prints execution time for each run.

## File Overview

- `Sorting.java`: Main program containing data generation, sorting implementations, timing, and correctness checks.

## Implemented Algorithms

The program supports these options at runtime:

- `I` -> Insertion Sort
- `M` -> Merge Sort
- `Q` -> Quick Sort (with performance improvements)
- `S` -> Java library sort (`Arrays.sort`)

### Notes on the Quick Sort implementation

The Quick Sort in this code is not a basic textbook version. It includes practical improvements:
- random pivot selection in `Partition(...)` to reduce bad pivot patterns,
- insertion-sort fallback for small subarrays (`< 20` elements),
- depth monitoring with a fallback to Merge Sort when recursion exceeds an ideal threshold.

This makes the behavior closer to an introspective strategy and helps avoid deep-recursion failures.

## Input Data Patterns Tested

For every selected algorithm, the program runs on four datasets:

1. Sorted data
2. Nearly sorted data
3. Reversely sorted data
4. Random data

Each dataset uses the same `size` you provide, so the runs are directly comparable.

## How to Run

## Prerequisites

- Java JDK 8+ installed
- Terminal in this project folder

## Compile

```bash
javac Sorting.java
```

## Run

```bash
java Sorting
```

You will be prompted to enter:

1. Sorting algorithm code (`I`, `M`, `Q`, or `S`)
2. Input size (for example `10000`)

Then the program prints timing results (in milliseconds) for all four data patterns and confirms whether sorting was correct.

## Findings (From the Current Exploration)

Based on the repository focus and the current implementation, the main findings are:

- Input distribution strongly affects algorithm performance, especially for simple strategies like Insertion Sort.
- Insertion Sort is practical for small or nearly sorted inputs but becomes expensive on large random/reverse data.
- Merge Sort provides consistent performance across input patterns because its complexity is reliably `O(n log n)`.
- Naive Quick Sort can run into deep recursion issues on bad pivots; this implementation addresses that with random pivots and a depth-based Merge Sort fallback.
- Hybridization improves robustness: combining Quick Sort with insertion-sort cutoffs and depth limits yields more stable behavior across adversarial inputs.
- Java's `Arrays.sort` provides a strong built-in baseline and is useful as a reference point for custom implementations.

## Output Interpretation

For each dataset, look for:

- `Correctly sorted ... elements in ... ms` -> success and timing
- `ERROR!: INCORRECT SORTING!` -> sorting bug detected

When using `Q`, the program also prints:
- max recursion depth reached,
- ideal recursion depth threshold.

These values help explain when Quick Sort had to rely more on fallback behavior.
