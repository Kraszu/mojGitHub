# -*- coding: utf-8 -*-
"""
Created on Fri Oct 12 10:00:41 2018

@author: Maciej Kubiniec

Write a function called powerV1. When you call this function (from your main function) it
should ask the user for a base number and a power number. It should then print out the result
of raising the base number to the power of the second number

"""

def powerV1():
    
    
    num= int(input('Please enter base number: ')) #user inputs
    limit= int(input('Please enter power number: '))   
    
    print ('The value',num, 'raised to the power of',limit,'is',num ** limit) # print with calculation
    
def main():
    
    powerV1() #calling for the function 
    
main()