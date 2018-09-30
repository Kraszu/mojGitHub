#!/bin/bash
#Maciej Kubiniec R00144142

clear
all(){
#asking user for input
echo "please enter the first number: ";
read number_a
echo "please enter the second number: ";
read number_b

#function of mutliplication of first and second user input
multiplication(){

echo "$number_a*$number_b = $(($number_a*$number_b))"

}
#function of addition of first and second user input
 addition(){
echo "$number_a+$number_b = $(($number_a+$number_b))"
}
# function check if second user input is multiple of 5 to run one of the prevoious two functions
 selection(){
if  [ $(($number_b%5)) -eq 0 ]; 

then
multiplication
else
addition
fi
}
selection
}

# after four interactions script will terminate
for run in {1..4}
do
all

sleep 1
done

## FUNCTIONS SHOULD BE CALLED WITH PARAMETERS AND NOT USE GLOBAL VARIABLES.

## SCORE = 4 MARKS.

## TOTAL SCORE = 20.5 MARKS






