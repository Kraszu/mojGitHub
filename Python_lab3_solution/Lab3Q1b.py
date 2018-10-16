# -*- coding: utf-8 -*-
"""
Created on Fri Oct 12 12:10:13 2018

@author: Maciej Kubiniec

This function will take in
two int values as parameters. The first will specify the base number and the second should
specify power number. The function will calculate value of the base number raised to the power
value and will return this value. Verify the function works correctly by storing the result
returned from the powerV3 function in a variable and printing out the variable.
"""

def powerV2(num, limit):
    
    return num ** limit # this function return calculation of numbers from main function
    
    
    
    
def main():
    
   num= int(input('Please enter base number: ')) # user input
   limit= int(input('Please enter power number: '))
   #printing caling for output from function powerV2
   print ('The value',num, 'raised to the power of',limit,'is:', powerV2(num, limit))   
   
main()