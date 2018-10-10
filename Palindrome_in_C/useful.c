
/*
	Robert Colan-O'Leary: R00090721: Version 4
*/
//--------------------------------------------------
// DATA TYPES DEFINITIONS
//--------------------------------------------------
enum Bool { False, True };
typedef enum Bool boolean;
//--------------------------------------------------
// my_getchar
//--------------------------------------------------
char my_get_char() {
	//1. We create the output variable with the value the user has input by keyboard
	char res = getchar();

	//2. We discard any extra character the user has input by keyboard
	boolean finish = False;
	char dummy_char = res;

	while (finish == False) {
		if (dummy_char == '\n')
			finish = True;
		else
			dummy_char = getchar();
	}

	//3. We return the output variable
	return res;
}

boolean is_palindrome(int* plist_of_numbers, int size){
	
	boolean checker = True;
	int startArray = 0;
	int endArray = size - 1;
	int half = size / 2;
	
	while (endArray >= half){
		
		if(plist_of_numbers[startArray] != plist_of_numbers[endArray]){
			checker = False;
			break;
		}else{
			startArray++;
			endArray--;
		}
	}
	
	/*if(checker == False){
		printf("\nArray is not a palindrome\n");
		printf("Int %d != %d", plist_of_numbers[startArray], plist_of_numbers[endArray]);
	}else{
		printf("\nArray is a palindrome\n");
	}*/
	
	return checker;
	
}