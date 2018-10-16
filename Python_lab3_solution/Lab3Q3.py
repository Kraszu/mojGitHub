# -*- coding: utf-8 -*-
"""
Created on Fri Oct 12 12:15:34 2018

@author: Maciej Kubiniec

Write a guessing game for a user. This program should initially generate a random
number between 1 and 100.
"""
import random
    
def guesGame():    
    
    rand = random.randint(1,100) #selecting random number between 1 and 100
    guess= int(input('Please Guess the number between 1 nad 100: '))  #user guess      
    count =0 
    
    while guess != rand : # while asked number is not equal the user answer
        count +=1 #count the number of user guesses
                  
        if rand > guess: # if requested number is higher then answer
                 print ('to low')
                 guess= int(input('Please Guess the number between 1 nad 100: '))
        elif rand < guess: # if requested number is lower then answer
                 print ('to high')
                 guess= int(input('Please Guess the number between 1 nad 100: '))
    else:
            print('You answer is correct!!!')
            
             
            print ('You made a total of',count +1,' guesses.')
   
     
guesGame()