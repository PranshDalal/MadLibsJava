<!-- TODO: Complete Readme -->

# 1.2.10 - MadLibs

## Algorithm

The algorithm utilizes the Scanner library to take inputs, string concantenation to return the completed and a text-to-speech library to have the computer output the final string as an audio recording.

First, the code imports all the necessary libraries (detailed above).

Second, the code initializes the object Main. Inside it, it creates two methods: main, where the string inputs are collected and concantenated, and SpeakText, which takes a String input and outputs an audio recording of the input.

In the first method, a Scanner is initialized. A String object template records the position of each input string in the larger, concantenated string. Then, the program uses the Scanner to record the input of several Strings that are concantenated together with some filler words in the template to create a cohesive sentence. This final sentence is both returned as a string in the terminal and "spoke" by the computer using the SpeakText method.

The SpeakText method sets a voice for a Synthesizer object, and proceeds to "speak" the text provided, as part of a try/catch series. If an Exception occurs instead, the stack trace is printed to help debug the code.
