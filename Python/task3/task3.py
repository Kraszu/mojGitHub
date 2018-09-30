"""Assignment 2
TASK3
Maciej Kubiniec R00144142"""
import random
""" function that takes number from user input and delates numbers
from list at random position""" 
def generator(userList, num):
    
    for i in range(num):
     
     del userList[random.randint(2,9)]  ### THE RANGE SHOULD BE 2 TO 5 AND NOT 2 TO 9
    return userList

def main():
    userList = [];

    print("Please enter 12 elements: ")
    print("(confirm each  with 'enter' key) ")
    for elementTaken in range(0, 12):      
            element = input();
            userList.append(element)
    print()
    #print("Original List: ", userList)
    print()
    print("Please enter number between 2 and 9 ")
    num = int(input())
   
         
         
    print(" Original List:", userList)
    userList = generator(userList, num)
    print(" Output tuple: ",userList)
    
main()

### THE SOLUTION LOGIC IS NOT CONSISTENT WITH REQUIREMENTS. THE RANDOM RANGE IS WRONG. THE RETURNED TUPLE SHOULD BE OUTPUTTED
### FIRST BEFORE THE ORIGINAL LIST THE WOULD SHOW THAT THE SOLUTION IS WRONG BECAUSE PASS BY REFERENCE WAS NOT HANDLED.

### SCORE = 6 MARKS
