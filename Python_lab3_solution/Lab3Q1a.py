# -*- coding: utf-8 -*-
"""
Created on Fri Oct 12 12:08:00 2018

@author: Maciej Kubiniec

Your main function
should ask the user to input two int values. The first will specify the base number and the
second should specify power number. You will then called a function called powerV2. This
function will take in two int values as parameters (the base and power numbers). The function
will simply print the value of the base number raised to the power value.
"""

def powerV1(num, limit):
    
    
    
    #calculation using numbers from main function user inputs 
    print ('The value',num, 'raised to the power of',limit,'is',num ** limit)
    
def main(): #main function
    
   num= int(input('Please enter base number: '))# user input
   limit= int(input('Please enter power number: '))   
   powerV1(num, limit) #call for function
main()