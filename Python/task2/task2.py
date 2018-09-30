"""Assignment 2
TASK2
Maciej Kubiniec R00144142"""

""" function checks if number given by user is odd or even.
if number is even then is devided by 2.
if number is odd then is multiplay by 3 and 1 is added"""
def reducer(number):
    if number % 2 == 0:
        print("(",number ,"/ 2 )")
        print()
        return int(number / 2)
        
    else:
        print("(",number ,"* 3 + 1 )")
        print()
        return  number * 3 + 1
"""main function that takes number as parameter and until
number will reach 1 will be devided or multiple
depending on its value equal to even or odd number"""
def main():
    number = 0
    print("Please enter a number")         
    number = int(input())
    if number % 2 == 0:
        print("you enetered an even number so first operation is division" )
    else:
        print("you enetered an odd number so first operation is multiplication" )
        
    while number != 1:
        print(number)
        number = reducer(number)
    print(number)
main()

### CORRECT SOLUTION BUT OUTPUTTING INSIDE THE REDUCER FUNCTION IS WRONG. THOSE CODE SHOULD HAVE BEEN REMOVED.

### SCORE = 6.5 MARKS
