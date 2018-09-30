"""Assignment 2
TASK4
Maciej Kubiniec R00144142"""

"""function split the sentence each word a one element of an array, 
then look for word1 in the list and replace elemnt on that position with
word2"""
def replacer(Sentence, word1, word2):
    splitSentence = Sentence.split()     
    n = len(splitSentence)
    for x in range(0,n):
        if splitSentence[x] == word1:
           splitSentence[x] = word2     
    else:
        newSentence = ""
        for x in range(0,n):
            newSentence += splitSentence[x] + " "
        print()    
        print("Original sentence:", Sentence)
        print("Changed sentence: ", newSentence)
""" main function that takes from user sentence, first word to replace
and replacement. cheks if string with sentence is not empty and passes
values to replacer function"""
def main():
    print("Please write a sentence")
    sentence = input()
    print("Select the word you wish to replace ")
    word1 = input()
    print("Please write the word that you wish to appears in the sentence instead")
    word2 = input()
    if sentence != "":
        replacer(sentence, word1, word2)
    else:
        print("Please try again and write a sentence")

main()

### CORRECT SOLUTION

### SCORE = 8 MARKS

### TOTAL SCORE = 29.5 MARKS
