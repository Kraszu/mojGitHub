# -*- coding: utf-8 -*-
"""
Created on Fri Oct 12 10:52:37 2018

@author: Maciej Kubiniec

Write a calculator. The program should first ask the user for two separate numerical values. It
should then give the user an option to perform one of four operations: addition, subtraction,
division or multiplication. Therefore, if the user selects multiplication then your program
should print out the product of the two values. You should write a different function for
performing each operation. For example the sum function will take in two int numerical
parameters and return the result.A user can exit by entering y/n when
asked to select the operation they want to perform
"""

def addition(num1, num2):
    add = num1 + num2
    return add #returning result
def subtraction(num1, num2):
    sub = num1 - num2
    return sub #returning result
def multiplication(num1, num2):
    mult = num1 * num2
    return mult #returning result
def division(num1, num2):
    dev = num1/num2
    return dev #returning result


def Main():#
    num1= int(input('Please enter a first numerical value:'))# user input
    num2= int(input('Please enter a second numerical value:'))
    
   
   # user selection of operation with two given numbers
    selection = int(input('Would you like to perform: \n 1: Addition \n 2: Subtraction \n 3: Multiplication \n 4: Division \n '))
        
        #depend of user choice system call to relevant function for results
    if selection == 1:
            print('Addition of ',num1, 'and' ,num2, 'is: ',addition(num1, num2))
            print()
            print()
    elif selection ==2:
            print('substraction of ',num1, 'and' ,num2, 'is: ',subtraction(num1, num2))
            print()
            print()
    elif selection ==3:
            print('Multiplication of ',num1, 'and' ,num2, 'is: ',multiplication(num1, num2))
            print()
            print()
    elif selection ==4:
            print('Division of ',num1, 'and' ,num2, 'is: ',division(num1, num2))
            print()
            print()  
       #validation of selection input     
    else:
        print ('Selected number is out of range!!! \nYou have to chose number from 1 to 4')
        print ('Start again \nPlease select two numbers for calculation')
        Main()
     # user choice for continue or exit   
    exitOp= input('Would you like to perform another operation y/n?')
    if exitOp == 'y' or exitOp == 'Y': # if answer is yes customer restart the program
        Main()
    else: # if no the program is terminated
            print("Goodbye!!!")
            exit
          
Main()