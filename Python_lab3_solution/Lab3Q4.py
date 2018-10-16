# -*- coding: utf-8 -*-
"""
Created on Sat Oct 13 15:18:20 2018

@author: Maciej Kubiniec
Write a program that asks the user to enter the rainfall for the first X months of the year into a
list
"""

def rainfall():
    # user choice of number of months
    monthsNum=int (input(' How many months of data do you wish to enter  \n Please enter value between 1 and 12: '))
    data=[] #empty array for rain values
    month=['January', 'February', 'March', 'April', 'May', 'June', 'July',
              'August', 'September', 'October', 'November', 'December']
    dataNum= range (monthsNum)
    rainData={} #Dictionary for month name with rain value
    if monthsNum  >= 1 and  monthsNum <= 12:# loop for months if user choise was between 1 and 12  
     for z in dataNum:        
        print ('Please enter rainfall for month', month[z],': ' )                
        list=int(input())         
        data.append(list) # apend value given by user into array 
        rainData[month[z]]=list     
     print ('Highest rainfall value: ',max(data))  
     print ('Lowest rainfall value: ',min(data)) 
     aver= sum(data)/len(data) # calculation af average rain value
     print ('Average rainfall value: ',aver)
     print ('Months that exceeded average: ',end= " ")
     for x in rainData:
         if rainData[x]>aver: #check for months that that exceeded average      
             print (x ,end= " ") 
                  
    else:
        print (' Number is out of range 1 to 12,\n Please enetr valid number')
        rainfall()
rainfall()
