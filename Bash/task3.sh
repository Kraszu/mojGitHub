#!/bin/bash
#Maciej Kubiniec R00144142

clear
extraLine () {
#Assign variable to input parameter.
ts3=$1                    


#check if file exist and if it is readable

if [[ -r $ts3 && -w $ts3 ]]
then
echo "  The supplied file exists and is readable"
echo
echo  " Copying new contents to a  'doublets3.txt' file ."
#insert a new line after every line and write output to a txt file
lines=0
while IFS= read -r line
do
printf '%s\n' "${line}"
((lines++ )) && echo 
	
done < "$1"> doublets3.txt
	 
echo      
else
echo " The file doesn't exist or is unreadable"
echo " Please provide coorect file path next to ./task3.sh"
echo
exit 1
fi
# output new file to the screen
echo  "    The new file standard output is "    

cat doublets3.txt

}

#invoke function with parameter
extraLine  $1

## CORRECT SOLUTION

## SCORE = 6 MARKS
