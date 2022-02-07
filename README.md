# sudoku-verifier

A mini project to verify a sudoku grid.

![Screenshot of the app's front-end](./docs/images/2022-02-07_screenshot.png)

## The Challenge

Given a valid grid and an invalid grid correctly identify the invalid grid by checking rows, columns and blocks.

A valid row, column and block contains 9 values - each of the values between 1-9 (inclusive) without duplication.

### Valid Sudoku Grid

|     |     |     |     |     |     |     |     |     |
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
|  2  |  4  |  1  |  6  |  9  |  5  |  3  |  8  |  7  |
|  7  |  3  |  5  |  4  |  2  |  8  |  1  |  6  |  9  |
|  8  |  6  |  9  |  7  |  3  |  1  |  4  |  2  |  5  |
|  4  |  1  |  3  |  8  |  7  |  9  |  2  |  5  |  6  |
|  6  |  9  |  2  |  5  |  1  |  3  |  7  |  4  |  8  |
|  5  |  8  |  7  |  2  |  4  |  6  |  9  |  3  |  1  |
|  1  |  7  |  8  |  3  |  6  |  4  |  5  |  9  |  2  |
|  9  |  5  |  4  |  1  |  8  |  2  |  6  |  7  |  3  |
|  3  |  2  |  6  |  9  |  5  |  7  |  8  |  1  |  4  |

### Invalid Sudoku Grid

|       |     |     |     |     |     |     |     |     |
|:-----:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
|   2   |  4  |  1  |  6  |  9  |  5  |  3  |  8  |  1  |
|   7   |  3  |  5  |  4  |  2  |  8  |  1  |  6  |  9  |
|   8   |  6  |  9  |  7  |  3  |  1  |  4  |  2  |  5  |
|   4   |  1  |  3  |  8  |  7  |  9  |  2  |  5  |  6  |
|   6   |  9  |  2  |  5  |  2  |  3  |  7  |  4  |  8  |
|   5   |  8  |  7  |  2  |  4  |  6  |  9  |  3  |  1  |
|   1   |  7  |  8  |  3  |  6  |  4  |  5  |  9  |  2  |
| **8** |  5  |  4  |  1  |  8  |  2  |  6  |  7  |  3  |
|   3   |  2  |  6  |  9  |  5  |  7  |  8  |  1  |  4  |


## Personal Challenges

### Streams API

I wanted to use the Java 8 functional streams API, and in particular to minimise declared loops.

I found this challenging as the grid is provided as an array, with the blocks being subgrids of the first.

As a compromise to myself I used parallel iterators to force non-sequential code.
Although unlikely to be faster than the traditional for loop method, readability of the declarative code is better.


### End-to-End testing

I wanted to write end-to-end tests with a limited public API to verify the app works as intended,
rather than unit testing individual methods.  This was in part because the initial challenge was
to write a single output to the console stating whether the grid was valid, which means I'd need
to create test grids for isolated tests.

- end-to-end test grids tailored to test individual private methods.

## Furthering the project

### Servlet

Originally the verifier used the console as a means of communication, but I wrote a little servlet to send and receive grids.
This also allowed more practice at the following within Java:
- validating responses
- regular expressions
- string manipulation
- functional streams and lambdas

### Front End

It also provided an opportunity to exhibit:
- some creative use of forms
- css stying

## Future

### TODO

- [ ] Java generated html  
- [ ] bookmarkable urls to store state  
- [ ] better response page  
- [ ] testing server with test http requests  
- [ ] refactor for hidden grid implementation  
- [ ] dockerfile for easy deployment  