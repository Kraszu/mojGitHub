#ifndef PAL_H_INCLUDED
#define PAL_H_INCLUDED

/*
	Robert Colan-O'Leary: R00090721: Version 4
*/
int displayState(int* pListOfNumbers,  int  positionOfCursor, int number_of_digits, int numberOfGoes);

void moveCursorRight(int* pPositionOfCursor, int max);

void moveCursorLeft(int* pPositionOfCursor, int min);

void incrementDigitInListAtPos(int* pListOfNumbers, int* positionOfCursor);

char get_command();

void processCommand(int* pList, int size, int* pPositionOfCursor, char command);
	
#endif