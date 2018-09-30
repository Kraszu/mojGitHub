#!/bin/bash
#Maciej Kubiniec R00144142

# function reguralFiles is the function that write to file all regulral files over 2K from directory given by user.Function will loop through that file to outpot each file to the user one at the time. It contains menu function that provide four options for user included copy and delate. it also contains function that records time of deletion file if that option is chosen by user. 

clear
reguralFiles () {                              
# path to the required target provided by user
echo "Please enter path to a target you wish to check:  "  
read path                               

echo
# check if the path to the direction is correct
if ! [[ -d $path ]]   
#if doesn't:   
then    
echo "  sorry but the path $path does not exist !"
echo  
reguralFiles
fi
#mentioned menu function 
menu () {                 
echo                           
echo "  Select option number:"
echo "  1. Copy file to directory 'copyStore'"
echo "  2. Delete file"
echo "  3. Ignore/Next file"
echo "  4. Exit program"
read userChoice

# option chsen by user case by case
case $userChoice in
# case 1 will check if direction to copy file exist and if no then will create one and then move the file or if does exist then will just move assign file to that direction
1) echo " Copying file $file" 
if ! [ -d CopyFile ]   
then
echo "creating directory 'CopyFile' for copied files."
mkdir CopyFile
cp $file CopyFile
		
else
cp $file CopyFile
clear
fi
;;
# case 2 will aloud user to delete assigned file and same time will create a log in which will write time of deletion (with usage of function deletionTime) and the name of deleted file 
2) echo " Deleting file $file" 
rm $file                   
echo "Time: $(deletionTime): Deleted file name:  $file" >> deletedFile.txt
echo
;;
# move to next position in the loop     
3) echo " Next file"       
clear
;;     
# exit program         
4) echo "   Good bye "                                                   
exit
;;
# if user chose will not much 1-4 numbers
*) clear                   
echo
echo "  Sorry but you have to choose an option between 1 and 4"
echo "  Please try again."
echo
menu
;;
esac 	 
		 
}  
# time function
deletionTime()                   
{
date +%F/%T
}
  
echo
# finsds all file size over 2k and writes them to file
find  -type f -size +2k -exec ls -h {} \; >filesOver2K.txt

filesOver2K=($(<filesOver2K.txt))  
 # Looping through the files to bring the files one at the time 
for file in "${filesOver2K[@]}"   
do
clear
#prints one file from a loop and then menu 
echo "  File:  $file   "
menu	
			
done 

  
}

#invoke function
reguralFiles
echo
echo "  Thank you."
echo
exit

## COMPLEX BUT CORRECT SOLUTION.

## SCORE = 7 MARKS
