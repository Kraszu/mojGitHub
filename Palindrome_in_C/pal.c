#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "pal.h"
#include "useful.h"

/*
	Robert Colan-O'Leary: R00090721: Version 4
*/
void startGame(int initialNumber){
	
	int listOfNumbers[] = {1,2,3,4,2,1};
	int number_of_digits = 6;//sizeof(listOfNumbers);
	int positionOfCursor = random_number();
	int numberOfGoes = 0;
	int palindrome = is_palindrome(listOfNumbers, number_of_digits);
	
	//Check original Array to see if it is a palindrome
	//If it returns a 0 inform user it is not a palindrome
	if(palindrome == 0){
		printf("\nArray is not a palindrome\n");
	}
	
	//Call function to give user a display of the game
	displayState(listOfNumbers, positionOfCursor, number_of_digits, numberOfGoes);
	
	//Use a while loop to stay in the game until the list of numbers
	//becomes a palindrome, we check this through each iteration
	while(palindrome != 1){
		//process the user input
		processCommand(listOfNumbers, number_of_digits, &positionOfCursor, get_command());
		//if input is valid add 1 to the number of goes
		numberOfGoes++;
		//After each successful move display game state
		displayState(listOfNumbers, positionOfCursor, number_of_digits, numberOfGoes);
		//Check if it has become a palindrome
		palindrome = is_palindrome(listOfNumbers, number_of_digits);
	}
	
	//When the user is successful or original number is a palindrome
	//Display message below.
	printf("\nCongratulations Array is now a palindrome\n");
	
	//Correct grammer for count
	if(numberOfGoes == 1){
		printf("You completed the game in %d move!", numberOfGoes);
	}else{
		printf("You completed the game in %d moves!", numberOfGoes);
	}

}

//function to randomly generate number inclusive 0-5
//this is called in start to assign cursor position
int random_number(){
	
	srand(time(0));
	int r = rand() % 6;
	
	return r;
}

//Functioon call for information on game state
int displayState(int* pListOfNumbers,  int  positionOfCursor, int number_of_digits, int numberOfGoes)
{
	printf("\nGame State: ");
	printf("< ");
	for (int i = 0; i < number_of_digits; i++){
		printf("%d", pListOfNumbers[i]);
	}
	printf(" >");
	printf("\tCursor at : %d",positionOfCursor);
	printf("\tMove number : %d\n", numberOfGoes);
	printf("\t      ");
	for(int i = 0; i < positionOfCursor; i++){
		printf(" ");
	}
	printf("^\n");
	
  return 0;    
}

//function to increase the int value of the cursor by 1	
//if cursor value is equal max - 1 it does not increase
void moveCursorRight(int* pPositionOfCursor, int max){
	
	if(*pPositionOfCursor >= 0 && *pPositionOfCursor < max - 1){
		*pPositionOfCursor = *pPositionOfCursor + 1;
	}
	else if(*pPositionOfCursor == max - 1){
		printf("\nYou cannot go further right than position: %d\n", *pPositionOfCursor);
	}else{
		printf("Current position of Cursor : %d\n", *pPositionOfCursor);
	}

}

//function to decrease the int value of the cursor by 1	
//if cursor value is 0 it does not decrease
void moveCursorLeft(int* pPositionOfCursor, int min){
	
	if(*pPositionOfCursor > 0 && *pPositionOfCursor <= min - 1){
		*pPositionOfCursor = *pPositionOfCursor - 1;
	}
	else if(*pPositionOfCursor == 0){
		printf("\nYou cannot go further left than position: %d\n", *pPositionOfCursor);
	}else{
		printf("Current position of Cursor : %d\n", *pPositionOfCursor);
	}
	
}

//increase int value by 1, unless it is 9 it will go to 0
void incrementDigitInListAtPos(int* pListOfNumbers, int* positionOfCursor){
	
	printf("\nArray element in position %d",*positionOfCursor);
	printf(" has been increased by 1");
	
	if(pListOfNumbers[*positionOfCursor] == 9){
		pListOfNumbers[*positionOfCursor] = 0;
	}else{
		pListOfNumbers[*positionOfCursor]++;
	}
}

//decrease int value by 1, unless it is 0 it will go to 9
void decrementDigitInListAtPos(int* pListOfNumbers, int* positionOfCursor){
	
	printf("\nArray element in position %d",*positionOfCursor);
	printf(" has been decreased by 1");
	
	if(pListOfNumbers[*positionOfCursor] == 0){
		pListOfNumbers[*positionOfCursor] = 9;
	}else{
		pListOfNumbers[*positionOfCursor]--;
	}
}

char get_command() {
	char validCharacters[4] = { 'a', 'd', 'w', 'x'};
	char choice;
	int arrayCount = 0;
	int whileBreaker = 0;
	
	//display it
	printf("\nEnter a valid command from keyboard( ");
		for(int i = 0; i < sizeof(validCharacters); i++){
			printf("%c ",validCharacters[i]);
		}
	printf("):");
	scanf("%c", &choice);
	//Validate the user input against array
	while(choice != validCharacters[arrayCount]){
		//loop through each array element and compare input
		for(int j = 0; j < sizeof(validCharacters); j++){
			if(choice == validCharacters[j]){
				whileBreaker = 1;
				break;
			}else{
				arrayCount++;
			}
		}
		//If we have found a valid character we break the while loop
		if(whileBreaker == 1){
			break;
		}
		//else ask again
		printf("\nEnter a valid command from keyboard( ");
		for(int i = 0; i < sizeof(validCharacters); i++){
			printf("%c ",validCharacters[i]);
		}
		printf("):");
		scanf("%c", &choice);
	}
	return choice;
}

//processes valid character and assigns correct function to 
//each one
void processCommand(int* pList, int size, int* pPositionOfCursor, char command){
	
	if(command == 'a'){
		printf("\na entered\n");
		moveCursorLeft(pPositionOfCursor, size);
	}
	else if(command == 'd'){
		printf("\nd entered\n");
		moveCursorRight(pPositionOfCursor, size);
	}
	else if(command == 'w'){
		printf("\nw entered\n");
		incrementDigitInListAtPos(pList, pPositionOfCursor);
	}
	else{
		printf("\nx entered\n");
		decrementDigitInListAtPos(pList, pPositionOfCursor);
	}
}