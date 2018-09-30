"""Assignment 2
TASK1
Maciej Kubiniec R00144142"""



""" Function to assign the highest number in the list.
Iterating through the numbers within the range and
if checking number is bigger then the one
currently assign as the maxNumber, then this number become a new max"""
def largestNumber(numbers):    
        maxNumber = numbers[0]
        for i in range(0, 12): ### WHY NOT USE THE len() FUNCTION TO DYNAMICALLY DETERMINE THE LENGTH OF THE LIST INSTEAD OF FIXING IT AT 12?
            if numbers[i] > maxNumber:
                maxNumber = numbers[i]
        return maxNumber
# function oposite to the one above              
def smallestNumber(numbers):
        minNumber = numbers[0]
        for i in range(0, 12):
            if numbers[i] < minNumber:
                minNumber = numbers[i]
        return minNumber
  


# Finding the odd numbers
def replicateList(numbers): ### REPLICATION COULD HAVE COMED FIRST BEFORE OUTPUTING THE ODD NUMBERS.
    print("Odd numbers in the list are: ")
    print()
    for i in range(0, 12):
        if numbers[i] % 2 == 1:
            odd = numbers[i]            
            print( odd)
    """replicate the list by 3printing the new lenght
    and returning the new list"""
    newList = numbers * 3
    n = len(newList)
    
    print()   
    print("The new list length is: ", len(newList))
    return newList

# an interactive menu
def printMenu(userList):
    
    print()
    print("------------------MENU-------------------------")
    print()
    print(" > Please enter 'b' to print largest number")
    print("-----------------------------------------------")
    print()
    print(" > Please enter 's' to print smallest number")
    print("-----------------------------------------------")
    print()
    print(" > Please enter any number for replication,")
    print("   listing the odd numbers and new list length")
    print()
    print("-----------------------------------------------")
    print()
    print(" > Please enter 'e' to terminate program")
    print("-----------------------------------------------")
    print()
# main function that takes 12 number from user and store them in userList array
def main():
    userList = [];

    print("Please enter 12 numbers: ")
    print("(confirm each number with 'enter' key) ")
    for numberTaken in range(0, 12):      
            number = int(input())
            userList.append(number)
    print()
    print("Your list is: ", userList)
    print()

# while loop depends on user input gives required result 
    while (True):
        printMenu(userList)
        selection = input();
        
        if selection.isnumeric():
            print(replicateList(userList))
            print()
            print()
        if selection in ("B","b"):
            print("The largest number is: ",largestNumber(userList))
            print()
            print()
        if selection in ("S","s"):
            print("The smallest number is: ", smallestNumber(userList))
            print()
            print()
        if selection in ("E","e"):
            print("Goodbye!!!")
            break

main()

### THE SOLUTION IS ALMOST PERFECT BUT THE REPLICATELIST FUNCTION IS NOT CONSISTENT WITH REQUIREMENTS.

### SCORE = 9 MARKS
