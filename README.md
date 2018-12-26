# Markov Chains 
I've known about the concept of Markov chains for a little while now and over the Christmas period 
I'd like to try and make a web app that demonstrates how this concept works. 

# Idea
After inputting a collection of data, assess how likely it is that a given string would be the result of what
the application knows about. If the application knew about 20 words (nodes) and those nodes had independent 
relationships, how likely is it that your string would be chosen? 

# Example

Say we input the following phrase into the application: 
    
    hello world. 
    
The word "hello" only has a relationship with the word "world". If we were to then query this in the manner of
"How likely is it that I'm going to get back the phrase 'hello world'?" then currently it would be 100%. 

However, if we were to add in the following phrase: 

    hello another world. 
    
This increases the number of nodes that the word "hello" is associated with, it can either return with "world" or
"another" - a 50/50 split*

If we then query the same question for "Hello world" this lowers our probability to a mere(!) 50% as the first node 
now has two paths it can take:

    p = .5 * 1 = .5
    p*100 = 50.
