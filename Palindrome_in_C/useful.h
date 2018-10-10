#ifndef USEFUL_H_INCLUDED
#define USEFUL_H_INCLUDED

/*
	Robert Colan-O'Leary: R00090721: Version 4
*/
//--------------------------------------------------
// DATA TYPES DEFINITIONS
//--------------------------------------------------
enum Bool { False, True };
typedef enum Bool boolean;

char my_get_char();

boolean is_palindrome(int* plist_of_numbers, int size);

#endif // USEFUL_H_INCLUDED