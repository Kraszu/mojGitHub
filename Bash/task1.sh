#!/bin/bash
#Maciej Kubiniec R00144142

# variable for user
user=$1
#if user nam is given and it is match
if [ "$user" == "$USER" ]
then
clear
echo "hello $USER"
echo
# the real user name is extract
echo "you real name is: "
grep "/bin/bash" /etc/passwd | cut -d: -f5 |tail -1
echo
#number of user logs
echo "times you have been logged in is: "
last pts/0| grep ^$username |  wc -l

echo
#last log time

echo "last time you logged in is: "
last $USER|cut -d: -s -f2 |head -1
echo



#if given name is not an user name or no name is given
elif [ "$user" != $USER ] || [ $user -z ]
then
clear
echo "no existing username has been provided"
echo
echo "users curently logged in are: "
echo
#List users logged in
who -u
echo

fi

## SOLUTION IS PARTIALLY CORRECT

## SCORE = 3.5 MARKS
